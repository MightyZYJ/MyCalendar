package com.example.zhangyijun.mycalendar.ui.Main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.ui.List.RefreshList;

import java.util.Calendar;

/**
 * Created by 11234 on 2018/4/23.
 */
public class ClickMonth {

    public static void clickMonth(final Activity activity) {

        final Calendar today = Calendar.getInstance();

        TextView month = activity.findViewById(R.id.top_month);
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);

                        SlideScreen.slideScreen(activity, calendar);

                        calendar.set(year, month, dayOfMonth);

                        TextView textView;
                        for (int i = 0; i < 42; i++) {
                            textView = CreateCalendar.sTextViewArrayList.get(i);
                            if (textView.getText().toString().equals(String.valueOf(dayOfMonth))) {
                                SwitchDay.switchDay(activity, textView, calendar);
                            }
                        }

                    }
                }, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }
}
