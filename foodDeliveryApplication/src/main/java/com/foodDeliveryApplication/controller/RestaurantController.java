package com.foodDeliveryApplication.controller;

import com.foodDeliveryApplication.dto.RestaurantDTO;
import com.foodDeliveryApplication.model.Restaurant;
import com.foodDeliveryApplication.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public Restaurant add(@RequestBody RestaurantDTO restaurantDTO){
        return restaurantService.add(restaurantDTO);
    }

    @GetMapping
    public List<Restaurant> all() {
        return restaurantService.getAll();
    }
}
