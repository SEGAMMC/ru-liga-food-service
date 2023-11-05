package ru.liga.service.interfaces;


import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.request.RequestOrderStatus;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.dto.response.ResponseOrdersList;

public interface KitchenService {
	
	ResponseMenuItem getMenuItemById(long id);
	
	ResponseMenuItem createNewMenuItem(RequestMenuItem requestMenuItem);
	
	ResponseMenuItem editMenuItem(RequestMenuItem requestMenuItem, long id);
	
	void updateOrderStatusByKitchen(RequestOrderStatus requestOrderStatus, long id);

	ResponseOrdersList getOrdersByStatusKitchen(String status);
	}