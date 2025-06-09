package com.example.models;

public class OrdersViewer extends Order {
    //shift F6 sửa tên file
    private String customerName;
    private String employeeName;
    private double GrossTotal;
    private double TotalDiscount;
    private double TotalVAT;
    private double FinalTotal;



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getGrossTotal() {
        return GrossTotal;
    }

    public void setGrossTotal(double grossTotal) {
        GrossTotal = grossTotal;
    }

    public double getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public double getTotalVAT() {
        return TotalVAT;
    }

    public void setTotalVAT(double totalVAT) {
        TotalVAT = totalVAT;
    }

    public double getFinalTotal() {
        return FinalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        FinalTotal = finalTotal;
    }
}
