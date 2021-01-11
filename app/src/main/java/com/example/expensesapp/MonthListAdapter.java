package com.example.expensesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MonthListAdapter extends ArrayAdapter {

    public MonthListAdapter(Context context, int resource, ArrayList<Month> monthArrayList) {
        super(context, resource, monthArrayList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Month month = (Month) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.month_cell, parent, false);
        }

        TextView tvMonthName = convertView.findViewById(R.id.tv_month_name);
        TextView tvMonthlyIncome = convertView.findViewById(R.id.tv_monthly_income);
        TextView tvMonthlyExpenses = convertView.findViewById(R.id.tv_monthly_expenses);
//        Button btDeleteMonth = convertView.findViewById(R.id.bt_delete_month);

        if (!month.getName().equals("")) {
            tvMonthName.setText(month.getName());
        }
        tvMonthlyIncome.setText(month.getMonthlyIncome().toString());
        tvMonthlyExpenses.setText(month.getMonthlyExpenses().toString());

        /*btDeleteMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(getItem(position));
                notifyDataSetChanged();
            }
        });*/

        return convertView;
    }
}
