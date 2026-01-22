package com.project.erp.service;


import com.project.erp.model.*;
import com.project.erp.model.dto.SalesLineDto;
import com.project.erp.model.dto.SalesOrderDto;
import com.project.erp.repository.ItemRepo;
import com.project.erp.repository.SalesHeaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesOrderService {

    @Autowired
    CustomerService customerService;

    @Autowired
    SalesHeaderRepo salesHeaderRepo;

    @Autowired
    ItemService itemService;

    @Autowired
    LocationService locationService;

    @Autowired
    InventoryService inventoryService;

    public UUID addSalesOrder(SalesOrderDto salesOrderDto) throws Exception{
        SalesHeader salesHeader = new SalesHeader();
        Customer customer = customerService.getCustomer(salesOrderDto.getCustomerId());
        if(customer == null){
            throw new Exception("Customer not found");
        }
        if(checkInventory(salesOrderDto.getSalesLineDtoList())){
            throw new Exception("Inventory is less than specified qty for items");
        }
        salesHeader.setCustomer(customer);
        salesHeader.setSell_to_contact_no(salesOrderDto.getSellToContactNo());
        salesHeader.setSell_to_contact_fax_no(salesOrderDto.getSellToContactFaxNo());
        salesHeader.setSell_to_contact_email(salesOrderDto.getSellToContactEmail());
        salesHeader.setSell_to_contact_role(salesOrderDto.getSellToContactRole());
        salesHeader.setOrder_date(salesOrderDto.getOrderDate());
        salesHeader.setPosting_date(salesOrderDto.getPostingDate());
        salesHeader.setDue_date(salesOrderDto.getDueDate());
        salesHeader.setRequested_delivery_date(salesOrderDto.getRequestedDeliveryDate());
        salesHeader.setExternal_document_no(salesOrderDto.getExternalDocumentNo());
//        salesHeaderRepo.save(salesHeader);
        List<SalesLine> salesLineList = new ArrayList<>();
        for(SalesLineDto salesLineDto : salesOrderDto.getSalesLineDtoList()){
            SalesLine salesLine = new SalesLine();
            salesLine.setSalesHeader(salesHeader);
            salesLine.setDescription(salesLineDto.getDescription());
            Item item = itemService.getItem(salesLineDto.getItemId());
            if(item == null){
                throw new Exception("Item not found");
            }
            salesLine.setItem(item);
            Location location = locationService.getLocation(salesLineDto.getLocationId());
            if(location == null){
                throw new Exception("Location not found");
            }
            salesLine.setLocation(location);
            salesLine.setQuantity(salesLineDto.getQuantity());
            salesLine.setUnit_of_measure_code(salesLineDto.getUnitOfMeasureCode());
            salesLineList.add(salesLine);
//            salesLineRepo.save(salesLine); This can lead to data inconsistency if any error for further lines
        }
        salesHeader.setSalesLineList(salesLineList);
        salesHeaderRepo.save(salesHeader);//salesLine cascade is enabled under sales header which will save objects
        return salesHeader.getSalesHeaderId();
    }

    private boolean checkInventory(List<SalesLineDto> salesLineDtoList) {
        for(SalesLineDto sld: salesLineDtoList){
            int qty = inventoryService.getInventory(new InventoryKey(sld.getItemId(), sld.getLocationId())).getQuantity();
            if(qty < sld.getQuantity()) return false;
        }
        return true;
    }

    public SalesHeader getSalesHeader(UUID salesHeaderId){
        Optional<SalesHeader> salesHeader = salesHeaderRepo.findById(salesHeaderId);
        return salesHeader.orElse(null);
    }

    public List<SalesHeader> getAllSalesOrders(){
        return salesHeaderRepo.findAll();
    }
}
