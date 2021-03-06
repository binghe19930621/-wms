package com.cmsystem.wms.form;


import com.cmsystem.wms.entity.SerializeBase;
import com.cmsystem.wms.model.Delivery;
import com.cmsystem.wms.model.StockEntry;
import org.springframework.stereotype.Component;

@Component
public class StockEntryDeliveryFinishForm extends SerializeBase {

    private StockEntry stockEntry;
    private Delivery delivery;
    private Integer needDelivery;

    public StockEntry getStockEntry() {
        return stockEntry;
    }

    public void setStockEntry(StockEntry stockEntry) {
        this.stockEntry = stockEntry;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Integer getNeedDelivery() {
        return needDelivery;
    }

    public void setNeedDelivery(Integer needDelivery) {
        this.needDelivery = needDelivery;
    }
}
