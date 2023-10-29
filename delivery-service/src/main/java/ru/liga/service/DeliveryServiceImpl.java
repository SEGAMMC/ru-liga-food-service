package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseCustomer;
import ru.liga.dto.response.ResponseDeliveryOrder;
import ru.liga.dto.response.ResponseRestaurant;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.feign.FeignDelivery;
import ru.liga.repository.hibernate.OrdersRepository;
import ru.liga.service.interfaces.DeliveryService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final OrdersRepository ordersRepository;
	private final FeignDelivery feignDelivery;
	private static GeoService geoService;
	private static final double CASH_BY_METTERS=7;

	@Override
    public List<ResponseDeliveryOrder> getDeliveryOrdersByStatus(RequestOrderStatus status) {
        List<Order> orders =  ordersRepository.getOrderByStatus(status);
		List<ResponseDeliveryOrder>  responseDeliveryOrderList = new ArrayList<>();
        for (Order order:orders) {
			ResponseDeliveryOrder respDeliveryOrder = convertOrderToResponseOrder(order);
			responseDeliveryOrderList.add(respDeliveryOrder);
        }
	    return responseDeliveryOrderList;
    }

	@Override
	public ResponseDeliveryOrder getDeliveryOrderById (long id){
		Order order =  ordersRepository.findById(id).get();		
		return convertOrderToResponseOrder(order);
	}
	@Override
	public void updateDeliveryOrderStatus(RequestOrderStatus requestDeliveryStatus, long id) {
		feignDelivery.updateOrderStatus(requestDeliveryStatus, id);
    }
	
	private static ResponseDeliveryOrder convertOrderToResponseOrder (Order order){
			ResponseDeliveryOrder respDeliveryOrder = new ResponseDeliveryOrder();
			ResponseRestaurant respRestaurant = new ResponseRestaurant();
			ResponseCustomer respCustomer = new ResponseCustomer();
							
			Courier courierOrder = order.getCourierId();
			
			Restaurant restaurantOrder = order.getRestaurantId();
			respRestaurant.setAddress(restaurantOrder.getAddress());
			double distanceByRestaurant = geoService.determineDistance(courierOrder, restaurantOrder);
			respRestaurant.setDistance(distanceByRestaurant);
						
			Customer customerOrder = order.getCustomerId();
			respCustomer.setAddress(customerOrder.getAddress());
			double distanceByCustomer = geoService.determineDistance(courierOrder, customerOrder);
			respCustomer.setDistance(distanceByCustomer);
			
			double paymentByDelivery = (distanceByRestaurant + distanceByCustomer)*CASH_BY_METTERS;
			
			respDeliveryOrder.setOrderId(order.getId());
			respDeliveryOrder.setRestaurant(respRestaurant);
			respDeliveryOrder.setCustomer(respCustomer);
			respDeliveryOrder.setPayment(paymentByDelivery);
		
		return respDeliveryOrder;
	}

}
