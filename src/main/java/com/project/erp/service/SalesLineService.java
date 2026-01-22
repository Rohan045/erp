package com.project.erp.service;

import com.project.erp.model.SalesLine;
import com.project.erp.repository.SalesLineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SalesLineService {

    @Autowired
    SalesLineRepo salesLineRepo;

    public SalesLine getSalesLine(UUID salesLineId){
        Optional<SalesLine> salesLine = salesLineRepo.findById(salesLineId);
        return salesLine.orElse(null);
    }
}
