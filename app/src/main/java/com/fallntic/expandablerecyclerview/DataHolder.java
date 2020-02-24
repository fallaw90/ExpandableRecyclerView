package com.fallntic.expandablerecyclerview;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DataHolder {

    private List<Item> items;
    private List<Item> subItems;
    private List<Item> innerSubItems;
    private List<Item> elements;

    public DataHolder(){
        this.items = new ArrayList<Item>();
        this.subItems = new ArrayList<Item>();
        this.innerSubItems = new ArrayList<Item>();
        this.elements = new ArrayList<Item>();

        populateList(items, "Item ", 4);
        populateList(subItems, "Sub item ", 1);
        populateList(innerSubItems, "Inner sub item ", 2);
        populateList(elements, "elements  ", 2);
    }

    private void populateList(List<Item> listItems, String item_type, int itemNumber){
        for (int i = 0; i <= itemNumber ; i++) {
            Item item = new Item(item_type + i);
            listItems.add(item);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> getSubItems() {
        return subItems;
    }

    public List<Item> getInnerSubItems() {
        return innerSubItems;
    }

    public List<Item> getElements() {
        return elements;
    }
}
