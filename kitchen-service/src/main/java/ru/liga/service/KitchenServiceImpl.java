package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.request.RequestMenuItem;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.repository.MenuItemRepository;
import ru.liga.service.interfaces.KitchenService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
	private final MenuItemRepository menuItemRepository;


	@Override
	public ResponseMenuItem getMenuItemById(Long id) {
        RestaurantMenuItem restaurantMenuItem = menuItemRepository.findById(id).get();
		return mapMenuItemToResponseMenuItem(restaurantMenuItem);
    }

	@Override
	public ResponseMenuItem createNewMenuItem(RequestMenuItem requestMenuItem) {
		RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem ();
		restaurantMenuItem =mapRequestMenuItemToMenuItem(requestMenuItem, restaurantMenuItem);
		restaurantMenuItem = menuItemRepository.save(restaurantMenuItem);
		return mapMenuItemToResponseMenuItem(restaurantMenuItem);
    }

	@Override
	public ResponseMenuItem editMenuItem(RequestMenuItem requestMenuItem, long id) {
		RestaurantMenuItem restaurantMenuItem =
			menuItemRepository.findById(id).get();
		restaurantMenuItem =mapRequestMenuItemToMenuItem(requestMenuItem, restaurantMenuItem);
		restaurantMenuItem = menuItemRepository.save(restaurantMenuItem);
		return mapMenuItemToResponseMenuItem(restaurantMenuItem);
    }

	@Override
	public void deleteMenuItem(long id) {
		menuItemRepository.deleteById(id);
	}

	private ResponseMenuItem mapMenuItemToResponseMenuItem(RestaurantMenuItem restaurantMenuItem){
		ResponseMenuItem respMenuItem = new ResponseMenuItem ();
		respMenuItem.setId(restaurantMenuItem.getId());
		respMenuItem.setRestaurantId(restaurantMenuItem.getRestaurantId().getId());
		respMenuItem.setName(restaurantMenuItem.getName());
		respMenuItem.setPrice(restaurantMenuItem.getPrice());
		respMenuItem.setImage(restaurantMenuItem.getImage());
		respMenuItem.setDescription(restaurantMenuItem.getDescription());
		return respMenuItem;
	}

	private RestaurantMenuItem mapRequestMenuItemToMenuItem(RequestMenuItem requestMenuItem
			, RestaurantMenuItem restaurantMenuItem){
		restaurantMenuItem.setRestaurantId(requestMenuItem.getRestaurantId());
		restaurantMenuItem.setName(requestMenuItem.getName());
		restaurantMenuItem.setPrice(requestMenuItem.getPrice());
		restaurantMenuItem.setImage(requestMenuItem.getImage());
		restaurantMenuItem.setDescription(requestMenuItem.getDescription());
		return restaurantMenuItem;
	}

}
