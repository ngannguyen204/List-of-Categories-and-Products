package com.example.k224111493practice;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Customer;
import com.example.models.ListCustomer;
import com.example.models.ListProduct;
import com.example.models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    ListView lvProduct;
    ArrayAdapter<Product> adapter;
    ListProduct lc=new ListProduct();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvProduct=findViewById(R.id.lvProduct);
        adapter=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);

        lc.generate_sample_dataset();
        adapter.addAll(lc.getProducts());
        lvProduct.setAdapter(adapter);

    }
}