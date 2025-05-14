package com.example.k224111493practice;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Category;
import com.example.models.Customer;
import com.example.models.ListCategory;
import com.example.models.ListCustomer;

public class CategoryManagementActivity extends AppCompatActivity {
    ListView lvCategory;
    ArrayAdapter<Category> adapter;
    ListCategory lc=new ListCategory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addviews();
    }

    private void addviews() {
        lvCategory=findViewById(R.id.lvCategory);
        adapter= new ArrayAdapter<>(
                CategoryManagementActivity.this,
                android.R.layout.simple_list_item_1
        );
        lc.generate_sample_dataset();
        adapter.addAll(lc.getCategories());
        lvCategory.setAdapter(adapter);
    }
}