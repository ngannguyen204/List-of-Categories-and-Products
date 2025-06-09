package com.example.k224111493practice;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapters.OrderDetailsViewerAdapter;
import com.example.connectors.OrderDetailsViewerConnector;
import com.example.connectors.SQLiteConnector;
import com.example.models.OrderDetailsViewer;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {
    ListView lvOrderDetails;
    OrderDetailsViewerAdapter adapter;
    TextView tvOrderInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        int orderId = getIntent().getIntExtra("orderId", -1);
        String orderInfo = getIntent().getStringExtra("orderInfo");

        tvOrderInfo = findViewById(R.id.tvOrderInfo);
        lvOrderDetails = findViewById(R.id.lvOrderDetails);

        tvOrderInfo.setText(orderInfo);

        SQLiteConnector connector = new SQLiteConnector(this);
        OrderDetailsViewerConnector odvc = new OrderDetailsViewerConnector();
        ArrayList<OrderDetailsViewer> dataset = odvc.getOrderDetailsByOrderId(connector.openDatabase(), orderId);

        adapter = new OrderDetailsViewerAdapter(this, R.layout.item_orderdetails, dataset);
        lvOrderDetails.setAdapter(adapter);
    }
}