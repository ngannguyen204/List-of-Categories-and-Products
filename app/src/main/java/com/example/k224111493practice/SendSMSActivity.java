package com.example.k224111493practice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.TelephonyInfor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SendSMSActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_REQUEST_CODE = 101;
    TextView txtPhoneNumber;
    EditText edtBody;
    ImageView imgSendSms;
    TelephonyInfor ti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_smsactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addViews() {
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        edtBody = findViewById(R.id.edtBody);
        imgSendSms = findViewById(R.id.imgSendSMS);

        Intent intent = getIntent();
        ti = (TelephonyInfor) intent.getSerializableExtra("TI");
        if (ti != null) {
            txtPhoneNumber.setText(ti.getPhoneNumber() + "(" + ti.getDisplayName() + ")");
        }
    }

    private void addEvents() {
        imgSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ti != null && edtBody.getText() != null) {
                    if (checkSmsPermission()) {
                        sendSms(ti, edtBody.getText().toString());
                    } else {
                        requestSmsPermission();
                    }
                } else {
                    Toast.makeText(SendSMSActivity.this,
                            "Lỗi: Không có thông tin người nhận hoặc nội dung",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkSmsPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestSmsPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS},
                SMS_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ti != null && edtBody.getText() != null) {
                    sendSms(ti, edtBody.getText().toString());
                }
            } else {
                Toast.makeText(this,
                        "Permission denied - cannot send SMS",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sendSms(TelephonyInfor ti, String content) {
        try {
            final SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(ti.getPhoneNumber(), null, content, null, null);

            // Save to Firebase
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference smsRef = database.getReference("sms_messages");

            String messageId = smsRef.push().getKey();
            HashMap<String, Object> smsData = new HashMap<>();
            smsData.put("sender", "You");
            smsData.put("recipient", ti.getPhoneNumber());
            smsData.put("message", content);
            smsData.put("time", System.currentTimeMillis());
            smsData.put("status", "sent");

            smsRef.child(messageId).setValue(smsData)
                    .addOnSuccessListener(aVoid -> {
                        Log.d("SMS", "Đã lưu tin nhắn gửi đi");
                        Toast.makeText(SendSMSActivity.this,
                                "Đã gửi tin nhắn tới " + ti.getPhoneNumber(),
                                Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Log.e("SMS", "Lỗi khi lưu tin nhắn", e);
                        Toast.makeText(SendSMSActivity.this,
                                "Lỗi khi lưu tin nhắn: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    });

        } catch (Exception e) {
            Toast.makeText(this, "Lỗi khi gửi SMS: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            Log.e("SMS", "Gửi SMS thất bại", e);
        }
    }
}