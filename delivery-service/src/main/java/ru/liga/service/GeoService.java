package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Restaurant;

import java.util.List;

@Service
public class GeoService {
    //написать взаимодействие с внешним апи для определения координат
	//или сразу дистанции между 2 координатами

    public double determineDistance(Courier courier, Restaurant restaurant) {
		//составляем json на внешний АПИ для определения координат по адресу
		//double distance =determineDistanceByCoords(coord1, coord2);
		return 0;
    }
	
	public double determineDistance(Courier courier, Customer customer) {
		//составляем json на внешний АПИ для определения координат по адресу
		//double distance =determineDistanceByCoords(coord1, coord2);
		return 0;
    }

	private double determineDistanceByCoords(double coord1, double coord2){
		
		
	return 0;
	}

}
