package com.example.expensesapp;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;

public class MonthListActivity extends AppCompatActivity {
    private ArrayList<Month> monthArrayList;
    private FloatingActionButton fbtAddMonth;
    private ListView lvMonthList;
    private MonthListAdapter monthListAdapter;
    private Dialog addItemDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_group_activity);

        setupActivity();


    }

    private void setupActivity() {
        loadData();
        setupLayout();
        setupFloatingActionButton();

        setupDialog();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("month list", null);
        Type type = new TypeToken<ArrayList<Month>>() {}.getType();
        monthArrayList = gson.fromJson(json, type);

        if(monthArrayList == null) {
            monthArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(monthArrayList);
        editor.putString("month list", json);
        editor.apply();
    }

    private void setupLayout() {
        lvMonthList = findViewById(R.id.lv_month_list);
        monthListAdapter = new MonthListAdapter(getApplicationContext(), 0, monthArrayList);

        lvMonthList.setAdapter(monthListAdapter);
        setupLvItemLongClick();
        LinearLayout ll = findViewById(R.id.options_bottom_sheet_layout_container);
        fbtAddMonth = findViewById(R.id.fbt_add_month);
    }

    private void setupLvItemLongClick() {
        lvMonthList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            /*TODO: implement on item long click listener to display bottom
            *  sheet dialog with edit month name and delete month
            *
            * */

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }

    private void setupFloatingActionButton() {
        fbtAddMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void setupDialog() {
        addItemDialog = new Dialog(MonthListActivity.this);
        addItemDialog.setContentView(R.layout.add_item_dialog_layout);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addItemDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.add_item_dialog_background));
        }

        EditText etMonthName;
        Button addButton, cancelButton;

        etMonthName = addItemDialog.findViewById(R.id.et_list_item);
        addButton = addItemDialog.findViewById(R.id.bt_add_month);
        cancelButton = addItemDialog.findViewById(R.id.bt_cancel);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthName = etMonthName.getText().toString();
                if(!monthName.isEmpty()) {
                    addListItem(monthName);
                }
                etMonthName.setText("");
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMonthName.setText("");
                addItemDialog.dismiss();
            }
        });
    }

    private void addListItem(String monthName) {
        monthArrayList.add(new Month(monthName));
        monthListAdapter.notifyDataSetChanged();
        saveData();
    }

    private void showDialog() {
        addItemDialog.show();
        Objects.requireNonNull(addItemDialog.getWindow())
                .setSoftInputMode(SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}

