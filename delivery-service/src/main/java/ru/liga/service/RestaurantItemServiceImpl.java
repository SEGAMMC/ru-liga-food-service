package ru.liga.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.liga.dto.response.ResponseMenuItem;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.repository.mybatis.RestaurantMenuItemRepositoryBatis;
import ru.liga.service.interfaces.RestaurantMenuItemService;

@Service
@RequiredArgsConstructor
public class RestaurantItemServiceImpl implements RestaurantMenuItemService {
    private final RestaurantMenuItemRepositoryBatis restaurantMenuItemRepositoryBatis;

    @Override
    public ResponseMenuItem getRestaurantItemById(Long id) {
        RestaurantMenuItem restaurantMenuItem =
                restaurantMenuItemRepositoryBatis.getRestaurantMenuItemById(id);
        ResponseMenuItem responseMenuItem = new ResponseMenuItem();
        responseMenuItem.setId(restaurantMenuItem.getId());
        responseMenuItem.setName(restaurantMenuItem.getName());
        responseMenuItem.setDescription(restaurantMenuItem.getDescription());
        responseMenuItem.setImage(restaurantMenuItem.getImage());
        responseMenuItem.setPrice(restaurantMenuItem.getPrice());
        return responseMenuItem;
    }

}
