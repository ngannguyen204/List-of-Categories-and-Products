package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class ListEmployee {
    private List<Employee> employees;

    public ListEmployee() {
        employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void generate_sample_dataset() {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("Ngan");
        e1.setEmail("ngan@gmail.com");
        e1.setUsername("ngan");
        e1.setPassword("0987");
        e1.setPosition("Manager");
        e1.setSalary(5000.00);
        employees.add(e1);

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("Nhu");
        e2.setEmail("nhu@gmail.com");
        e2.setUsername("nhu");
        e2.setPassword("6543");
        e2.setPosition("Developer");
        e2.setSalary(4000.00);
        employees.add(e2);

        Employee e3 = new Employee();
        e3.setId(3);
        e3.setName("Truc");
        e3.setEmail("truc@gmail.com");
        e3.setUsername("truc");
        e3.setPassword("1234");
        e3.setPosition("Designer");
        e3.setSalary(3500.00);
        employees.add(e3);

        Employee e4 = new Employee();
        e4.setId(4);
        e4.setName("Mai");
        e4.setEmail("mai@gmail.com");
        e4.setUsername("mai");
        e4.setPassword("3456");
        e4.setPosition("HR");
        e4.setSalary(3800.00);
        employees.add(e4);

        Employee e5 = new Employee();
        e5.setId(5);
        e5.setName("Mai");
        e5.setEmail("mai@gmail.com");
        e5.setUsername("mai");
        e5.setPassword("3456");
        e5.setPosition("HR");
        e5.setSalary(3800.00);
        employees.add(e5);

        Employee e6 = new Employee();
        e6.setId(6);
        e6.setName("Binh");
        e6.setEmail("binh@gmail.com");
        e6.setUsername("binh");
        e6.setPassword("2222");
        e6.setPosition("Accountant");
        e6.setSalary(3700.00);
        employees.add(e6);

        Employee e7 = new Employee();
        e7.setId(7);
        e7.setName("Chau");
        e7.setEmail("chau@gmail.com");
        e7.setUsername("chau");
        e7.setPassword("3333");
        e7.setPosition("Developer");
        e7.setSalary(3900.00);
        employees.add(e7);

        Employee e8 = new Employee();
        e8.setId(8);
        e8.setName("Dang");
        e8.setEmail("dang@gmail.com");
        e8.setUsername("dang");
        e8.setPassword("4444");
        e8.setPosition("Tester");
        e8.setSalary(3650.00);
        employees.add(e8);

        Employee e9 = new Employee();
        e9.setId(9);
        e9.setName("Duc");
        e9.setEmail("duc@gmail.com");
        e9.setUsername("duc");
        e9.setPassword("5555");
        e9.setPosition("Manager");
        e9.setSalary(4800.00);
        employees.add(e9);

        Employee e10 = new Employee();
        e10.setId(10);
        e10.setName("Giang");
        e10.setEmail("giang@gmail.com");
        e10.setUsername("giang");
        e10.setPassword("6666");
        e10.setPosition("Designer");
        e10.setSalary(3500.00);
        employees.add(e10);

        Employee e11 = new Employee();
        e11.setId(11);
        e11.setName("Ha");
        e11.setEmail("ha@gmail.com");
        e11.setUsername("ha");
        e11.setPassword("7777");
        e11.setPosition("HR");
        e11.setSalary(3700.00);
        employees.add(e11);

        Employee e12 = new Employee();
        e12.setId(12);
        e12.setName("Hung");
        e12.setEmail("hung@gmail.com");
        e12.setUsername("hung");
        e12.setPassword("8888");
        e12.setPosition("Developer");
        e12.setSalary(4100.00);
        employees.add(e12);

        Employee e13 = new Employee();
        e13.setId(13);
        e13.setName("Khanh");
        e13.setEmail("khanh@gmail.com");
        e13.setUsername("khanh");
        e13.setPassword("9999");
        e13.setPosition("Accountant");
        e13.setSalary(3750.00);
        employees.add(e13);

        Employee e14 = new Employee();
        e14.setId(14);
        e14.setName("Lan");
        e14.setEmail("lan@gmail.com");
        e14.setUsername("lan");
        e14.setPassword("0000");
        e14.setPosition("Tester");
        e14.setSalary(3600.00);
        employees.add(e14);

        Employee e15 = new Employee();
        e15.setId(15);
        e15.setName("Linh");
        e15.setEmail("linh@gmail.com");
        e15.setUsername("linh");
        e15.setPassword("1110");
        e15.setPosition("HR");
        e15.setSalary(3850.00);
        employees.add(e15);

        Employee e16 = new Employee();
        e16.setId(16);
        e16.setName("Long");
        e16.setEmail("long@gmail.com");
        e16.setUsername("long");
        e16.setPassword("1212");
        e16.setPosition("Developer");
        e16.setSalary(4000.00);
        employees.add(e16);

        Employee e17 = new Employee();
        e17.setId(17);
        e17.setName("My");
        e17.setEmail("my@gmail.com");
        e17.setUsername("my");
        e17.setPassword("1313");
        e17.setPosition("Designer");
        e17.setSalary(3600.00);
        employees.add(e17);

        Employee e18 = new Employee();
        e18.setId(18);
        e18.setName("Nam");
        e18.setEmail("nam@gmail.com");
        e18.setUsername("nam");
        e18.setPassword("1414");
        e18.setPosition("Developer");
        e18.setSalary(4200.00);
        employees.add(e18);

        Employee e19 = new Employee();
        e19.setId(19);
        e19.setName("Ngoc");
        e19.setEmail("ngoc@gmail.com");
        e19.setUsername("ngoc");
        e19.setPassword("1515");
        e19.setPosition("Accountant");
        e19.setSalary(3700.00);
        employees.add(e19);

        Employee e20 = new Employee();
        e20.setId(20);
        e20.setName("Phuc");
        e20.setEmail("phuc@gmail.com");
        e20.setUsername("phuc");
        e20.setPassword("1616");
        e20.setPosition("Tester");
        e20.setSalary(3650.00);
        employees.add(e20);

        Employee e21 = new Employee();
        e21.setId(21);
        e21.setName("Quang");
        e21.setEmail("quang@gmail.com");
        e21.setUsername("quang");
        e21.setPassword("1717");
        e21.setPosition("Manager");
        e21.setSalary(4900.00);
        employees.add(e21);

        Employee e22 = new Employee();
        e22.setId(22);
        e22.setName("Quyen");
        e22.setEmail("quyen@gmail.com");
        e22.setUsername("quyen");
        e22.setPassword("1818");
        e22.setPosition("HR");
        e22.setSalary(3750.00);
        employees.add(e22);

        Employee e23 = new Employee();
        e23.setId(23);
        e23.setName("Son");
        e23.setEmail("son@gmail.com");
        e23.setUsername("son");
        e23.setPassword("1919");
        e23.setPosition("Developer");
        e23.setSalary(4150.00);
        employees.add(e23);

        Employee e24 = new Employee();
        e24.setId(24);
        e24.setName("Tai");
        e24.setEmail("tai@gmail.com");
        e24.setUsername("tai");
        e24.setPassword("2020");
        e24.setPosition("Accountant");
        e24.setSalary(3800.00);
        employees.add(e24);

        Employee e25 = new Employee();
        e25.setId(25);
        e25.setName("Tam");
        e25.setEmail("tam@gmail.com");
        e25.setUsername("tam");
        e25.setPassword("2121");
        e25.setPosition("Tester");
        e25.setSalary(3650.00);
        employees.add(e25);

    }


}