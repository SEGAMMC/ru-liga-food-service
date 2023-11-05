package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestCourier;
import ru.liga.dto.request.RequestCourierStatus;
import ru.liga.dto.response.ResponseCourier;
import ru.liga.entity.Courier;
import ru.liga.exception.CourierNotFoundException;
import ru.liga.repository.hibernate.CourierRepository;
import ru.liga.repository.mybatis.CourierRepositoryBatis;
import ru.liga.service.interfaces.CourierService;
import ru.liga.util.Validator;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepositoryBatis courierRepositoryBatis;
    private final CourierRepository courierRepository;
    private final Validator validator;

    @Override
    public ResponseCourier getCourierById(long courierId) {
        validator.isPositive(courierId);
        Courier courier = courierRepositoryBatis.getCourierById(courierId);
        if (courier == null) throw new CourierNotFoundException(courierId);
        return mapCourierToResponseCourier(courier);
    }

    @Override
    public ResponseCourier createNewCourier(RequestCourier requestCourier) {
        Courier courier = new Courier();
        courier.setPhone(requestCourier.getPhone());
        courier.setStatus(requestCourier.getStatus());
        courier.setCoordinates(requestCourier.getCoordinates());
        courierRepository.save(courier);
        return mapCourierToResponseCourier(courier);
    }

    @Override
    public void updateCourierStatus(RequestCourierStatus requestCourierStatus
            , long id) {
        validator.isPositive(id);
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException(id));
        courier.setStatus(requestCourierStatus.getStatus());
        courierRepository.save(courier);
    }

    @Override
    public ResponseCourier getCourierByPhone(String phone) {
        Courier courier = courierRepositoryBatis.getCourierByPhone(phone);
        if (courier == null) throw new CourierNotFoundException(phone);
        return mapCourierToResponseCourier(courier);
    }

    private ResponseCourier mapCourierToResponseCourier(Courier courier) {
        ResponseCourier responseCourier = new ResponseCourier();
        responseCourier.setId(courier.getId());
        responseCourier.setStatus(courier.getStatus());
        responseCourier.setPhone(courier.getPhone());
        responseCourier.setCoordinates(courier.getCoordinates());
        return responseCourier;
    }
}