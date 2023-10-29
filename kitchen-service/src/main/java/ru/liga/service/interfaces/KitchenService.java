package ru.liga.service.interfaces;


import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.response.ResponseMenuItem;

public interface KitchenService {
	
	ResponseMenuItem getMenuItemById(Long id);
	
	ResponseMenuItem createNewMenuItem(RequestMenuItem requestMenuItem);
	
	ResponseMenuItem editMenuItem(RequestMenuItem requestMenuItem, long id);
    
	void deleteMenuItem(long id);
	}