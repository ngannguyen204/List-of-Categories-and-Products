package com.example.k224111493practice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AsyncTaskActivity extends AppCompatActivity {
    EditText edtNumberButton;
    Button btnDrawButton;
    TextView txtPercent;
    ProgressBar progressBarPercent;
    LinearLayout linearLayoutPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_async_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnDrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawButton();
            }
        });
    }

    private void drawButton() {
    }

    private void addViews() {
        edtNumberButton=findViewById(R.id.edtNumberButton);
        btnDrawButton=findViewById(R.id.btnDrawButton);
        txtPercent=findViewById(R.id.txtPercent);
        progressBarPercent=findViewById(R.id.progressBarPercent);
        linearLayoutPercent=findViewById(R.id.linearLayoutButton);
    }

}