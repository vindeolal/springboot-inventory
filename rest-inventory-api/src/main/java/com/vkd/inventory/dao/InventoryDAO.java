package com.vkd.inventory.dao;

import com.vkd.inventory.model.Inventory;
import com.vkd.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryDAO {

    @Autowired
    private InventoryRepository inventoryRepository;

    //to add to inventory
    public Inventory add(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    //to find all items
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    //to find item by id
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    //delete an item
    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }

}
