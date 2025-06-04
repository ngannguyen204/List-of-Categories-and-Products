package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.k224111493practice.R;
import com.example.models.PaymentMethod;

import java.util.ArrayList;

public class PaymentMethodAdapter extends BaseAdapter {
    Context context;
    ArrayList<PaymentMethod> methods;

    public PaymentMethodAdapter(Context context, ArrayList<PaymentMethod> methods) {
        this.context = context;
        this.methods = methods;
    }

    @Override
    public int getCount() {
        return methods.size();
    }

    @Override
    public Object getItem(int position) {
        return methods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return methods.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_payment_method, parent, false);

        TextView txtName = convertView.findViewById(R.id.txtPaymentMethodName);
        TextView txtDescription = convertView.findViewById(R.id.txtPaymentMethodDescription);

        PaymentMethod pm = methods.get(position);
        txtName.setText(pm.getName());
        txtDescription.setText(pm.getDescription());

        return convertView;
    }
}
