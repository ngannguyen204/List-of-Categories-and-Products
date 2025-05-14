package com.example.utils;

public class HealthCare {
    public static BMIStatus calcualte_bmi(double height, double weight)
    {
     double BMI=weight/Math.pow(height/100,2);
     String des="";
     if(BMI<18.5)
     {
         des="Underweight";
     }
     else if(BMI<23)
     {
         des="Normal weight";
     }
     else
     {
         des="Overwweight";
     }
     BMIStatus bmiStatus=new BMIStatus(BMI,des);
     return bmiStatus;
    }
}
