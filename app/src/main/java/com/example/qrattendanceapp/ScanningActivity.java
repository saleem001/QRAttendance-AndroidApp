package com.example.qrattendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScanningActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;

    public static int day,month,year;
    public static String monthS;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        day=MainActivity.sday;
        month=MainActivity.smonth;
        year=MainActivity.syear;

        String date=day+"-"+month+"-"+year;
        if(month==1)
        {
            monthS="Jan";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("Jan"+year).child(date);
        }
        else if(month==2)
        {
            monthS="Feb";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("Feb"+year).child(date);
        }else  if(month==3)
        {
            monthS="March";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("March"+year).child(date);

        }else  if(month==4)
        {
            monthS="April";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("April"+year).child(date);

        }else if(month==5)
        {
            monthS="May";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("May"+year).child(date);

        }else if(month==6)
        {
            monthS="June";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("June"+year).child(date);

        }else if(month==7)
        {
            monthS="July";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("July"+year).child(date);

        }else if(month==8)
        {
            monthS="August";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("August"+year).child(date);

        } else if(month==9)
        {
            monthS="September";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("September"+year).child(date);

        } else if(month==10)
        {
            monthS="October";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("October"+year).child(date);

        } else if(month==11)
        {
            monthS="November";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("November"+year).child(date);
        }else if(month==12)
        {
            monthS="December";
            ref= FirebaseDatabase.getInstance().getReference("Attendance").child("December"+year).child(date);
        }
        initViews();

   }
    private void initViews() {
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        surfaceView = findViewById(R.id.surfaceView);
        btnAction = findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (intentData.length() > 0) {

                    Map<String, String> users = new HashMap<>();
                    users.put("id",txtBarcodeValue.getText().toString());
                    ref.push().setValue(users);

                    Toast.makeText(ScanningActivity.this, "Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initialiseDetectorsAndSources() {

        Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScanningActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScanningActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    txtBarcodeValue.post(new Runnable() {
                        @Override
                        public void run() {

                            if (barcodes.valueAt(0).email != null) {
                                txtBarcodeValue.removeCallbacks(null);
                                intentData = barcodes.valueAt(0).email.address;
                                txtBarcodeValue.setText(intentData);
                                isEmail = true;
                                btnAction.setText("ADD CONTENT TO THE MAIL");
                            } else {
                                isEmail = false;
                                btnAction.setText("Save");
                                intentData = barcodes.valueAt(0).displayValue;
                                txtBarcodeValue.setText(intentData);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }
}

