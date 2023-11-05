package ru.liga.service;

import org.springframework.stereotype.Service;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Restaurant;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class GeoService {
    private final double EARTH_RADIUS = 6372795; //радиус земли в метрах

    public double determineDistance(Courier courier, Restaurant restaurant) {
        String[] addressRestaurant = restaurant.getAddress().split("\\|");
        String coordCourierByOrder = courier.getCoordinates();
        String coordRestaurantByOrder = addressRestaurant[1];

        Map<String, Double> coordsCourier = parseCoords(coordCourierByOrder);
        Map<String, Double> coordsRestaurant = parseCoords(coordRestaurantByOrder);

        return determineDistanceByMapXY(coordsCourier, coordsRestaurant);
    }

    public double determineDistance(Restaurant restaurant, Customer customer) {
        String[] addressRestaurant = restaurant.getAddress().split("\\|");
        String[] addressCustomer = customer.getAddress().split("\\|");

        String coordRestaurantByOrder = addressRestaurant[1];
        String coordCustomerByOrder = addressCustomer[1];

        Map<String, Double> coordsRestaurant = parseCoords(coordRestaurantByOrder);
        Map<String, Double> coordsCustomer = parseCoords(coordCustomerByOrder);

        return determineDistanceByMapXY(coordsRestaurant, coordsCustomer);
    }


    private double determineDistanceByMapXY(Map<String, Double> coord1, Map<String, Double> coord2) {
        double longitudeCoord1 = coord1.get("longitude");
        double latitudeCoord1 = coord1.get("latitude");

        double longitudeCoord2 = coord2.get("longitude");
        double latitudeCoord2 = coord2.get("latitude");

        //рассчет координат в радианах
        double radLongitudeCoord1 = Math.toRadians(longitudeCoord1);
        double radLatitudeCoord1 = Math.toRadians(latitudeCoord1);

        double radLongitudeCoord2 = Math.toRadians(longitudeCoord2);
        double radLatitudeCoord2 = Math.toRadians(latitudeCoord2);

        //косинусы и синусы широт и разница долгот
        double cosLat1 = Math.cos(radLatitudeCoord1);
        double sinLat1 = Math.sin(radLatitudeCoord1);

        double cosLat2 = Math.cos(radLatitudeCoord2);
        double sinLat2 = Math.sin(radLatitudeCoord2);

        double delta = radLongitudeCoord2 - radLongitudeCoord1;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

        // вычисления длины большого круга
        double y = Math.sqrt(Math.pow(cosLat2 * sinDelta, 2) +
                Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDelta, 2));
        double x = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDelta;

        double atan = Math.atan2(y, x);
        double distance = atan * EARTH_RADIUS;

        return distance;
    }


    private Map<String, Double> parseCoords(String coords) {
        Map<String, Double> resultCoords = new HashMap<>();
        String[] twoCoords = coords.split(":");

        String longitude = twoCoords[0];
        String latitude = twoCoords[1];
        resultCoords.put("longitude", Double.parseDouble(longitude));
        resultCoords.put("latitude", Double.parseDouble(latitude));
        return resultCoords;
    }


}
