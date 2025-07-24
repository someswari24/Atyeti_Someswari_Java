package com.foodDeliveryApplication.controller;

import com.foodDeliveryApplication.dto.MenuItemDTO;
import com.foodDeliveryApplication.model.MenuItem;
import com.foodDeliveryApplication.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public MenuItem add(@RequestBody MenuItemDTO menuItem) {
        return menuItemService.add(menuItem);
    }

    @GetMapping("/restaurant/{id}")
    public List<MenuItem> get(@PathVariable Long id) {
        return menuItemService.getByRestaurant(id);
    }

    @GetMapping
    public List<MenuItem> getAll() {
        return menuItemService.getAll();
    }

}
