package com.example.qrattendanceapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.Collections;
import java.util.List;

import static com.example.qrattendanceapp.ShowMonthlyAttendanceActivity.attendanceCount;


public class MonthlyCustomAdapter extends ArrayAdapter<String> {

    int[] attendClasses;
    public static double[] arr;
    public MonthlyCustomAdapter(Context context, int resource) {
        super(context, resource);
    }


    public MonthlyCustomAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) ;
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.monthlyshowstudents, null);
        }
        Log.e("TAG", "run: ok");


        Log.e("TAG", "run: RUNEED11");

        attendClasses=new int[FirebaseHelper.arrayListMonthlyAccess.size()];

        arr=new double[FirebaseHelper.arrayListMonthlyAccess.size()];

        int index=FirebaseHelper.arrayListMonthlyAccess.size();
        int heldClassesCount=(int)FirebaseHelper.heldClasses;
        int i=0;
        while(index>0){
            int count=attendClasses[i]/heldClassesCount;
            count=count*100;
            arr[i]=count;
            i++;
            index--;
        }

        i=0;
        index=FirebaseHelper.arrayListMonthlyAccess.size();
        while (index>0)
        {
            attendClasses[i]= Collections.frequency(attendanceCount, attendanceCount.get(i));
            index--;
            i++;
        }
        Log.e("TAG", "run: RUNEED");

        String attendance = getItem(position);
        if (attendance != null) {
            TextView id = view.findViewById(R.id.idtext);
            if (id != null) {
                 id.setText(attendance);

                    if(arr[position]<70){
                         id.setBackgroundColor(Color.parseColor("#d50000"));
                         Log.e("TAG", "run: done");
                     }

            }
        }
        return view;
    }
}