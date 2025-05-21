package com.example.k224111493practice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    EditText edtProductId, edtProductName, edtQuality, edtPrice, edtCategoryId;
    ImageView imgProduct;
    Button btnSave, btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        displayProductDetail();
        addEvents();
    }

    private void addViews() {
        edtProductId = findViewById(R.id.edtProductId);
        edtProductName = findViewById(R.id.edtProductName);
        edtQuality = findViewById(R.id.edtQuality);
        edtPrice = findViewById(R.id.edtPrice);
        edtCategoryId = findViewById(R.id.edtCategoryId);
        imgProduct = findViewById(R.id.imgProduct);
        btnSave = findViewById(R.id.btnSave);
        btnRemove = findViewById(R.id.btnRemove);
    }

    private void displayProductDetail() {
        Product product = (Product) getIntent().getSerializableExtra("SELECTED_PRODUCT");

        edtProductId.setText(product.getId() + "");
        edtProductName.setText(product.getName());
        edtQuality.setText(product.getQuality() + "");
        edtPrice.setText(product.getPrice() + "");
        edtCategoryId.setText(product.getCateid() + "");
        // imgProduct.setImageResource(product.getImage_id());
    }

    private void addEvents() {
        btnSave.setOnClickListener(v -> {
            Toast.makeText(this, "Save product information", Toast.LENGTH_SHORT).show();
            // Implement save logic here
        });

        btnRemove.setOnClickListener(v -> {
            Toast.makeText(this, "Remove product", Toast.LENGTH_SHORT).show();
            // Implement remove logic here
        });
    }
}