package com.example.k224111493practice;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.TelephonyInfor;

public class SendSMSActivity extends AppCompatActivity {
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
       // addViews();
        addEvents();
    }

    private void addEvents() {
        imgSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms(ti,edtBody.getText().toString());
            }
        });

    }

    public void sendSms(TelephonyInfor ti, String content) {
        final SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(ti.getPhoneNumber(), null, content, null, null);
        Toast.makeText(SendSMSActivity.this, "Đã gửi tin nhắn tới " + ti.getPhoneNumber(),
                Toast.LENGTH_LONG).show();
    }

//    private void addViews() {
//        txtPhoneNumber=findViewById(R.id.txtPhoneNumber);
//        edtBody=findViewById(R.id.edtBody);
//        imgSendSms=findViewById(R.id.imgSendSMS);
//        Intent intent=getIntent();
//        ti=(Telephony) intent.getSerializableExtra("TI");
//        txtPhoneNumber.setText(ti.getPhoneNumber()+"("+ ti.getDisplayName()+")");
//    }
}