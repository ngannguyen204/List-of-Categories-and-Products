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
    public void generate_sample_dataset(){
        addCustomer(new Customer(1,"Teo","teo@gmail.com","0987654","teo","123"));
        addCustomer(new Customer(2,"Ti","ti@gmail.com","0876543","ti","1234"));
        addCustomer(new Customer(3,"Tinh","tinh@gmail.com","09876541","tinh","12345"));
        addCustomer(new Customer(4,"Ngan","ngan@gmail.com","0678954","ngan","12356"));
        addCustomer(new Customer(5,"Mai","mai@gmail.com","098765412","mai","12354"));
        addCustomer(new Customer(6,"Van","van@gmail.com","098768765","van","12309"));
        addCustomer(new Customer(7,"Linh","linh@gmail.com","098765434","linh","12387"));
        addCustomer(new Customer(8,"Truc","truc@gmail.com","0987654","truc","1235"));
        addCustomer(new Customer(9,"Te","te@gmail.com","0987654","te","12398"));
        addCustomer(new Customer(10,"Nhu","nhu@gmail.com","098765487","nhu","12309"));
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
    public void getAllCustomers(SQLiteDatabase database){
        Cursor cursor = database.rawQuery("SELECT * FROM Customer",
                 null);
        while(cursor.moveToNext()){

        }    int id = cursor.getInt(0);
        String name= cursor.getString(1);
        String phone = cursor.getString(2);
        String email = cursor.getString(3);
        String username = cursor.getString(4);
        String password=cursor.getString(5);
        int saveInfor=cursor.getInt(6);
        Customer c =new Customer();
        c.setId(id);
        c.setName(name);
        c.setPhone(phone);
        c.setEmail(email);
        c.setUsername(username);
        c.setPassword(password);
        customers.add(c);
            cursor.close();

    }
}
