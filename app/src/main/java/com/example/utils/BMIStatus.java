package com.example.utils;

public class BMIStatus {
    private double BMI;
    private String description;

    public BMIStatus() {
    } //đây là constructer không có đối số nào cả

    public BMIStatus(double BMI, String description) {
        this.BMI = BMI;
        this.description = description;
    } //đây là constructor có đối số

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
