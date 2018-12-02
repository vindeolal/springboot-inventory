package com.vkd.inventory.controller;


import com.vkd.inventory.dao.InventoryDAO;
import com.vkd.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {


    @Autowired
    private InventoryDAO inventoryDAO;

    /**
     * add a new item into inventory
     *
     * @param inventory inventory object
     * @return inventory object
     */
    @PostMapping("/item")
    public Inventory addItem(@Valid @RequestBody Inventory inventory) {
        return inventoryDAO.add(inventory);
    }

    /**
     * get all the items from the inventory
     *
     * @return list of all inventory items
     */
    @GetMapping("/items")
    public List<Inventory> getAllItems() {
        return inventoryDAO.findAll();
    }

    /**
     * to get item details by item id
     *
     * @param id item ID
     * @return details of that item
     */
    @GetMapping("/item/{id}")
    public ResponseEntity<Inventory> findById(@PathVariable(value = "id") Long id) {
        Optional<Inventory> inventory = inventoryDAO.findById(id);

        return inventory.map(inv -> ResponseEntity.ok().body(inv)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * update an existing item into inventory
     *
     * @param id        item id
     * @param inventory updated inventory item
     * @return updated inventory item
     */
    @PutMapping("/item/{id}")
    public ResponseEntity<Inventory> updateItemDetails(@PathVariable(value = "id") Long id, @Valid @RequestBody Inventory inventory) {
        Optional<Inventory> optionalInventory = inventoryDAO.findById(id);

        if (optionalInventory.isPresent()) {
            Inventory inv = optionalInventory.get();

            inv.setDescription(inventory.getDescription());
            inv.setItemName(inventory.getItemName());
            inv.setQuantity(inventory.getQuantity());

            Inventory updatedInventory = inventoryDAO.add(inv);

            return ResponseEntity.ok().body(updatedInventory);
        } else return ResponseEntity.notFound().build();
    }

    /**
     * delete the item from inventory by id
     *
     * @param id item id
     * @return deleted item
     */
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Inventory> deleteById(@PathVariable(value = "id") Long id) {
        Optional<Inventory> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            inventoryDAO.deleteById(id);
            return ResponseEntity.ok().body(inventory.get());
        } else
            return ResponseEntity.notFound().build();

    }
}
