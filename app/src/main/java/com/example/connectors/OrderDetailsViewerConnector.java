package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.OrderDetailsViewer;
import java.util.ArrayList;

public class OrderDetailsViewerConnector {
    public ArrayList<OrderDetailsViewer> getOrderDetailsByOrderId(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetailsViewer> datasets = new ArrayList<>();

        String sql = "SELECT Product.Name, OrderDetails.Price, OrderDetails.Quantity, " +
                "OrderDetails.Discount, OrderDetails.VAT, " +
                "ROUND((OrderDetails.Price * OrderDetails.Quantity * (1 - OrderDetails.Discount) * (1 + OrderDetails.VAT)), 2) AS LineTotal " +
                "FROM OrderDetails " +
                "JOIN Product ON OrderDetails.ProductId = Product.Id " +
                "WHERE OrderDetails.OrderId = ?";

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(orderId)});

        while (cursor.moveToNext()) {
            OrderDetailsViewer odv = new OrderDetailsViewer();
            odv.setProductName(cursor.getString(0));
            odv.setPrice(cursor.getDouble(1));
            odv.setQuantity(cursor.getInt(2));
            odv.setDiscount(cursor.getDouble(3));
            odv.setVat(cursor.getDouble(4));
            odv.setLineTotal(cursor.getDouble(5));

            datasets.add(odv);
        }

        cursor.close();
        return datasets;
    }
}