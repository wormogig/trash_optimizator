package util;

public class Marker {
    private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

    public Marker(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
    }
}
