package com.example.k224111493practice;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Customer;
import com.example.models.ListCustomer;

public class CustomerManagementActivity extends AppCompatActivity {
    ListView lvCustomer;
    ArrayAdapter<Customer> adapter;
    ListCustomer lc=new ListCustomer();

    MenuItem menu_new_customer;
    MenuItem menu_broadcast_advertising;
    MenuItem menu_help;

    final int ID_CREATE_NEW_CUSTOMER=1;
    final int ID_UPDATE_CUSTOMER=2;

    String DATABASE_NAME="SalesDatabase.sql";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer c=adapter.getItem(position);
                openCustomerDetailActivity(c);
            }
        });
    }

    private void openCustomerDetailActivity(Customer c) {
        Intent intent = new Intent(CustomerManagementActivity.this, CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER",c);
        startActivity(intent);

    }

    private void addViews() {
        lvCustomer=findViewById(R.id.lvCustomer);
        adapter=new ArrayAdapter<>(
                CustomerManagementActivity.this,
                android.R.layout.simple_list_item_1);

       // lc.generate_sample_dataset();
        database=openOrCreateDatabase(DATABASE_NAME,
                MODE_PRIVATE,null);
        lc.getAllCustomers(database);
        adapter.addAll(lc.getCustomers());

        lvCustomer.setAdapter(adapter);

        menu_new_customer=findViewById(R.id.menu_new_customer);
        menu_broadcast_advertising=findViewById(R.id.menu_broadcast_advertising);
        menu_help=findViewById(R.id.menu_help);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_new_customer)
        {
            Toast.makeText(CustomerManagementActivity.this, "Open add new customer screen", Toast.LENGTH_SHORT).show();
            openNewCustomerActivity();
        }
        else if (item.equals(menu_broadcast_advertising)) {
            Toast.makeText(CustomerManagementActivity.this, "Broadcasting advertisement to all customers", Toast.LENGTH_SHORT).show();

        }
        else if (item.equals(menu_help)) {
            Toast.makeText(CustomerManagementActivity.this, "Open for help", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void openNewCustomerActivity() {
        Intent intent = new Intent(CustomerManagementActivity.this, CustomerDetailActivity.class);
        startActivityForResult(intent,ID_CREATE_NEW_CUSTOMER);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ID_CREATE_NEW_CUSTOMER && resultCode==1000)
        {
            //lấy kết quả ra:
            Customer c= (Customer) data.getSerializableExtra("NEW_CUSTOMER");
            //tới đây có 2 tình huống: Mới or Cập nhật?
            process_save_customer(c);

        }
    }
    private  void process_save_customer(Customer c)
    {
        boolean result=lc.isExisting(c);
        if(result==true)//đã tồn tại cus này rồi
            return;//không thêm mới
        //còn muốn cập nhật thì viết code cập nhật
        //các mã lệnh dưới đây là thêm mới Customer:
        lc.addCustomer(c);
        adapter.clear();
        adapter.addAll(lc.getCustomers());
    }
}