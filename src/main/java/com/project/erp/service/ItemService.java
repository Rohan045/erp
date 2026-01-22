package com.project.erp.service;

import com.project.erp.model.Item;
import com.project.erp.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    ItemRepo itemRepo;

    public void addItem(Item item){
        itemRepo.save(item);
    }

    public Item getItem(UUID item_id){
        Optional<Item> item = itemRepo.findById(item_id);
        return item.orElse(null);
    }

    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }
}
