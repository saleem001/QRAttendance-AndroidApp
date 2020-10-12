package com.example.qrattendanceapp;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    String semesterSpinnerItem,courseSpinnerItem;

    DatePickerDialog datePickerDialog;

    MaterialButton materialButton;

     TextView datetxt,barCodeText;

     public static int smonth,sday,syear;
    String date;

    public static final int REQUEST_CODE=100;
    public static final int PERMISSION_REQUEST=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Mark Attendance");
        setSupportActionBar(myToolbar);


        final Spinner semesterspinner = findViewById(R.id.semesterSpinner);
        final Spinner courseSpinner=findViewById(R.id.courseSpinner);
        datetxt=findViewById(R.id.eText);
        barCodeText=findViewById(R.id.barcodeResult);

        materialButton=findViewById(R.id.ScanButton);

        ArrayAdapter<CharSequence> semesterAdapter = ArrayAdapter.createFromResource(this, R.array.semesterList,
                android.R.layout.simple_spinner_item);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterspinner.setAdapter(semesterAdapter);
        semesterSpinnerItem="Semester 1";
        semesterspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(position==0) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester1,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                   else if(position==1) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester2,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else if(position==2) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester3,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else if(position==3) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester4,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else if(position==4) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester5,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }else if(position==5) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester6,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }else if(position==6) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester7,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }else if(position==7) {
                        ArrayAdapter<CharSequence> Courseadapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Semester8,
                                android.R.layout.simple_spinner_item);
                        Courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        courseSpinner.setAdapter(Courseadapter);
                        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                courseSpinnerItem=parent.getItemAtPosition(position).toString();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ScanningActivity.class);
                //startActivityForResult(intent,REQUEST_CODE);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && requestCode==RESULT_OK){
            if(data!=null){
               final Barcode barcode=data.getParcelableExtra("barcode");
                barCodeText.post(new Runnable() {
                    @Override
                    public void run() {
                        barCodeText.setText(barcode.displayValue);
                    }
                });
            }
        }
    }

    public void SelectDate(View view) {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datetxt.setText("Selected date is : " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        sday=dayOfMonth;
                        smonth=monthOfYear+1;
                        syear=year;
                        int m = monthOfYear + 1;
                        date = dayOfMonth + "/" + m + "/" + year;
                        if (date.equals("") && courseSpinnerItem.equals("") && semesterSpinnerItem.equals("")) {
                            Toast.makeText(getApplicationContext(), "Fill all data", Toast.LENGTH_SHORT).show();
                        } else {
                            materialButton.setVisibility(View.VISIBLE);
                        }
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

}