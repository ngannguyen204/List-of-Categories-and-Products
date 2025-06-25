package com.example.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ListCustomer {
    private ArrayList<Customer> customers;


   public ListCustomer(){
       customers= new ArrayList<>();
   }
    public void addCustomer(Customer c)
    {
        customers.add(c);
    }
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public void setCustomers(ArrayList<Customer> customers){
        this.customers=customers;
    }

    public  boolean isExisting(Customer c)
    {
        for(Customer cus: customers)
        {
            if (cus.getId()==c.getId() ||
                cus.getPhone().equals(c.getPhone())||
                cus.getEmail().equalsIgnoreCase(c.getEmail())||
                        cus.getUsername().equalsIgnoreCase(c.getUsername())
                )
                return true;
        }
        return false;
    }
    public void getAllCustomers(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("SELECT * FROM Customer",
                null);
        customers.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            String username = cursor.getString(4);
            String password = cursor.getString(5);


            Customer c = new Customer();
            c.setId(id);
            c.setName(name);
            c.setPhone(phone);
            c.setEmail(email);
            c.setUsername(username);
            c.setPassword(password);

            addCustomer(c);
        }
        cursor.close();
    }
    }
