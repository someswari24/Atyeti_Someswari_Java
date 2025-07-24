package com.foodDeliveryApplication.service;

import com.foodDeliveryApplication.dto.MenuItemDTO;
import com.foodDeliveryApplication.model.MenuItem;
import com.foodDeliveryApplication.model.Restaurant;
import com.foodDeliveryApplication.repository.MenuItemRepository;
import com.foodDeliveryApplication.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public MenuItem add(MenuItemDTO menuItemDTO){
        Restaurant restaurant=restaurantRepository.findById(menuItemDTO.restaurantId).orElseThrow(null);
        MenuItem menuItem=new MenuItem();
        menuItem.setName(menuItemDTO.name);
        menuItem.setPrice(menuItemDTO.price);
        menuItem.setRestaurant(restaurant);

        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getByRestaurant(Long id) {
        return menuItemRepository.findAll().stream()
                .filter(m -> m.getRestaurant().getId().equals(id)).toList();
    }
}
