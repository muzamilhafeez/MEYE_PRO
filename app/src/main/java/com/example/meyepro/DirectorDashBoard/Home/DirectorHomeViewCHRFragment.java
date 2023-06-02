package com.example.meyepro.DirectorDashBoard.Home;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.FragmentDirectorHomeViewCHRBinding;
import com.example.meyepro.fragments.Admin.AdminViewFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class DirectorHomeViewCHRFragment extends Fragment {
    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 500;

    // creating a bitmap variable
    // for storing our images

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;
    FragmentDirectorHomeViewCHRBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentDirectorHomeViewCHRBinding.inflate(getLayoutInflater());
        //code start
//        Toast.makeText(getContext(), ""+getArguments().getString("IntentData"), Toast.LENGTH_SHORT).show();
        ScheduleDetailsAndCHR scheduleDetailsAndCHR= new Gson().fromJson(getArguments().getString("IntentData"),ScheduleDetailsAndCHR.class);
        binding.txtCourse.setText(scheduleDetailsAndCHR.getCourseName());
        binding.txtDate.setText(scheduleDetailsAndCHR.getDate());
        binding.txtDiscipline.setText(scheduleDetailsAndCHR.getDiscipline());
        binding.txtViewTeacherName.setText(scheduleDetailsAndCHR.getTeacherName());
        binding.txtDay.setText(scheduleDetailsAndCHR.getDay());
        binding.txtStatus.setText(scheduleDetailsAndCHR.getStatus());
        binding.txtTime.setText(scheduleDetailsAndCHR.getStartTime().split("\\.")[0]+"-"+scheduleDetailsAndCHR.getEndTime().split("\\.")[0]);
//        Toast.makeText(getContext(), ""+scheduleDetailsAndCHR.getTotalTimeIn(), Toast.LENGTH_SHORT).show();
        binding.txtTimeIn.setText(""+scheduleDetailsAndCHR.getTotalTimeIn());
        binding.txtTimeOut.setText(""+scheduleDetailsAndCHR.getTotalTimeOut());

        if(!scheduleDetailsAndCHR.getImage().isEmpty()){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Teacher"+"/"+scheduleDetailsAndCHR.getImage())).
                    into(binding.profileImageTeacher);
        }

        binding.btnGeneratePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
//                    Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    requestPermission();
                }
                // calling method to
                // generate our PDF file.
                // Generate the PDF
                binding.btnGeneratePDF.setVisibility(View.INVISIBLE);
//        PdfGenerator pdfGenerator= new PdfGenerator();
//        pdfGenerator.generatePdf(TeacherSelectTimTableCHRActivity.this, binding.getRoot(), "TeacherCHR.pdf");
                generatePDF();

//        // Generate the PDF
//        PdfGeneratorWithText.generatePdf(getApplication(), binding.getRoot(), "kk.pdf");
//
//        // Copy the text to the clipboard
//        String text = getTextFromView(binding.txtDate);
//        PdfGeneratorWithText.copyTextToClipboard(getApplicationContext(), text);
                binding.btnGeneratePDF.setVisibility(View.VISIBLE);
            }
        });

        //code end
        return binding.getRoot();
    }


    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getActivity().getSupportFragmentManager().beginTransaction();
        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frament_container_Director, f);
        //   ft.addToBackStack(null);
        ft.commit();
    }

    private void generatePDF() {



        // initializing our variables.
//        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_dvr_camera_setting);
//        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

// creating a variable for canvas
// from our page of PDF.
        Canvas canvas = myPage.getCanvas();

// Get the drawable from the ImageView
        BitmapDrawable bmpDrawable = (BitmapDrawable) binding.profileImageTeacher.getDrawable();
        Bitmap bmpImage = bmpDrawable.getBitmap();

        // Calculate the desired resolution based on the zoom level
        float zoomLevel = 2.0f; // Adjust the zoom level as needed
        int desiredWidth = (int) (50* zoomLevel);
        int desiredHeight = (int) (50 * zoomLevel);

// Create a new bitmap with the desired resolution
        Bitmap highResBitmap = Bitmap.createScaledBitmap(bmpImage, desiredWidth, desiredHeight, true);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        // Calculate the x coordinate to align the image to the right
        int x = pagewidth-140;
// Draw the high-resolution bitmap onto the PDF canvas
        canvas.drawBitmap(highResBitmap, x, 20, paint);



        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        title.setFlags(title.getFlags() | Paint.UNDERLINE_TEXT_FLAG);
        canvas.drawText(binding.txtViewTeacherName.getText().toString(), 209, 70, title);
//        canvas.drawText("MEYE Pro", 209, 80, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));
        title.setTextSize(15);

        Paint paintTxt= new Paint();

        canvas.drawText("Discipline: "+binding.txtDiscipline.getText(), 40, 160, paintTxt);
        canvas.drawText("Date: "+binding.txtDate.getText(), 20+(pagewidth/2), 160, paintTxt);

        canvas.drawText("Course: "+binding.txtCourse.getText(), 40, 185, paintTxt);
        canvas.drawText("Day: "+binding.txtDay.getText(), 20+(pagewidth/2), 185, paintTxt);

        canvas.drawText("Time In: "+binding.txtTimeIn.getText(), 40, 210, paintTxt);
        canvas.drawText("Time out: "+binding.txtTimeOut.getText(), 20+(pagewidth/2), 210, paintTxt);

        canvas.drawText("Time: "+binding.txtTime.getText(), 40, 235, paintTxt);
        canvas.drawText("Status: "+binding.txtStatus.getText(), 20+(pagewidth/2), 235, paintTxt);


        Paint linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.blue_500)); // Set the desired color for the lines
        linePaint.setStrokeWidth(1); // Set the desired width for the lines

// Draw the lines on the canvas
        float startX = 5; // Starting X coordinate for the lines
        float startY = 260; // Starting Y coordinate for the lines
        float endX = canvas.getWidth(); // Ending X coordinate for the lines
        float endY = canvas.getHeight(); // Ending Y coordinate for the lines

        canvas.drawLines(new float[]{startX, startY, endX, startY, startX, endY, endX, endY}, linePaint);
        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
//
//        }
//        else
//        {
//
//
//        }
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                // The app has been granted the MANAGE_EXTERNAL_STORAGE permission
                File file = new File(String.valueOf(Environment.getExternalStorageDirectory()), "muzamil.pdf");
                try {
                    // after creating a file name we will
                    // write our PDF file to that location.
                    pdfDocument.writeTo(new FileOutputStream(file));

                    // below line is to print toast message
                    // on completion of PDF generation.
                    Toast.makeText(getContext(), "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // below line is used
                    // to handle error
                    e.printStackTrace();
                    binding.txtCourse.setText(e.toString());
                    Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
                // The app has not been granted the MANAGE_EXTERNAL_STORAGE permission
                // Request the permission from the user
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                startActivity(intent);
            }
        }



        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
//                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }


}