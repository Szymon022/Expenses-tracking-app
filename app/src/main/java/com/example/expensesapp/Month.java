package com.example.expensesapp;

import java.util.ArrayList;

public class Month {
    private int id;
    private String name;
    private boolean isSettled;
    private Float monthlyExpenses;
    private Float monthlyIncome;
    private ArrayList<Receipt> receiptArrayList;

    public Month() {
        this.name = "";
        this.monthlyExpenses = this.monthlyIncome = 0.0f;
        this.receiptArrayList = new ArrayList<>();
    }

    public Month(String name) {
        this.name = name;
        this.monthlyExpenses = this.monthlyIncome = 0.0f;
        this.receiptArrayList = new ArrayList<>();
    }

    public void addReceipt() {}
    public void deleteReceipt() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSettled() {
        return isSettled;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    public Float getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(Float monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public Float getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public ArrayList<Receipt> getReceiptArrayList() {
        return receiptArrayList;
    }

    public void setReceiptArrayList(ArrayList<Receipt> receiptArrayList) {
        this.receiptArrayList = receiptArrayList;
    }
}
