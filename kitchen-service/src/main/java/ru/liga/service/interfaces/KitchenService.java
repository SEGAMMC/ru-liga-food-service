package ru.liga.service.interfaces;


import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.request.RequestUpdatePriceMenuItem;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseOrdersList;

public interface KitchenService {
	
	ResponseMenuItem getMenuItemById(long id);
	
	ResponseMenuItem createNewMenuItem(RequestMenuItem requestMenuItem);
	
	ResponseMenuItem editMenuItem(RequestMenuItem requestMenuItem, long id);
	
	void updateOrderStatusByKitchenAccept( String uuid);

	void updateOrderStatusByKitchenDecline(String uuid);

	void updateOrderStatusByKitchenReady(String uuid);

	void updatePriceByMenuItem(RequestUpdatePriceMenuItem requestUpdatePriceMenuItem, long id);

	ResponseOrdersList getOrdersByStatusKitchen(String status);
	}