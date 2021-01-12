package com.example.expensesapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {

    private static Month monthItem;
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;


    public static ActionBottomDialogFragment newInstance() {
        return new ActionBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.options_bottom_sheet_dialog_layout,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        view.setBackground(getResources().getDrawable(R.drawable.options_bottom_sheet_background));
//        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
        view.findViewById(R.id.ll_edit_month_name).setOnClickListener(this);
        view.findViewById(R.id.ll_remove_month).setOnClickListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + "must implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        LinearLayout llActionSelected = (LinearLayout) view;
        if(monthItem == null){
            throw new RuntimeException("monthItem is null");
        } else {
            if(llActionSelected == view.findViewById(R.id.ll_edit_month_name)) {
                mListener.onItemClickEditMonthName(monthItem);
            } else if (llActionSelected == view.findViewById(R.id.ll_remove_month)) {
                mListener.onItemClickDeleteMonth(monthItem);
            }
        }
    }

    public interface ItemClickListener {
        void onItemClickEditMonthName(Month month);
        void onItemClickDeleteMonth(Month month);
    }

    public static Month getMonthItem() {
        return monthItem;
    }

    public static void setMonthItem(Month monthItem) {
        ActionBottomDialogFragment.monthItem = monthItem;
    }
}

