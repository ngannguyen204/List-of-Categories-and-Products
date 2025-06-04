package com.example.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ListPaymentMethod {
    ArrayList<PaymentMethod> methods = new ArrayList<>();

    public ArrayList<PaymentMethod> getMethods() {
        return methods;
    }

    public void getAllPaymentMethods(SQLiteDatabase db) {
        methods.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            PaymentMethod pm = new PaymentMethod(id, name, description);
            methods.add(pm);
        }
        cursor.close();
    }
}
