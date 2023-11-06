package ru.liga.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        log.info("[CourierServiceImpl:getCourierById]: " +
                        "Получили информацию о курьере с id {} ", courierId);
        return mapCourierToResponseCourier(courier);
    }

    @Override
    public ResponseCourier createNewCourier(RequestCourier requestCourier) {
        Courier courier = new Courier();
        courier.setPhone(requestCourier.getPhone());
        courier.setStatus(requestCourier.getStatus());
        courier.setCoordinates(requestCourier.getCoordinates());
        courier = courierRepository.save(courier);
        log.info("[CourierServiceImpl:createNewCourier]: " +
                        "Создали нового курьера с id {}", courier.getId());
        return mapCourierToResponseCourier(courier);
    }

    @Override
    public void updateCourierStatus(RequestCourierStatus requestCourierStatus
            , long id) {
        validator.isPositive(id);
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException(id));
        courier.setStatus(requestCourierStatus.getStatus());
        courier = courierRepository.save(courier);
        log.info("[CourierServiceImpl:updateCourierStatus]: " +
                        "Изменили статус курьера с id {} на статус {}"
                , id, courier.getStatus());
    }

    @Override
    public ResponseCourier getCourierByPhone(String phone) {
        Courier courier = courierRepositoryBatis.getCourierByPhone(phone);
        if (courier == null) throw new CourierNotFoundException(phone);
        log.info("[CourierServiceImpl:getCourierByPhone]: " +
                        "Получили информацию о курьере по номеру телефона {}"
                , courier.getPhone());
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