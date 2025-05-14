package com.example.connectors;

import com.example.models.Employee;
import com.example.models.ListEmployee;

public class EmployeeConnector {
    public Employee login(String usr,String pwd)
    {
        ListEmployee le=new ListEmployee();
        le.generate_sample_dataset();
        for(Employee e : le.getEmployees())
        {
            if(e.getUsername().equalsIgnoreCase(usr) && e.getPassword().equals(pwd))
            {
                return e;
            }
        }
        return null;
    }
}
