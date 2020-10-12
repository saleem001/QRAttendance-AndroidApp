package com.example.qrattendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrattendanceapp.Models.Attendance;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import dmax.dialog.SpotsDialog;

public class ShowAttendanceActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;

    ListView listView;
    String date;
    TextView datetxt;
    public static int smonth,sday,syear;
    private static Context context;
public static int monthfirebase;
    public static String  attendancedate;

    FirebaseHelper helper;

    ArrayList<Attendance> arrayList=new ArrayList<>();
    AlertDialog waiting_dialog;

    public static String monthS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Show Attendance");
        setSupportActionBar(myToolbar);

        datetxt=findViewById(R.id.eText);
        ShowAttendanceActivity.context = getApplicationContext();
        listView=findViewById(R.id.list);



        SelectDate();


        waiting_dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Loading")
                .setCancelable(false)
                .build();





    }

    public void SelectDate() {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        // date picker dialog
        datePickerDialog = new DatePickerDialog(ShowAttendanceActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datetxt.setText("Selected date is : " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        sday=dayOfMonth;
                        smonth=monthOfYear+1;
                        syear=year;
                        monthfirebase=monthOfYear+1;
                        attendancedate=sday+"-"+smonth+"-"+syear;
                        int m = monthOfYear + 1;

                        if(monthfirebase==1)
                        {
                            monthS="Jan";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("Jan"+syear).child(attendancedate);
                        }
                        else if(monthfirebase==2)
                        {
                            monthS="Feb";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("Feb"+syear).child(attendancedate);
                        }else  if(monthfirebase==3)
                        {
                            monthS="March";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("March"+syear).child(attendancedate);

                        }else  if(monthfirebase==4)
                        {
                            monthS="April";
                            //ref= FirebaseDatabase.getInstance().getReference("Attendance").child("April"+syear).child(attendancedate);

                        }else if(monthfirebase==5)
                        {
                            monthS="May";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("May"+syear).child(attendancedate);

                        }else if(monthfirebase==6)
                        {
                            monthS="June";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("June"+syear).child(attendancedate);

                        }else if(monthfirebase==7)
                        {
                            monthS="July";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("July"+syear).child(attendancedate);

                        }else if(monthfirebase==8)
                        {
                            monthS="August";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("August"+syear).child(attendancedate);

                        } else if(monthfirebase==9)
                        {
                            monthS="September";
                            // ref= FirebaseDatabase.getInstance().getReference("Attendance").child("September"+syear).child(attendancedate);

                        } else if(monthfirebase==10)
                        {
                            monthS="October";
                            //  ref= FirebaseDatabase.getInstance().getReference("Attendance").child("October"+syear).child(attendancedate);

                        } else if(monthfirebase==11)
                        {
                            monthS="November";
                            //  ref= FirebaseDatabase.getInstance().getReference("Attendance").child("November"+syear).child(attendancedate);
                        }else if(monthfirebase==12)
                        {
                            monthS="December";
                            //  ref= FirebaseDatabase.getInstance().getReference("Attendance").child("December"+syear).child(attendancedate);
                        }


                        date = dayOfMonth + "/" + m + "/" + year;




                        if (date.equals("")) {
                            Toast.makeText(getApplicationContext(), "Select Date", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, year, month, day);
        datePickerDialog.show();


    }

    public void getAttendance(View view) {
        waiting_dialog.show();

        helper = new FirebaseHelper();
        helper.getStudents(new FirebaseHelper.studentCallBack() {


            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onSuccess(ArrayList<Attendance> arrayList1) {
                arrayList = arrayList1;

            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                waiting_dialog.dismiss();

                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), 0, arrayList);
                listView.setAdapter(customAdapter);
            }
        }, 5000);

    }

    public static Context getAppContext() {
        return ShowAttendanceActivity.context;
    }
}
