package com.funflowers.billing;

import com.flexionmobile.codingchallenge.integration.Purchase;

/**
 *
 * @author Agelos
 */
public class FunFlowersPurchase implements Purchase{
    private final String id;
    private final boolean consumed;
    private final String itemId;
    
    public FunFlowersPurchase(String id, boolean consumed, String itemId){
        this.id = id;
        this.consumed = consumed;
        this.itemId = itemId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean getConsumed() {
        return consumed;
    }

    @Override
    public String getItemId() {
        return itemId;
    }
}
