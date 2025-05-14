package com.example.k224111493practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.utils.BMIStatus;
import com.example.utils.HealthCare;

public class EmployeeHealthcareActivity extends AppCompatActivity {

    EditText edtHeight;
    EditText edtWeight;
    Button btnCalculate;
    Button btnClear;
    Button btnClose;
    TextView txtResult;
    View.OnClickListener myClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_healthcare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }
    private void addViews() {
        txtResult=findViewById(R.id.txtResult);
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);
        btnCalculate=findViewById(R.id.btnCalculate);
        btnClear=findViewById(R.id.btnClear);
        btnClose=findViewById(R.id.btnClose);

    }

    private void addEvents() {
        myClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xử lý suwj kiện
                if(v.equals(btnCalculate))
                {
                    //nút Calculate đc dùng sự kiện
                    double h=Double.parseDouble(edtHeight.getText().toString());
                    double w=Double.parseDouble(edtWeight.getText().toString());
                    BMIStatus bmiStatus= HealthCare.calcualte_bmi(h,w);
                    txtResult.setText(bmiStatus.getBMI()+"=>"+bmiStatus.getDescription());
                }
                else if(v.equals(btnClear))
                {
                    //nút Clear đang dùng sự kiện
                    edtWeight.setText("");
                    edtHeight.setText("");
                    txtResult.setText("");
                    edtHeight.requestFocus();

                }
                else if(v.equals(btnClose))
                {
                    //nút Close đang dùng sự kiện
                    finish();
                    //không dùng System.exit : này là tắt luôn
                }
                //nguyên phần if else này là để báo là nút nào đang dùng sự kiện,
                // phải chỉ ra ai(view) nào (trường hợp này là button nào) đang dùng cái myClick này
            }
        };
        //gán chia sẻ sự kiện
        btnCalculate.setOnClickListener(myClick);
        btnClear.setOnClickListener(myClick);
        btnClose.setOnClickListener(myClick);
        //myClick được chia sẻ dùng nhiều lần

    }

}