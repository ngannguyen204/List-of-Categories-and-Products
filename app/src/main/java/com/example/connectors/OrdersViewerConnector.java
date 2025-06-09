package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.OrdersViewer;
import java.util.ArrayList;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer> getAllOrdersViewers(SQLiteDatabase database) {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(" Orders.Id AS OrderId, ");
        sqlBuilder.append(" Orders.Code AS OrderCode, ");
        sqlBuilder.append(" Customer.Name AS CustomerName, ");
        sqlBuilder.append(" Employee.Name AS EmployeeName, ");
        sqlBuilder.append(" Orders.OrderDate, ");

        sqlBuilder.append(" ROUND(SUM(OrderDetails.Price * OrderDetails.Quantity), 2) AS GrossTotal, ");
        sqlBuilder.append(" ROUND(SUM(OrderDetails.Price * OrderDetails.Quantity * OrderDetails.Discount), 2) AS TotalDiscount, ");
        sqlBuilder.append(" ROUND(SUM((OrderDetails.Price * OrderDetails.Quantity * (1 - OrderDetails.Discount)) * OrderDetails.VAT), 2) AS TotalVAT, ");
        sqlBuilder.append(" ROUND(SUM((OrderDetails.Price * OrderDetails.Quantity * (1 - OrderDetails.Discount)) * (1 + OrderDetails.VAT)), 2) AS FinalTotal ");

        sqlBuilder.append(" FROM Orders ");
        sqlBuilder.append(" JOIN Customer ON Orders.CustomerId = Customer.Id ");
        sqlBuilder.append(" JOIN Employee ON Orders.EmployeeId = Employee.Id ");
        sqlBuilder.append(" JOIN OrderDetails ON Orders.Id = OrderDetails.OrderId ");

        sqlBuilder.append(" GROUP BY Orders.Id;");

        String sql = sqlBuilder.toString();
        Cursor cursor = database.rawQuery(sql, null);

        ArrayList<OrdersViewer> datasets = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String orderCode = cursor.getString(1);
            String customerName = cursor.getString(2);
            String employeeName = cursor.getString(3);
            String orderDate = cursor.getString(4);
            double grossTotal = cursor.getDouble(5);
            double totalDiscount = cursor.getDouble(6);
            double totalVAT = cursor.getDouble(7);
            double finalTotal = cursor.getDouble(8);

            OrdersViewer ov = new OrdersViewer();
            ov.setId(id);
            ov.setCode(orderCode);
            ov.setCustomerName(customerName);
            ov.setEmployeeName(employeeName);
            ov.setOrderDate(orderDate);
            ov.setGrossTotal(grossTotal);
            ov.setTotalDiscount(totalDiscount);
            ov.setTotalVAT(totalVAT);
            ov.setFinalTotal(finalTotal);

            datasets.add(ov);
        }

        cursor.close();
        return datasets;
    }
}
