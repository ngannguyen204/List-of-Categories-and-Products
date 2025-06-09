package com.example.models;

public class OrderDetailsViewer {
    private String productName;
    private double price;
    private int quantity;
    private double discount;
    private double vat;
    private double lineTotal;

    public OrderDetailsViewer() {
    }

    public OrderDetailsViewer(String productName, double price, int quantity, double discount, double vat, double lineTotal) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.vat = vat;
        this.lineTotal = lineTotal;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }
}
