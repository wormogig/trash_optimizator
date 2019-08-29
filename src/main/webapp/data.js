let garbageIds = [];
let urns;
let map;
let markers = [];

document.getElementById("urnsSend").hidden = "hidden";

function garbageCount() {
    document.getElementById('garbageCount').value = garbageIds.length;
}

function initMap() {
    // let marker;

    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 60.7735, lng: 28.6894}, //координаты lavola
        zoom: 13,
        disableDefaultUI: true //убираем кнопки с карты
    });

    let drawingManager = new google.maps.drawing.DrawingManager({
        drawingControl: true,
        drawingControlOptions: {
            position: google.maps.ControlPosition.TOP_CENTER,
            drawingModes: ['circle']
        },
        circleOptions: {
            fillColor: '#ffff00',
            fillOpacity: 0.35,
            strokeWeight: 1,
            clickable: true,
            editable: true,
            zIndex: 1
        }
    });
    drawingManager.setMap(map);

    let points = getPoints();
    //Marker garbage on map.
    let index = 0;
    for (let i in points) {
        let model = points[i];
        let markerPosition = {lat: model.lat, lng: model.lng};
        let title = model.id;
        let pointInfo
        // let pointInfo = getPoint(title);
        let marker = new google.maps.Marker({
            position: markerPosition,
            // info: pointInfo,
            map: map,
            title: "'" + title + "'",
            id: model.id,
            icon: {
                url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png"
            }
        });
        markers.push(marker);

        google.maps.event.addListener(marker, 'click', function () {
        showInfo(getPoint(marker.id),marker)
        });
    index ++;
    }

    function showInfo(pointInfo,marker){
        let infoWindow = new google.maps.InfoWindow({
            content: "<p>" + pointInfo.categoryTitle + "<p>" +
                "<img src='data:image/jpeg;base64," + pointInfo.image + "'/>" + "<br>" +
            "<button onclick='removePoint(" + marker.id + ")'> Удалить</button>",
        })
        infoWindow.open(map, marker);
    }

    //Marker urns on map
    google.maps.event.addListener(drawingManager, 'circlecomplete', function (circle) {
        let points = getPoints();
        for (let i in points) {
            let model = points[i];
            let title = model.id;
            if (isInCircle(model.lat, model.lng, circle.getCenter().lat(), circle.getCenter().lng(), circle.getRadius())) {
                garbageIds.push(model.id);
            }
        }
    });

}

function removePoint(id) {
    $.ajax({
        type: 'POST',
        url: '/point/admin',
        async: false,
        data: {action: 'delete', id: id},
        success: function () {
            markers.forEach(function (mrk) {
                if(mrk.id===id){
                    mrk.setMap(null);
                }
            })
        },
        error: function (error) {
            console.log(error.responseText);
        }
    })


}

function drawUrns(data) {
    for (let i in data) {
        let model = data[i];
        let markerPosition = {lat: model.lat, lng: model.lng};
        let title = model.id;
        let marker = new google.maps.Marker({
            position: markerPosition,
            map: map,
            icon: {
                url: "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
            }
        });
    }
}

function isInCircle(pLat, pLong, cLat, cLong, cRad) {
    var pCord = new google.maps.LatLng(pLat, pLong);
    var cCord = new google.maps.LatLng(cLat, cLong);
    return google.maps.geometry.spherical.computeDistanceBetween(pCord, cCord) <= cRad;
}

function getPoint(pointId) {
    let pointInfo;
    $.ajax({
        type: 'GET',
        url: '/point/admin',
        async: false,
        data: {id: pointId},
        success: function (data) {
            pointInfo = data;
        },
        error: function (error) {
            console.log(error.responseText);
        }
    })
    return pointInfo;
}

function getPoints() {
    let points;
    $.ajax({
        type: 'GET',
        url: '/points',
        async: false,
        success: function (data) {
            points = data;
        },
        error: function (error) {
            console.log(error.responseText);
        }
    });
    return points;
}

function sendPoints() {
    if (garbageIds.length >= $('#urnCount').val()) {
        $.ajax({
            type: 'POST',
            url: '/optim',
            async: false,
            data: {garbageIds: JSON.stringify(garbageIds), urnCount: $('#urnCount').val()},
            success: function (data) {
                urns = data;
            },
            error: function (error) {
                console.log(error.responseText);
            },
        });
        for (let i in urns) {
            let model = urns[i];
            let markerPosition = {lat: model.lat, lng: model.lng};
            let title = model.id;
            let marker = new google.maps.Marker({
                position: markerPosition,
                map: map,
                icon: {
                    url: "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
                }
            });
        }
        $('#exampleModal2 .close').click();
        document.getElementById("garbageCalc").hidden = "hidden";
        document.getElementById("urnsSend").hidden = "";
    }
}

function urnsSend() {
    $.ajax({
        type: 'POST',
        url: '/report',
        data: {garbageIds: JSON.stringify(garbageIds), urns: JSON.stringify(urns)},
        success: function () {
        },
        error: function (error) {
            console.log(error.responseText);
        },
    });
}