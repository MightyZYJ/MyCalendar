package com.example.zhangyijun.mycalendar.ui.Main;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.db.DataToArrayList;
import com.example.zhangyijun.mycalendar.db.ScheduleData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by 11234 on 2018/4/24.
 */
public class PrintRed {

    public static TextView printRed(Activity activity, Calendar calendar, TextView textView) {

        String store_date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(calendar.getTime());
        DataToArrayList dataToArrayList = new DataToArrayList();
        ArrayList<ScheduleData> list = dataToArrayList.getlist(activity, store_date);

        boolean isEmergency = false;
        if (list.size() > 0) {
            for(int i = 0 ; i < list.size() ; i++){
                ScheduleData scheduleData = list.get(i);
                if (scheduleData.getIsEmergency() == 1){
                    isEmergency = true;
                }
            }
            if (isEmergency){
                textView.setTextColor(activity.getResources().getColor(R.color.red));
            }else{
                textView.setTextColor(activity.getResources().getColor(R.color.bule));
            }

        }else{
            textView.setTextColor(activity.getResources().getColor(R.color.black_overlay));
        }

        return textView;
    }
}
