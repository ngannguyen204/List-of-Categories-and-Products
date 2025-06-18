package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k224111493practice.R;
import com.example.k224111493practice.SendSMSActivity;
import com.example.k224111493practice.TelephonyActivity;
import com.example.models.TelephonyInfor;

public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor> {

    Activity context;
    int resource;
    public TelephonyInforAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);
        TextView txtTelephonyInforName=item.findViewById(R.id.txtTelephonyInforName);
        TextView txtTelephonyInforPhone=item.findViewById(R.id.txtTelephonyInforPhone);
        TelephonyInfor ti=getItem(position);
        txtTelephonyInforName.setText(ti.getDisplayName());
        txtTelephonyInforPhone.setText(ti.getPhoneNumber());

        ImageView imgCallDirect=item.findViewById(R.id.imgCallDirect);
        imgCallDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TelephonyActivity) context).callDirect(ti);
            }
        });

        ImageView imgCallDil=item.findViewById(R.id.imgCallDialup);
        imgCallDil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TelephonyActivity) context).callDialup(ti);
            }
        });
        ImageView imgSms=item.findViewById(R.id.imgSms);
        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI",ti);
                context.startActivity(intent);

            }
        });
        return item;
    }
}
