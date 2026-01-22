package com.project.erp.controller;

import com.project.erp.model.Item;
import com.project.erp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("post")
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        itemService.addItem(item);
        Item savedItem = itemService.getItem(item.getItem_id());
        if(savedItem != null){
            URI uri = URI.create("/item/get/" + savedItem.getItem_id());
            return ResponseEntity.created(uri).body(savedItem);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") UUID id){
        Item item = itemService.getItem(id);
        if(item != null){
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get")
    public ResponseEntity<List<Item>> getItems(){
        return ResponseEntity.ok(itemService.getAllItems());
    }
}
