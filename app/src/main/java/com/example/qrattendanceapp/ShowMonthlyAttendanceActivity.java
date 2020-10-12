package com.example.qrattendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dmax.dialog.SpotsDialog;

public class ShowMonthlyAttendanceActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;

    ListView listView;
    String date;
    TextView datetxt;
    public static int smonth,sday,syear;
    private static Context context;

    public static String  attendancedate;
    public static int monthfirebase;

    FirebaseHelper helper;


   // ArrayList<Attendance> arrayList=new ArrayList<>();

    ArrayList<String> arrayList=new ArrayList<>();
    int days;
    static ArrayList<String> attendanceCount = new ArrayList<>();

    public static String monthS;
    AlertDialog waiting_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_monthly_attendance);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Show Monthly Attendance");
        setSupportActionBar(myToolbar);

        datetxt=findViewById(R.id.eText);
        ShowMonthlyAttendanceActivity.context = getApplicationContext();
        listView=findViewById(R.id.list);

        waiting_dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Loading")
                .setCancelable(false)
                .build();



        SelectDate();


    }

    public void SelectDate() {


        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        // date picker dialog
        datePickerDialog = new DatePickerDialog(ShowMonthlyAttendanceActivity.this,
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



    public void getMonthlyAttendance(View view) {
        waiting_dialog.show();


        helper = new FirebaseHelper();
        helper.getMonthlyStudents(new FirebaseHelper.studentCallMonthly() {

            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onSuccess(ArrayList<String> attendanceArrayList,ArrayList<String> count) {
                arrayList = attendanceArrayList;
attendanceCount=count;
            }
        });



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                waiting_dialog.dismiss();


//                LongOperation myTask=new LongOperation();
//                try {
//                    myTask.execute();
//                    Log.e("TAG", "run: execyuted");
//                } finally {
//                    Toast.makeText(getApplicationContext(), "Not", Toast.LENGTH_SHORT).show();
//
//                }
                MonthlyCustomAdapter monthlyCustomAdapter = new MonthlyCustomAdapter(getApplicationContext(), 0, arrayList);
                listView.setAdapter(monthlyCustomAdapter);



            }
        }, 5000);

    }


//    private final class LongOperation extends AsyncTask<int[], int[], int[]> {
//
//
//        @Override
//        protected void onPostExecute(int[] count1) {
//            arr=new double[FirebaseHelper.arrayListMonthlyAccess.size()];
//
//            int index=FirebaseHelper.arrayListMonthlyAccess.size();
//            int heldClassesCount=(int)FirebaseHelper.heldClasses;
//            int i=0;
//            while(index>0){
//                int count=attendClasses[i]/heldClassesCount;
//                count=count*100;
//                arr[i]=count;
//            }
//        }
//
//        @Override
//        protected int[] doInBackground(int[]... ints) {
//            attendClasses=new int[FirebaseHelper.arrayListMonthlyAccess.size()];
//            int i=0;
//            int index=FirebaseHelper.arrayListMonthlyAccess.size();
//            while (index>0)
//            {
//                attendClasses[i]=Collections.frequency(attendanceCount, attendanceCount.get(i));
//                index--;
//                i++;
//            }
//            return attendClasses;
//        }

}
