package com.example.zhangyijun.mycalendar.ui.Main;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zhangyijun.mycalendar.db.DataToArrayList;
import com.example.zhangyijun.mycalendar.R;
import com.example.zhangyijun.mycalendar.https.GetDataFromHttp;
import com.example.zhangyijun.mycalendar.ui.List.ClickList;
import com.example.zhangyijun.mycalendar.ui.List.RefreshList;
import com.example.zhangyijun.mycalendar.ui.List.SortList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar calendar = Calendar.getInstance();                                           //实例化日期

        GetDataFromHttp getDataFromHttp = new GetDataFromHttp(this, calendar);              //农历/节日事件
        getDataFromHttp.sendRequestWithHttpConnrction();

        ChangeTop.changeTop(this, calendar);                                                //创建日历的头顶部分

        CreateCalendar.createCalender(this, calendar);                                      //创建日历的内容部分

        BuildMission.addMission(this, Calendar.getInstance());                              //传入今天的日期，为 + 号的点击事件做准备

        String type = getSharedPreferences("sort_type", MODE_PRIVATE).getString("default_type", "");
        if (!type.isEmpty()) {                                                                      //读取默认排序方式
            DataToArrayList.sSortType = type;
            SortList.sort(this, Calendar.getInstance());                                    //列表排序部分
        } else {
            SortList.sort(this, Calendar.getInstance());
        }

        RefreshList.refreshList(this, Calendar.getInstance());                              //刷新任务列表部分

        ClickList.clickList(this, Calendar.getInstance());                                  //任务列表点击部分

        ClickMonth.clickMonth(MainActivity.this);                                           //月份点击部分

        LinearLayout toucher = findViewById(R.id.toucher);
        toucher.setOnTouchListener(new View.OnTouchListener() {                                     //左右滑动手势翻页----------
            double PastX, NowX;

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        PastX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        NowX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (NowX - PastX > 0 && NowX - PastX > 40) {                                //向右滑动，月份减一

                            calendar.add(Calendar.MONTH, -1);
                            SlideScreen.slideScreen(MainActivity.this, calendar);

                        } else if (NowX - PastX < 0 && NowX - PastX < -40) {                        //向左滑动，月份加一

                            calendar.add(Calendar.MONTH, 1);
                            SlideScreen.slideScreen(MainActivity.this, calendar);
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });                                                                                         //-----------------------
    }

    public static String mDate;
    public static boolean mIsEdited = false;

    @Override
    protected void onRestart() {                                                                    //编辑后刷新列表
        super.onRestart();
        if (mIsEdited) {
            RefreshList.refreshList(this, mDate);
            MainActivity.mIsEdited = false;

            /*try {
                @SuppressLint("SimpleDateFormat") Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(mDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                CreateCalendar.createCalender(this,calendar);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/


        }
    }

    @Override
    public void onBackPressed() {                                                                   //返回键杀掉程序
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}