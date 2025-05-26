package com.example.k224111493practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Customer;

public class CustomerDetailActivity extends AppCompatActivity {
    EditText edt_customer_id;
    EditText edt_customer_name;
    EditText edt_customer_email;
    EditText edt_customer_phone;
    EditText edt_customer_username;
    EditText edt_customer_password;
    Button btnNew;
    Button btnSave;
    Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_new();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_save();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_remove();
            }
        });
    }

    private void do_remove() {
    }

    private void do_save() {
        //khởi tạo đối tượng từ giao dện nhập của customer
        Customer c= new Customer();
        c.setId(Integer.parseInt(edt_customer_id.getText().toString()));
        c.setName(edt_customer_name.getText().toString());
        c.setEmail(edt_customer_email.getText().toString());
        c.setPhone(edt_customer_phone.getText().toString());
        c.setUsername(edt_customer_username.getText().toString());
        c.setPassword(edt_customer_password.getText().toString());
//lấy intent tiwf màn hình gọi nó để dùng
        Intent intent=getIntent();
        //đóng gói Customer vào intent
        intent.putExtra("NEW_CUSTOMER",c);
        //đóng dấu để gửi dữ liệu về:
        setResult(1000,intent);
        //sau đó bắt buộc phải đóng màn hình này lại
        //vì điện thoại ko cho phép cùng 1 lúc tại 1 vị trí có 2 màn hình
        //mặc dù đt có chức năng chia màn hinnh thaành nheieuf phần
        //để hiển thị nhiều phần mềm.
        finish();
    }

    private void do_new() {
    }

    private void addViews() {
        edt_customer_id=findViewById(R.id.edt_customer_id);
        edt_customer_name=findViewById(R.id.edt_customer_name);
        edt_customer_email=findViewById(R.id.edt_customer_email);
        edt_customer_phone=findViewById(R.id.edt_customer_phone);
        edt_customer_username=findViewById(R.id.edt_customer_username);
        edt_customer_password=findViewById(R.id.edt_customer_password);
        btnNew=findViewById(R.id.btnNew);
        btnSave=findViewById(R.id.btnSave);
        btnRemove=findViewById(R.id.btnRemove);
        display_customer_detail();

    }

    private void display_customer_detail() {
        //lấy intent từ customer management gửi tới
        Intent intent=getIntent();
        //Lấy customer liên quan biến mà ta đặt trong intent
        Customer c=(Customer) intent.getSerializableExtra("SELECTED_CUSTOMER");
        if(c==null)
            return;
         edt_customer_id.setText(c.getId()+"");
         edt_customer_name.setText(c.getName());
         edt_customer_email.setText(c.getEmail());
         edt_customer_phone.setText(c.getPhone());
         edt_customer_username.setText(c.getUsername());
         edt_customer_password.setText(c.getPassword());
    }
}