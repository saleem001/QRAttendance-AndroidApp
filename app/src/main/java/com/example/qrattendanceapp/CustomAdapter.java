package com.example.qrattendanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.qrattendanceapp.Models.Attendance;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Attendance> {


    public CustomAdapter(Context context, int resource) {
        super(context, resource);
    }



    public CustomAdapter(Context context, int resource, List<Attendance> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View view=convertView;
        if(view==null);
        {
            LayoutInflater vi;
            vi= LayoutInflater.from(getContext());
            view=vi.inflate(R.layout.showstudents,null);
        }
        Attendance attendance=getItem(position);
        if(attendance!=null)
        {
            TextView id=view.findViewById(R.id.idtext);
            TextView present=view.findViewById(R.id.presenttext);

//            SharedPreferences preferences;
//
//            preferences = ShowAttendanceActivity.getAppContext().getSharedPreferences("Attendancedate", Context.MODE_PRIVATE);
//            final String date = preferences.getString("date", "date not valid");



            if(id!=null)
            {
                id.setText(attendance.getId());
            }
            present.setText("Present");

        }
        return view;
    }
}
