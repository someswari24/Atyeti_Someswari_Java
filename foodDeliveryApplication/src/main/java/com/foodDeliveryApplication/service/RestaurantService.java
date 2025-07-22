package com.foodDeliveryApplication.service;

import com.foodDeliveryApplication.dto.RestaurantDTO;
import com.foodDeliveryApplication.model.Restaurant;
import com.foodDeliveryApplication.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant add(RestaurantDTO restaurantDTO){
        Restaurant restaurant=new Restaurant();
        restaurant.setName(restaurantDTO.name);
        restaurant.setAddress(restaurantDTO.address);

        return restaurantRepository.save(restaurant);
    }
}
