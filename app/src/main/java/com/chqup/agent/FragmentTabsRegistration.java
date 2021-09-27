package com.chqup.agent;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.google.android.material.textfield.TextInputEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class FragmentTabsRegistration extends Fragment {
    private String[] array_reg_type;
    TextInputEditText regi_type, dob;

    public FragmentTabsRegistration() {
    }

    public static FragmentTabsRegistration newInstance() {
        FragmentTabsRegistration fragment = new FragmentTabsRegistration();
        return fragment;
    }

    private void showStateChoiceDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setSingleChoiceItems(array_reg_type, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                regi_type.setTextColor(Color.BLACK);
                regi_type.setText(array_reg_type[which]);
            }
        });
        builder.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tabs_scan_registration, container, false);
        regi_type = root.findViewById(R.id.recharge_type);
        dob = root.findViewById(R.id.dob);
        array_reg_type = getResources().getStringArray(R.array.regi_type);
        regi_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStateChoiceDialog();
            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cur_calender = Calendar.getInstance();
                DatePickerDialog datePicker = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                long date_ship_millis = calendar.getTimeInMillis();
                                dob.setText(Tools.getFormattedDateSimple(date_ship_millis));

                            }
                        },
                        cur_calender.get(Calendar.YEAR),
                        cur_calender.get(Calendar.MONTH),
                        cur_calender.get(Calendar.DAY_OF_MONTH)
                );
                //set dark light
                datePicker.setThemeDark(false);
                datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
                datePicker.setMinDate(cur_calender);
                datePicker.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });
        return root;
    }


}