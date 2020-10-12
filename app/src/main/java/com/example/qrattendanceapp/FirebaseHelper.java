package com.example.qrattendanceapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.qrattendanceapp.Models.Attendance;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirebaseHelper {

    DatabaseReference reference;

    ValueEventListener valueEventListener;
    ArrayList<Attendance> arrayList = new ArrayList<>();
    HashSet<String> arrayListMonthly = new HashSet<>();
    static HashSet<String> arrayListMonthlyAccess = new HashSet<>();
    int days;
    static ArrayList<String> attendanceCount = new ArrayList<>();
    static long heldClasses;

    public interface studentCallBack {
        void onSuccess(ArrayList<Attendance> attendanceArrayList);
        void onFailure(Exception e);
    }

    public interface studentCallMonthly {
        void onSuccess(ArrayList<String> attendanceArrayList,ArrayList<String> attendanceCount);
        void onFailure(Exception e);
    }
    public interface studentCallCount{
        void onSuccess(ArrayList<String> attendanceCount);
        void onFailure(Exception e);
    }



    public void getStudents(final studentCallBack callback) {
        String child=ShowAttendanceActivity.monthS+ShowAttendanceActivity.syear;

        reference = FirebaseDatabase.getInstance().getReference("Attendance").child(child).child(ShowAttendanceActivity.attendancedate);
        valueEventListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Attendance attendance1 = new Attendance();
                    attendance1.key = snapshot.getKey();
                    attendance1.id = snapshot.child("id").getValue().toString();
                    arrayList.add(attendance1);
                    callback.onSuccess(arrayList);
                    reference=null;
                    valueEventListener=null;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure(databaseError.toException());
            }
        });
    }



    public void countHeldClasses() {
        String child=ShowMonthlyAttendanceActivity.monthS+ShowMonthlyAttendanceActivity.syear;

        reference = FirebaseDatabase.getInstance().getReference("Attendance").child(child);
        valueEventListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    heldClasses = dataSnapshot.getChildrenCount();
                Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");


                reference = null;
                valueEventListener = null;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



    public void getMonthlyStudents(final studentCallMonthly callback) {

        countHeldClasses();

        String child=ShowMonthlyAttendanceActivity.monthS+ShowMonthlyAttendanceActivity.syear;
        final int month=ShowMonthlyAttendanceActivity.smonth;
        final int year=ShowMonthlyAttendanceActivity.syear;

        reference = FirebaseDatabase.getInstance().getReference("Attendance").child(child);
        valueEventListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.hasChild("1"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("1"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {

                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);
//
                    }
                    days++;
                }
                if(dataSnapshot.hasChild("2"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("2"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {

                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);
                    }
                    days++;
                }
                if(dataSnapshot.hasChild("3"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("3"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {

                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                }
                if(dataSnapshot.hasChild("4"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("4"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {

                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("5"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("5"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("6"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("6"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("7"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("7"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("8"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("8"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("9"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("9"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;
                } if(dataSnapshot.hasChild("10"+"-"+month+"-"+year)){
                    days++;
                    DataSnapshot dp=dataSnapshot.child("10"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }

                } if(dataSnapshot.hasChild("11"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("11"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("12"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("12"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("13"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("13"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;
                } if(dataSnapshot.hasChild("14"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("14"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);
                    }
                    days++;

                } if(dataSnapshot.hasChild("15"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("15"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("16"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("16"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        //arrayListMonthly.add(attendance1);
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("17"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("17"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                                                    arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("18"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("18"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("19"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("19"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("20"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("20"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("21"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("21"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("22"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("22"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("23"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("23"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("24"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("24"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;
                } if(dataSnapshot.hasChild("25"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("25"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("26"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("26"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        //arrayListMonthly.add(attendance1);

                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("27"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("27"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        arrayListMonthly.add(attendance1);
                        attendanceCount.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("28"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("28"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                    }
                    days++;

                } if(dataSnapshot.hasChild("29"+"-"+month+"-"+year)) {
                    DataSnapshot dp = dataSnapshot.child("29" + "-" + month + "-" + year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1 = snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                        arrayListMonthly.add(attendance1);

                        days++;

                    }
                }if(dataSnapshot.hasChild("30"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("30"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                            arrayListMonthly.add(attendance1);
                    }
                    days++;

                }if(dataSnapshot.hasChild("31"+"-"+month+"-"+year)){
                    DataSnapshot dp=dataSnapshot.child("31"+"-"+month+"-"+year);
                    for (DataSnapshot snapshot : dp.getChildren()) {
                        String attendance1=snapshot.child("id").getValue().toString();
                        attendanceCount.add(attendance1);
                            arrayListMonthly.add(attendance1);
                    }
                    days++;
                }
                ArrayList<String> monthlyList = new ArrayList<String>();
                monthlyList.addAll(arrayListMonthly);
                arrayListMonthlyAccess=arrayListMonthly;
                callback.onSuccess(monthlyList,attendanceCount);
                valueEventListener=null;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}