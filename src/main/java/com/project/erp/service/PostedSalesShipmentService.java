package com.project.erp.service;

import com.project.erp.model.*;
import com.project.erp.repository.PostedSalesShipmentHeaderRepo;
import com.project.erp.repository.PostedSalesShipmentLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PostedSalesShipmentService {
    @Autowired
    PostedSalesShipmentHeaderRepo postedSalesShipmentHeaderRepo;

    @Autowired
    PostedSalesShipmentLineRepo postedSalesShipmentLineRepo;

    @Autowired
    SalesOrderService salesOrderService;

    @Autowired
    @Lazy
    WarehouseShipmentService warehouseShipmentService;

    public List<PostedSalesShipmentHeader> createPostedSalesShipmentFromWarehouseShipment(Map<UUID, List<UUID>> salesHeaderIdWithWshLine, WarehouseShipmentHeader warehouseShipmentHeader){
        List<PostedSalesShipmentHeader> postedSalesShipmentHeaders = new ArrayList<>();
        for(Map.Entry<UUID, List<UUID>> entry: salesHeaderIdWithWshLine.entrySet()){
            SalesHeader salesHeader = salesOrderService.getSalesHeader(entry.getKey());
            PostedSalesShipmentHeader postedSalesShipmentHeader = new PostedSalesShipmentHeader();
            postedSalesShipmentHeader.setCustomer(salesHeader.getCustomer());
            postedSalesShipmentHeader.setPostingDate(warehouseShipmentHeader.getPostingDate());
            postedSalesShipmentHeader.setSalesHeader(salesHeader);
            postedSalesShipmentHeader.setWarehouseShipmentHeader(warehouseShipmentHeader);
            postedSalesShipmentHeaderRepo.save(postedSalesShipmentHeader);
            postedSalesShipmentHeader.setPostedSalesShipmentLines(generatePostedSalesShipmentLines(entry.getValue(), postedSalesShipmentHeader, warehouseShipmentHeader.getLocation()));
            postedSalesShipmentHeaders.add(postedSalesShipmentHeader);
        }
        return postedSalesShipmentHeaders;
    }

    private List<PostedSalesShipmentLine> generatePostedSalesShipmentLines(List<UUID> warehouseShipmentLineIds, PostedSalesShipmentHeader postedSalesShipmentHeader, Location location) {
        List<PostedSalesShipmentLine> postedSalesShipmentLines = new ArrayList<>();
        for(UUID wshLine: warehouseShipmentLineIds){
            WarehouseShipmentLine warehouseShipmentLine = warehouseShipmentService.getWarehouseShipmentLineById(wshLine);
            PostedSalesShipmentLine postedSalesShipmentLine = new PostedSalesShipmentLine();
            postedSalesShipmentLine.setPostedSalesShipmentHeader(postedSalesShipmentHeader);
            postedSalesShipmentLine.setLocation(location);
            postedSalesShipmentLine.setWarehouseShipmentLine(warehouseShipmentLine);
            postedSalesShipmentLineRepo.save(postedSalesShipmentLine);
            postedSalesShipmentLines.add(postedSalesShipmentLine);
        }
        return postedSalesShipmentLines;
    }
}
