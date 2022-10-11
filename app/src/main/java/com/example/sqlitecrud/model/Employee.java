package com.example.sqlitecrud.model;

public class Employee {
    private String msv;
    private String ten;
    private int namSinh;

    public Employee() {
    }

    public Employee(String msv, String ten, int namSinh) {
        this.msv = msv;
        this.ten = ten;
        this.namSinh = namSinh;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
