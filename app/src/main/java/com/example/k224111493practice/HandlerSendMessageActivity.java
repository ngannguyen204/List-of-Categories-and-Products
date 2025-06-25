package com.example.k224111493practice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class HandlerSendMessageActivity extends AppCompatActivity {
    EditText edtNumber;
    Button btnDraw;
    TextView txtPercent;
    ProgressBar progressBarPercent;
    LinearLayout linearLayoutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_handler_send_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDrawButtonThreading();
            }
        });
    }


    int numberOfButton=0;
    Handler handler;
    private void processDrawButtonThreading() {
        //không được phép truy xuất bất kỳ biến control (biến giao diện: các view, layout) nào trong tiểu trình,
        // chỉ được truy xuát cập nhật thời gian thực trong MAIN
        numberOfButton=Integer.parseInt(edtNumber.getText().toString());
        //khóa nút Draw ể tránh bấm nhiều lần khi các tiểu trình cha chưa xong
        //vì mỗi lẫn bấm draw nó sẽ tạo ra một tiêểu trình mới, và các tiểu trình chạy độc lập
        // chạy xem kẽ nhau lung tung khó kiểm soát đc giao diện
        btnDraw.setEnabled(false);
        linearLayoutButton.removeAllViews();
        createHandlerObject();
        
        runThread();
    }

    private void runThread() {
        Random random=new Random();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=numberOfButton; i++){
                    Message message = handler.obtainMessage();
                    message.arg1 =  i*100/numberOfButton; //Percent
                    message.obj = random.nextInt(100);
                    handler.sendMessage(message);
                    SystemClock.sleep(100); //
                }
            }
        });
        thread.start();

    }

    private void createHandlerObject() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                int percent = message.arg1;
                if(percent == 100){ //không phải lúc nào cx đạt mức tối đa 100
                    Toast.makeText(HandlerSendMessageActivity.this, "DONE",
                            Toast.LENGTH_LONG).show();
                    //mở lại Draw ể thử nghiệm tiểu trình khác
                    btnDraw.setEnabled(true);
                }else{
                    int value = (int) message.obj;
                    //UPDATE UI …
                    Button btn=new Button(HandlerSendMessageActivity.this);
                    btn.setText(String.valueOf(value));
                    btn.setHeight(50);
                    btn.setWidth(300);
                    linearLayoutButton.addView(btn);
                }
                txtPercent.setText(percent + " %");
                progressBarPercent.setProgress(percent);
                return true;
            }
        });



    }

    private void addViews() {
        edtNumber=findViewById(R.id.edtNumber);
        btnDraw=findViewById(R.id.btnDraw);
        txtPercent=findViewById(R.id.txtPercent);
        progressBarPercent=findViewById(R.id.progressBarPercent);
        linearLayoutButton=findViewById(R.id.linearLayoutButton);
    }
}