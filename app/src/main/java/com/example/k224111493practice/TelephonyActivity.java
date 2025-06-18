package com.example.k224111493practice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.Manifest;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.TelephonyInforAdapter;
import com.example.models.TelephonyInfor;

import java.util.ArrayList;
import java.util.List;

public class TelephonyActivity extends AppCompatActivity {
    ListView lvTelephonyinfor;
    TelephonyInforAdapter adapter;
    List<TelephonyInfor> allContacts = new ArrayList<>();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_telephony);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        addViews();
//        readAllContacts();
//    }
//
//    private void addViews() {
//        lvTelephonyinfor = findViewById(R.id.lvTelephonyinfor);
//        adapter = new TelephonyInforAdapter(TelephonyActivity.this, R.layout.item_telephonyinfor);
//        lvTelephonyinfor.setAdapter(adapter);
//    }
//
//    private void readAllContacts() {
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        adapter.clear();
//
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//                String name = cursor.getString(nameIndex);
//                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//                String phone = cursor.getString(phoneIndex);
//
//                TelephonyInfor ti = new TelephonyInfor();
//                ti.setDisplayName(name);
//                ti.setPhoneNumber(phone);
//                adapter.add(ti);
//            }
//            cursor.close();
//        }
//    }
//
//    public void sendSms(TelephonyInfor ti, String content) {
//        final SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(ti.getPhoneNumber(), null, content, null, null);
//        Toast.makeText(TelephonyActivity.this, "Đã gửi tin nhắn tới " + ti.getPhoneNumber(),
//                Toast.LENGTH_LONG).show();
//    }
//
//    @SuppressLint("UnspecifiedRegisterReceiverFlag")
//    public void sendSmsPendingIntent(TelephonyInfor ti, String content) {
//        final SmsManager sms = SmsManager.getDefault();
//        Intent msgSent = new Intent("ACTION_MSG_SENT");
//
//        final PendingIntent pendingMsgSent =
//                PendingIntent.getBroadcast(this, 0, msgSent, PendingIntent.FLAG_IMMUTABLE);
//
//        registerReceiver(new BroadcastReceiver() {
//            public void onReceive(Context context, Intent intent) {
//                int result = getResultCode();
//                String msg = (result == Activity.RESULT_OK) ? "Send OK" : "Send failed";
//                Toast.makeText(TelephonyActivity.this, msg, Toast.LENGTH_LONG).show();
//            }
//        }, new IntentFilter("ACTION_MSG_SENT"));
//
//        sms.sendTextMessage(ti.getPhoneNumber(), null, content, pendingMsgSent, null);
//    }
//
//    public void callDirect(TelephonyInfor ti) {
//        Uri uri = Uri.parse("tel:" + ti.getPhoneNumber());
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(uri);
//        startActivity(intent);
//    }
//
//    public void callDialup(TelephonyInfor ti) {
//        Uri uri = Uri.parse("tel:" + ti.getPhoneNumber());
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(uri);
//        startActivity(intent);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);

        // Check and request permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    1);
        } else {
            // Permission already granted, proceed with setup
            setupAfterPermission();
        }
    }

    private void setupAfterPermission() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        readAllContacts();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupAfterPermission();
            } else {
                Toast.makeText(this, "Permission denied - cannot read contacts", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.telephony_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_all) {
            showAllContacts();
            return true;
        } else if (id == R.id.menu_viettel) {
            filterContactsByCarrier("Viettel");
            return true;
        } else if (id == R.id.menu_mobifone) {
            filterContactsByCarrier("MobiFone");
            return true;
        } else if (id == R.id.menu_vinaphone) {
            filterContactsByCarrier("Vinaphone");
            return true;
        } else if (id == R.id.menu_other) {
            filterContactsByCarrier("Other");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAllContacts() {
        adapter.clear();
        adapter.addAll(allContacts);
        Toast.makeText(this, "Showing all contacts", Toast.LENGTH_SHORT).show();
    }

    private void filterContactsByCarrier(String carrier) {
        List<TelephonyInfor> filteredList = new ArrayList<>();
        for (TelephonyInfor contact : allContacts) {
            String contactCarrier = contact.getCarrier();
            if (contactCarrier.equals(carrier)) {
                filteredList.add(contact);
            }
        }
        adapter.clear();
        adapter.addAll(filteredList);

        // Show more detailed info in the toast
        String message = "Showing " + carrier + " contacts: " + filteredList.size() + "\n";
        for (TelephonyInfor contact : filteredList) {
            message += contact.getDisplayName() + ": " + contact.getPhoneNumber() + "\n";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void addViews() {
        lvTelephonyinfor = findViewById(R.id.lvTelephonyinfor);
        adapter = new TelephonyInforAdapter(TelephonyActivity.this, R.layout.item_telephonyinfor);
        lvTelephonyinfor.setAdapter(adapter);
    }

    private void readAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        allContacts.clear();
        adapter.clear();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phone = cursor.getString(phoneIndex);

                TelephonyInfor ti = new TelephonyInfor();
                ti.setDisplayName(name);
                ti.setPhoneNumber(phone);
                adapter.add(ti);
                allContacts.add(ti);
            }
            cursor.close();
        }
    }

    public void sendSms(TelephonyInfor ti, String content) {
        final SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(ti.getPhoneNumber(), null, content, null, null);
        Toast.makeText(TelephonyActivity.this, "Đã gửi tin nhắn tới " + ti.getPhoneNumber(),
                Toast.LENGTH_LONG).show();
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public void sendSmsPendingIntent(TelephonyInfor ti, String content) {
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");

        final PendingIntent pendingMsgSent =
                PendingIntent.getBroadcast(this, 0, msgSent, PendingIntent.FLAG_IMMUTABLE);

        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = (result == Activity.RESULT_OK) ? "Send OK" : "Send failed";
                Toast.makeText(TelephonyActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));

        sms.sendTextMessage(ti.getPhoneNumber(), null, content, pendingMsgSent, null);
    }

    public void callDirect(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void callDialup(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }
}
