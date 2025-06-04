package com.example.k224111493practice;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapters.PaymentMethodAdapter;
import com.example.models.ListPaymentMethod;
import com.example.models.PaymentMethod;
import com.example.k224111493practice.R;

import java.util.ArrayList;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    ArrayList<PaymentMethod> methods;
    PaymentMethodAdapter adapter;
    SQLiteDatabase database;
    String DATABASE_NAME = "SalesDatabase.sql";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        ListPaymentMethod list = new ListPaymentMethod();
        list.getAllPaymentMethods(database);
        methods = list.getMethods();

        adapter = new PaymentMethodAdapter(this, methods);
        lvPaymentMethod.setAdapter(adapter);
    }
}
