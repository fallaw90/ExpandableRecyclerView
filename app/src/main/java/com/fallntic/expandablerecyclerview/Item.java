package com.fallntic.expandablerecyclerview;

public class Item {

    private String itemName;
    private boolean expanded;

    public Item(String itemName){
        this.itemName = itemName;
        this.expanded = false;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
