package com.example.models;

import java.util.ArrayList;

public class ListProduct {
    private ArrayList<Product> products;
    public ListProduct(){
        products=new ArrayList<>();
    }
    public void addProduct(Product p)
    {
        products.add(p);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products){
        this.products=products;
    }
    public void generate_sample_dataset(){
        addProduct(new Product(1, "Coca-Cola", 100, 1.5, 1)); // Soft Drink
        addProduct(new Product(2, "Pepsi", 120, 1.4, 1));     // Soft Drink
        addProduct(new Product(3, "Chocolate Cake", 50, 3.0, 2)); // Cake
        addProduct(new Product(4, "Cheesecake", 30, 3.5, 2));     // Cake
        addProduct(new Product(5, "Burger", 80, 4.5, 3));     // Fastfood
        addProduct(new Product(6, "French Fries", 100, 2.0, 3)); // Fastfood
        addProduct(new Product(7, "Heineken", 60, 2.5, 4));   // Beer
        addProduct(new Product(8, "Tiger Beer", 70, 2.3, 4)); // Beer
    }
}
