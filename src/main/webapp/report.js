function initMap() {

    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 60.7070, lng: 28.7572}, //координаты Выборга
        zoom: 13,
        disableDefaultUI: true //убираем кнопки с карты
    });

    let points = getPoints();
    //Marker garbage on map.
    let index = 0;
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
    }


}