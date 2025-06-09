package com.example.k224111493practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.OrdersViewerAdapter;
import com.example.connectors.OrdersViewerConnector;
import com.example.connectors.SQLiteConnector;
import com.example.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerActivity extends AppCompatActivity {
    ListView lvOrdersViewer;
    OrdersViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addViews() {
        lvOrdersViewer = findViewById(R.id.lvOrdersViewer);

        SQLiteConnector connector = new SQLiteConnector(this);
        OrdersViewerConnector ovc = new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset = ovc.getAllOrdersViewers(connector.openDatabase());

        adapter = new OrdersViewerAdapter(this, R.layout.item_ordersviewer, dataset);
        lvOrdersViewer.setAdapter(adapter);
    }

    private void addEvents() {
        lvOrdersViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrdersViewer selectedOrder = adapter.getItem(position);
                if (selectedOrder != null) {
                    String orderInfo = String.format("Order #%s - %s\nCustomer: %s\nTotal: $%.2f",
                            selectedOrder.getCode(),
                            selectedOrder.getOrderDate(),
                            selectedOrder.getCustomerName(),
                            selectedOrder.getFinalTotal());

                    Intent intent = new Intent(OrdersViewerActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("orderId", selectedOrder.getId());
                    intent.putExtra("orderInfo", orderInfo);
                    startActivity(intent);
                }
            }
        });
    }
}