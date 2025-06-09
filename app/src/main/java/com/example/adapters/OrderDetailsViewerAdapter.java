package com.example.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k224111493practice.R;
import com.example.models.OrderDetailsViewer;

import java.util.List;

public class OrderDetailsViewerAdapter extends ArrayAdapter<OrderDetailsViewer> {

    Activity context;
    int resource;

    public OrderDetailsViewerAdapter(@NonNull Activity context, int resource, @NonNull List<OrderDetailsViewer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView txtProductName = item.findViewById(R.id.txtProductName);
        TextView txtPrice = item.findViewById(R.id.txtPrice);
        TextView txtQuantity = item.findViewById(R.id.txtQuantity);
        TextView txtDiscount = item.findViewById(R.id.txtDiscount);
        TextView txtVAT = item.findViewById(R.id.txtVAT);
        TextView txtLineTotal = item.findViewById(R.id.txtLineTotal);

        OrderDetailsViewer odv = getItem(position);
        if (odv != null) {
            txtProductName.setText(odv.getProductName());
            txtPrice.setText(String.format("Price: $%.2f", odv.getPrice()));
            txtQuantity.setText(String.format("Qty: %d", odv.getQuantity()));
            txtDiscount.setText(String.format("Discount: %.0f%%", odv.getDiscount() * 100));
            txtVAT.setText(String.format("VAT: %.0f%%", odv.getVat() * 100));
            txtLineTotal.setText(String.format("Total: $%.2f", odv.getLineTotal()));
        }

        return item;
    }
}