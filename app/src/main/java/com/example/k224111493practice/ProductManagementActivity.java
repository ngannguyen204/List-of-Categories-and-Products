package com.example.k224111493practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Category;
import com.example.models.Customer;
import com.example.models.ListCategory;
import com.example.models.ListCustomer;
import com.example.models.ListProduct;
import com.example.models.Product;

public class ProductManagementActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    ArrayAdapter<Category> adapterCategory;

    ListCategory listCategory;
    ListProduct lc = new ListProduct();

    ListView lvProduct;
    ArrayAdapter<Product> adapterProduct;
    MenuItem menu_new_product;
    MenuItem menu_search_product;
    MenuItem menu_help_product;

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
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category c=adapterCategory.getItem(position);
                displayProductByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = (Product) parent.getItemAtPosition(position);
                Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
                intent.putExtra("product_id", selectedProduct.getId());
                startActivity(intent);
            }
        });

    }

    private void openProductDetailActivity(Product product) {
        Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
        intent.putExtra("SELECTED_PRODUCT", product);
        startActivity(intent);
    }

    private void displayProductByCategory(Category c) {
        //xóa dữ liệu danh sách sp cũ
        adapterProduct.clear();
        //thêm dl mới vào
        adapterProduct.addAll(c.getProducts());

    }



    private void addViews() {
        spinnerCategory=findViewById(R.id.spinnerCategory);
        adapterCategory= new ArrayAdapter<>(ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory= new ListCategory();
        listCategory.generate_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());


        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct= new ArrayAdapter<>(ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);

        menu_new_product = findViewById(R.id.menu_new_product);
        menu_search_product = findViewById(R.id.menu_search_product);
        menu_help_product = findViewById(R.id.menu_help_product);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_new_product) {
            Toast.makeText(ProductManagementActivity.this, "Open add new product screen", Toast.LENGTH_SHORT).show();
            // openNewProductActivity();
        }
        else if (item.getItemId() == R.id.menu_search_product) {
            Toast.makeText(ProductManagementActivity.this, "Search product", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_help_product) {
            Toast.makeText(ProductManagementActivity.this, "Help for product management", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
