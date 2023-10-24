package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.entity.Courier;
import ru.liga.repository.mybatis.CourierRepositoryBatis;
import ru.liga.service.interfaces.CourierService;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepositoryBatis courierRepositoryBatis;

    @Override
    public ResponseCourier getCourierById(Long id) {
        Courier courier = courierRepositoryBatis.getCourierById(id);
        ResponseCourier responseCourier = new ResponseCourier();
        responseCourier.setId(courier.getId());
        responseCourier.setStatus(courier.getStatus());
        responseCourier.setPhone(courier.getPhone());
        responseCourier.setCoordinates(courier.getCoordinates());
        return responseCourier;
    }

    @Override
    public ResponseCourier getCourierByPhone(String phone) {
        Courier courier = courierRepositoryBatis.getCourierByPhone(phone);
        ResponseCourier responseCourier = new ResponseCourier();
        responseCourier.setId(courier.getId());
        responseCourier.setStatus(courier.getStatus());
        responseCourier.setPhone(courier.getPhone());
        responseCourier.setCoordinates(courier.getCoordinates());
        return responseCourier;
    }

    @Override
    public ResponseCourier updateCourierStatus(RequestCourier requestCourier) {
        Courier courier = courierRepositoryBatis.getCourierById(requestCourier.getId());
        courier.setStatus(requestCourier.getStatus());
        courierRepositoryBatis.updateCourierStatus(courier);
        ResponseCourier responseCourier = new ResponseCourier();
        responseCourier.setId(courier.getId());
        responseCourier.setPhone(courier.getPhone());
        responseCourier.setStatus(courier.getStatus());
        responseCourier.setCoordinates(courier.getCoordinates());
        return responseCourier;
    }
}
