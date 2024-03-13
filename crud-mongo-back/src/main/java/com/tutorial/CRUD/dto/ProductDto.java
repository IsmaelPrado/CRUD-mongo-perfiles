package com.tutorial.CRUD.dto;

public class ProductDto {
    private String name;
    private int price;

    public ProductDto(){}

    public ProductDto(String name,int price){
        this.name = name;
        this.price = price;

    }

    //GETTERS
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    
    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
