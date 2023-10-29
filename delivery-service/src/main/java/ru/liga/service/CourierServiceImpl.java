package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.request.RequestCourierStatus;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.entity.Courier;
import ru.liga.repository.hibernate.CourierRepository;
import ru.liga.repository.mybatis.CourierRepositoryBatis;
import ru.liga.service.interfaces.CourierService;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepositoryBatis courierRepositoryBatis;
    private final CourierRepository courierRepository;

    public ResponseCourier getCourierById(Long id) {
        Courier courier = courierRepositoryBatis.getCourierById(id);
		return convertCourierToResponseCourier(courier);
    }

    public ResponseCourier createNewCourier(RequestCourier requestCourier) {
        Courier courier = new Courier();
        courier.setPhone(requestCourier.getPhone());
        courier.setStatus(requestCourier.getStatus());
        courierRepository.save(courier);
		return convertCourierToResponseCourier(courier);
    }

    public void updateCourierStatus(RequestCourierStatus requestCourierStatus, Long id) {
        Courier courier = courierRepository.findById(id).get();
        courier.setStatus(requestCourierStatus.getStatus());
        courierRepository.save(courier);
    }


    public ResponseCourier getCourierByPhone(String phone) {
        Courier courier = courierRepositoryBatis.getCourierByPhone(phone);
		return convertCourierToResponseCourier(courier);
    }
	
	private static ResponseCourier convertCourierToResponseCourier(Courier courier){
		ResponseCourier responseCourier = new ResponseCourier();
        responseCourier.setId(courier.getId());
        responseCourier.setStatus(courier.getStatus());
        responseCourier.setPhone(courier.getPhone());
        responseCourier.setCoordinates(courier.getCoordinates());
		return responseCourier;
	}

}
