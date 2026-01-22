package com.project.erp.service;

import com.project.erp.enums.SourceDocument;
import com.project.erp.model.*;
import com.project.erp.model.dto.InventoryDto;
import com.project.erp.model.dto.WarehouseShipmentDto;
import com.project.erp.model.dto.WarehouseShipmentLineDto;
import com.project.erp.repository.WarehouseShipmentHeaderRepo;
import com.project.erp.repository.WarehouseShipmentLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class WarehouseShipmentService {
    @Autowired
    WarehouseShipmentHeaderRepo warehouseShipmentHeaderRepo;

    @Autowired
    WarehouseShipmentLineRepo warehouseShipmentLineRepo;

    @Autowired
    SalesOrderService salesOrderService;

    @Autowired
    SalesLineService salesLineService;

    @Autowired
    PostedSalesShipmentService postedSalesShipmentService;

    @Autowired
    InventoryService inventoryService;
    
    public List<WarehouseShipmentDto> transformFromSalesHeaderWrapper(UUID salesHeaderId) throws Exception{
        List<WarehouseShipmentHeader> warehouseShipmentHeaders = transformFromSalesHeader(salesHeaderId);
        List<WarehouseShipmentDto> warehouseShipmentDtoList = new ArrayList<>();
        for(WarehouseShipmentHeader wshHeader: warehouseShipmentHeaders){
            List<WarehouseShipmentLineDto> warehouseShipmentLineDtoList = getWarehouseShipmentLineDtoList(wshHeader);
            WarehouseShipmentDto warehouseShipmentDto = new WarehouseShipmentDto(
                    wshHeader.getWarehouseShipmentHeaderId(),
                    wshHeader.getLocation().getName(),
                    wshHeader.getLocation().getLocation_id(),
                    wshHeader.getPostingDate(),
                    wshHeader.getExternalDocumentNo(),
                    wshHeader.getShipmentDate(),
                    wshHeader.getShippingAgentCode(),
                    warehouseShipmentLineDtoList);
            warehouseShipmentDtoList.add(warehouseShipmentDto);
        }
        return warehouseShipmentDtoList;
    }

    private static List<WarehouseShipmentLineDto> getWarehouseShipmentLineDtoList(WarehouseShipmentHeader wshHeader) {
        List<WarehouseShipmentLineDto> warehouseShipmentLineDtoList = new ArrayList<>();
        for(WarehouseShipmentLine wshLine: wshHeader.getWarehouseShipmentLines()){
            WarehouseShipmentLineDto warehouseShipmentLineDto = new WarehouseShipmentLineDto(
                    wshLine.getSourceDocument(),
                    wshLine.getSalesHeader().getSalesHeaderId(),
                    wshLine.getItem().getItem_id(),
                    wshLine.getDescription(),
                    wshLine.getQuantity(),
                    wshLine.getQtyToShip(),
                    wshLine.getQtyShipped(),
                    wshLine.getQtyOutstanding(),
                    wshLine.getDueDate(),
                    wshLine.getUnitOfMeasureCode());
            warehouseShipmentLineDtoList.add(warehouseShipmentLineDto);
        }
        return warehouseShipmentLineDtoList;
    }

    private List<WarehouseShipmentHeader> transformFromSalesHeader(UUID salesHeaderId) throws Exception{
        List<WarehouseShipmentHeader> warehouseShipmentHeaders = new ArrayList<>();
        SalesHeader salesHeader = salesOrderService.getSalesHeader(salesHeaderId);
        if(salesHeader == null){
            throw new Exception("Sales Order not found");
        }
        Map<UUID, List<UUID>> locWithSalesLine = locationWithSalesLine(salesHeader.getSalesLineList());
        for(Map.Entry<UUID, List<UUID>> entry: locWithSalesLine.entrySet()){
            List<SalesLine> salesLines = new ArrayList<>();
            for(UUID salesLineId: entry.getValue()){
                SalesLine sl = salesLineService.getSalesLine(salesLineId);
                salesLines.add(sl);
            }
            warehouseShipmentHeaders.add(createWarehouseShipment(salesHeader, salesLines));
        }
        return warehouseShipmentHeaders;
    }
    private Map<UUID, List<UUID>> locationWithSalesLine(List<SalesLine> salesLines){
        Map<UUID, List<UUID>> mp = new HashMap<>();
        for(SalesLine sl: salesLines){
            UUID locationId = sl.getLocation().getLocation_id();
            if(mp.get(locationId) != null){
                mp.get(locationId).add(sl.getSales_line_id());
            }else{
                List<UUID> uuidList = new ArrayList<>();
                uuidList.add(sl.getSales_line_id());
                mp.put(locationId, uuidList);
            }
        }
        return mp;
    }
    private Map<UUID, List<UUID>> salesHeaderIdWithWshShipLine(List<WarehouseShipmentLine> warehouseShipmentLines) {
        Map<UUID, List<UUID>> mp = new HashMap<>();
        for(WarehouseShipmentLine wsl: warehouseShipmentLines){
            if(wsl.getQtyOutstanding() <= 0 ||
                    wsl.getQtyToShip() <= 0 ||
                    inventoryService.getInventory(
                            new InventoryKey(wsl.getItem().getItem_id(), wsl.getWarehouseShipmentHeader().getLocation().getLocation_id()))
                            .getQuantity() <= 0){
                continue;
            }
            UUID salesHeaderId = wsl.getSalesHeader().getSalesHeaderId();
            if(mp.get(salesHeaderId) != null){
                mp.get(salesHeaderId).add(wsl.getWarehouseShipmentLineId());
            }else{
                List<UUID> uuidList = new ArrayList<>();
                uuidList.add(wsl.getWarehouseShipmentLineId());
                mp.put(salesHeaderId, uuidList);
            }
        }
        return mp;
    }
    public WarehouseShipmentHeader createWarehouseShipment(SalesHeader salesHeader, List<SalesLine> salesLines){
        WarehouseShipmentHeader warehouseShipmentHeader = new WarehouseShipmentHeader();
        warehouseShipmentHeader.setLocation(salesLines.get(0).getLocation());
        warehouseShipmentHeader.setExternalDocumentNo(salesHeader.getExternal_document_no());
        warehouseShipmentHeader.setPostingDate(salesHeader.getPosting_date());
        List<WarehouseShipmentLine> warehouseShipmentLines = new ArrayList<>();
        for(SalesLine sl: salesLines){
            WarehouseShipmentLine warehouseShipmentLine = getWarehouseShipmentLine(salesHeader, sl);
            warehouseShipmentLine.setWarehouseShipmentHeader(warehouseShipmentHeader);
            warehouseShipmentLines.add(warehouseShipmentLine);
        }
        warehouseShipmentHeader.setWarehouseShipmentLines(warehouseShipmentLines);
        warehouseShipmentHeaderRepo.save(warehouseShipmentHeader);
        return warehouseShipmentHeader;
    }

    private static WarehouseShipmentLine getWarehouseShipmentLine(SalesHeader salesHeader, SalesLine sl) {
        WarehouseShipmentLine warehouseShipmentLine = new WarehouseShipmentLine();
        warehouseShipmentLine.setItem(sl.getItem());
        warehouseShipmentLine.setDescription(warehouseShipmentLine.getItem().getDescription());
        warehouseShipmentLine.setQuantity(sl.getQuantity());
        warehouseShipmentLine.setSalesHeader(salesHeader);
        warehouseShipmentLine.setDueDate(salesHeader.getDue_date());
        warehouseShipmentLine.setQtyOutstanding(warehouseShipmentLine.getQuantity());
        warehouseShipmentLine.setQtyShipped(0);
        warehouseShipmentLine.setQtyToShip(0);
        warehouseShipmentLine.setSourceDocument(SourceDocument.salesOrder);
        warehouseShipmentLine.setUnitOfMeasureCode(sl.getUnit_of_measure_code());
        warehouseShipmentLine.setSalesLine(sl);
        sl.setWarehouseShipmentLine(warehouseShipmentLine);
        return warehouseShipmentLine;
    }
    public WarehouseShipmentHeader getWarehouseShipmentHeader(UUID warehouseShipmentHeaderId) {
        Optional<WarehouseShipmentHeader> warehouseShipmentHeader = warehouseShipmentHeaderRepo.findById(warehouseShipmentHeaderId);
        return warehouseShipmentHeader.orElse(null);
    }
    public List<WarehouseShipmentHeader> getAllWarehouseShipments() {
        return warehouseShipmentHeaderRepo.findAll();
    }
    public WarehouseShipmentLine getWarehouseShipmentLineById(UUID id) {
        Optional<WarehouseShipmentLine> warehouseShipmentLine = warehouseShipmentLineRepo.findById(id);
        return warehouseShipmentLine.orElse(null);
    }
    public void updateWarehouseShipmentLine(UUID warehouseShipmentLineId, int qtyToShip) throws Exception{
        WarehouseShipmentLine warehouseShipmentLine = getWarehouseShipmentLineById(warehouseShipmentLineId);
        if(warehouseShipmentLine == null) {
            throw new Exception("Warehouse Shipment Line not found");
        }
        if(qtyToShip <= 0)
            throw new Exception("QtyToShip should be >= 0");
        if(warehouseShipmentLine.getQtyOutstanding() < qtyToShip)
            throw new Exception("QtyToShip quantity is more than outstanding quantity");
        warehouseShipmentLine.setQtyToShip(qtyToShip);
        warehouseShipmentLineRepo.save(warehouseShipmentLine);
    }

    public List<PostedSalesShipmentHeader> postWarehouseShipment(UUID warehouseShipmentHeaderId) throws Exception{
        //create posted sales shipment header for each different sales order present in warehouse shipment line
        WarehouseShipmentHeader warehouseShipmentHeader = getWarehouseShipmentHeader(warehouseShipmentHeaderId);
        if(warehouseShipmentHeader == null){
            throw new Exception("Warehouse Shipment Header Id not found");
        }
        List<WarehouseShipmentLine> warehouseShipmentLines = warehouseShipmentHeader.getWarehouseShipmentLines();

        Map<UUID, List<UUID>> mp = salesHeaderIdWithWshShipLine(warehouseShipmentLines);
        if(mp.isEmpty()) throw new Exception("No quantity or inventory available to be posted");

        List<PostedSalesShipmentHeader> postedSalesShipmentHeaders = postedSalesShipmentService.createPostedSalesShipmentFromWarehouseShipment(mp, warehouseShipmentHeader);
        //deduct the qty outstanding in warehouse shipment, make qty to ship to zero, add qty shipped
        for(WarehouseShipmentLine wsl : warehouseShipmentLines){
            wsl.setQtyOutstanding(wsl.getQtyOutstanding() - wsl.getQtyToShip());
            wsl.setQtyShipped(wsl.getQtyShipped() + wsl.getQtyToShip());
            //add qty shipped in sales line
            wsl.getSalesLine().setQty_shipped(wsl.getSalesLine().getQty_shipped() + wsl.getQtyToShip());
            inventoryService.updateInventory(new InventoryDto(
                    wsl.getItem().getItem_id(),
                    warehouseShipmentHeader.getLocation().getLocation_id(),
                    inventoryService.getInventory(new InventoryKey(
                                    wsl.getItem().getItem_id(),
                                    warehouseShipmentHeader.getLocation().getLocation_id()))
                            .getQuantity() - wsl.getQtyToShip()));
            wsl.setQtyToShip(0);
            warehouseShipmentLineRepo.save(wsl);
        }
        return postedSalesShipmentHeaders;
    }
}
