let garbageIds = [];
let urns;
let map;
let drawingManager;
let previousCircle;
let currentCircle;
let calcNeed = true;
let markers = [];

document.getElementById("urnsSend").hidden = "hidden";

function garbageCount() {

    garbageIds.length = 0;

    let points = getPoints();
    for (let i in points) {
        let model = points[i];
        let title = model.id;
        if (isInCircle(model.lat, model.lng, currentCircle.getCenter().lat(), currentCircle.getCenter().lng(), currentCircle.getRadius())) {
            garbageIds.push(model.id);
        }
    }
    document.getElementById('garbageCount').value = garbageIds.length;

}

function initMap() {

    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 60.7070, lng: 28.7572}, //координаты Выборга
        zoom: 13,
        disableDefaultUI: true //убираем кнопки с карты
    });

    drawingManager = new google.maps.drawing.DrawingManager({
        drawingControl: true,
        drawingControlOptions: {
            position: google.maps.ControlPosition.TOP_CENTER,
            drawingModes: ['circle']
        },
        circleOptions: {
            fillColor: '#ffff00',
            fillOpacity: 0.25,
            strokeWeight: 1,
            clickable: true,
            editable: true,
            zIndex: 1
        }
    });
    drawingManager.setMap(map);

    google.maps.event.addListener(drawingManager, 'circlecomplete', function (circle) {
        try {
            previousCircle.setMap(null);
        } catch (e) {
        }
        currentCircle = circle;
        previousCircle = currentCircle;
        document.getElementById("garbageCalc").disabled = false;
    });

    let points = getPoints();
    //Marker garbage on map.
    for (let i in points) {
        let model = points[i];
        let markerPosition = {lat: model.lat, lng: model.lng};
        let title = model.id;
        let marker = new google.maps.Marker({
            position: markerPosition,
            map: map,
            title: "'" + title + "'",
            id: model.id,
            icon: {
                url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png"
            }
        });
        markers.push(marker);

        google.maps.event.addListener(marker, 'click', function () {
            showInfo(getPoint(marker.id), marker)
        });
    }

    function showInfo(pointInfo, marker) {
        let infoWindow = new google.maps.InfoWindow({
            content: "<p>" + pointInfo.categoryTitle + "<p>" +
                "<img src='data:image/jpeg;base64," + pointInfo.image + "'/>" + "<br>" +
                "<button onclick='removePoint(" + marker.id + ")'> Удалить</button>",
        })
        infoWindow.open(map, marker);
    }

}

function removePoint(id) {
    $.ajax({
        type: 'POST',
        url: '/point/admin',
        async: false,
        data: {action: 'delete', id: id},
        success: function () {
            markers.forEach(function (mrk) {
                if (mrk.id === id) {
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
    let input = document.getElementById("urnCount").value;
    if (!isNaN(input) && garbageIds.length >= input) {
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

        removeDrawingControl();
    }
}

function removeDrawingControl() {
    drawingManager.setDrawingMode(null);
    drawingManager.setOptions({
        drawingControl: false
    });
}

function urnsSend() {
    $.ajax({
        type: 'POST',
        url: '/report',
        data: {garbageIds: JSON.stringify(garbageIds), urns: JSON.stringify(urns)},
        success: function () {
            alert("Отправлено!");
        },
        error: function (error) {
            console.log(error.responseText);
        },
    });
}