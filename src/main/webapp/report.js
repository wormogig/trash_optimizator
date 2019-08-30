function initMap() {

    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 60.7070, lng: 28.7572}, //координаты Выборга
        zoom: 13,
        disableDefaultUI: true //убираем кнопки с карты
    });
    let jsons = getPoints();
    //Marker garbage on map.
    let green = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
    let red = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";

    for (let i in jsons[0]) {
        let model = jsons[0][i];
        let markerPosition = {lat: model.lat, lng: model.lng};
        let title = model.id;
        let marker = new google.maps.Marker({
            position: markerPosition,
            map: map,
            title: "'" + title + "'",
            id: model.id,
            icon: {
                url: "http://maps.google.com/mapfiles/ms/icons/green-dot.png"
            }
        });
    }

    for (let i in jsons[1]) {
        let model = jsons[1][i];
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
    }

    // alert(window.location.href);
}

function getPoints() {
    let id = window.location.href.split("=");
    let points;
    $.ajax({
        type: 'GET',
        url: '/getreport',
        data: {id: id[1]},
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