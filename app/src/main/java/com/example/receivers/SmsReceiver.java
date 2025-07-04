package com.example.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //  ta sẽ ra lệnh cho SmsReceiver chỉ lắng nghe
        //khi có gói tin là SMS gửi tới
        //và xử lý đọc nôi dung sau (hack nội dung)
        Bundle bundle=intent.getExtras();
        Object[] arrMessage =(Object[]) bundle.get("pdus");
        String phone, time, content;
        Date date;
        byte[] bytes;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for(int i = 0; i<arrMessage.length; i++)
        {
            bytes = (byte[]) arrMessage[1];
            SmsMessage message =SmsMessage.createFromPdu(bytes);
            phone=message.getDisplayOriginatingAddress();
            content=message.getMessageBody();
            date=new Date(message.getTimestampMillis());
            time=sdf.format(date);
            String infor=phone+"\n"+content+"\n"+time;
            Toast.makeText(context,infor,Toast.LENGTH_LONG).show();
        }

    }
}
