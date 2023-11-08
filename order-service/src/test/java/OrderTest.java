import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.RequestOrder;
import ru.liga.dto.request.RequestOrderItem;
import ru.liga.dto.response.ResponseOrderAccept;
import ru.liga.service.interfaces.OrderService;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class OrderTest {

    private final OrderService orderService;

    @Transactional
    @Test
    public void methodCreateNewOrder_Accept_OK() {
    
		//формируем requestOrder для запроса
		long restaurantId = 5L;
		long customerId = 2L;
		List<RequestOrderItem> orderItemList = new ArrayList<>();
		
		for(int i = 1; i<4;i++){
			RequestOrderItem roi = new RequestOrderItem(1, (long)i);
			orderItemList.add(roi);
		}
		
		RequestOrder requestOrder = new RequestOrder();
		requestOrder.setRestaurantId(restaurantId);
		requestOrder.setOrderItems(orderItemList);
				
		//Отправляем 2 запрос в сервис, создавая 2 разных заказа
		ResponseOrderAccept respOrderAccept1 = orderService
				.createNewOrder(requestOrder);
				
		ResponseOrderAccept respOrderAccept2 = orderService
				.createNewOrder(requestOrder);
				
		//Проверяем что Id, UUID и SecretPaymentUrl заказов отличаются
		assertThat(respOrderAccept1.getSecretPaymentUrl())
			.isNotEqualTo(respOrderAccept2.getSecretPaymentUrl());
			
        assertThat(respOrderAccept1.getId())
			.isNotEqualTo(respOrderAccept2.getId());

		assertThat(respOrderAccept1.getUuid())
				.isNotEqualTo(respOrderAccept2.getUuid());

	}
}
