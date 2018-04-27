package com.example.zhangyijun.mycalendar.ui.Main;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.ui.Schedule.ScheduleActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 11234 on 2018/4/4.
 * 这个类是关于主活动右下角的 + 号的
 * <p>
 * -用于为 + 号设置监听器
 * -点击后进入时间表ScheduleActivity中，并传递处理后的日期进去。
 * addmission方法用于MainActivity和CreateCalendar，传入一个被点击的日期，传出两个字符串给ScheduleActivity
 * <p>
 * store_date用于存放到数据库中 是标准 yyyy-MM-dd 的格式
 * show_date用与被Schedule活动呈现出来 是 MM月dd日 的格式
 */

public class BuildMission {

    public static void addMission(final Activity activity, final Calendar calendar) {
        //选中了其他日期后，将该日期传入。处理该日期，将String类型的日期传递给ScheduleActivity
        Button add_mission = activity.findViewById(R.id.add_mission);
        //找按钮，设监听
        add_mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String show_date = String.valueOf(calendar.get(Calendar.MONTH) + 1) + "月" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "日";
                //处理show_date
                Date date = calendar.getTime();
                String store_date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
                //处理store_date
                Intent intent = new Intent(activity, ScheduleActivity.class);
                intent.putExtra("show_date", show_date);
                intent.putExtra("store_date", store_date);
                //存值
                activity.startActivity(intent);
                //跳转到ScheduleActivity中
            }
        });
    }

}
