package com.example.models;

import java.util.ArrayList;

public class ListCategory {
    private ArrayList<Category> categories;
    public ListCategory(){
        categories= new ArrayList<>();
    }
    public void addCategory(Category c)
    {
        categories.add(c);
    }
    public void setCategories(ArrayList<Category> categories){
        this.categories=categories;
    }
    public void generate_sample_dataset(){
        addCategory(new Category(001,"Soft Drink"));
        addCategory(new Category(002,"Cake"));
        addCategory(new Category(003,"Fastfood"));
        addCategory(new Category(004,"Beer"));

    }
    public ArrayList<Category> getCategories() {
        return categories;
    }


}
