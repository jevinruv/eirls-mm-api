package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.form.Bom;
import com.jevin.eirlsmmapi.model.SalesOrder;
import com.jevin.eirlsmmapi.service.BomService;
import com.jevin.eirlsmmapi.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BomMonitor {

    @Autowired
    BomService bomService;

    @Scheduled(fixedDelay = 5000)
    public void checkBom() {

        List<Bom> bomList = bomService.getBoms();

        if (!bomList.isEmpty()) {
            bomService.validateOrder(bomList);
        }
    }

}
