package com.example.k224111493practice;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SMSActivity extends AppCompatActivity {
    private static final String TAG = "SMSActivity";
    private ListView lvSMS;
    private ArrayAdapter<String> smsAdapter;
    private List<String> smsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsactivity);

        initViews();
        setupAdapter();
        loadSMSMessages();
    }

    private void initViews() {
        lvSMS = findViewById(R.id.lvSMS);
        smsList = new ArrayList<>();
    }

    private void setupAdapter() {
        smsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                smsList);
        lvSMS.setAdapter(smsAdapter);
    }

    private void loadSMSMessages() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference smsRef = database.getReference("sms_messages");

        smsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                smsList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String sender = snapshot.child("sender").getValue(String.class);
                    String recipient = snapshot.child("recipient").getValue(String.class);
                    String message = snapshot.child("message").getValue(String.class);
                    Long timestamp = snapshot.child("time").getValue(Long.class);
                    String status = snapshot.child("status").getValue(String.class);

                    // Format thời gian
                    String timeStr = "Unknown";
                    if (timestamp != null) {
                        timeStr = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                                .format(new Date(timestamp));
                    }

                    // Tạo chuỗi hiển thị
                    String smsEntry = String.format(Locale.getDefault(),
                            "[%s]\n%s → %s\n%s\n%s",
                            status.toUpperCase(),
                            sender,
                            recipient,
                            message,
                            timeStr);

                    smsList.add(smsEntry);

                    Log.d(TAG, "Processed SMS: " + smsEntry);
                }

                smsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read SMS data.", databaseError.toException());
            }
        });
    }
}