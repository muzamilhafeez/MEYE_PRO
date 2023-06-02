package com.example.meyepro.TeacherDashBoard.CHR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.R;
import com.example.meyepro.TeacherDashBoard.PDFGenerate.PdfGenerator;
import com.example.meyepro.TeacherDashBoard.PDFGenerate.PdfGeneratorWithText;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.ActivityTeacherSelectTimTableChractivityBinding;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class TeacherSelectTimTableCHRActivity extends AppCompatActivity {
ActivityTeacherSelectTimTableChractivityBinding binding;

    // variables for our buttons.

    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 500;

    // creating a bitmap variable
    // for storing our images

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityTeacherSelectTimTableChractivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Toast.makeText(this, ""+getIntent().getStringExtra("UserLogin"), Toast.LENGTH_SHORT).show();
        ScheduleDetailsAndCHR obj= new Gson().fromJson(getIntent().getStringExtra("intentData").toString(),ScheduleDetailsAndCHR.class);
        Type type = new TypeToken<MEYE_USER>(){}.getType();
        String data= getIntent().getStringExtra("UserLogin");
        MEYE_USER user= new Gson().fromJson(data,type);
        binding.txtCourse.setText(obj.getCourseName());
        binding.txtDate.setText(""+obj.getDate());
        binding.txtDay.setText(""+obj.getDay());
        binding.txtDiscipline.setText(""+obj.getDiscipline());
        binding.txtTime.setText(""+obj.getStartTime());
        binding.txtStatus.setText(""+obj.getStatus());
        binding.txtTimeIn.setText(""+obj.getTotalTimeIn());
        binding.txtTimeOut.setText(""+obj.getTotalTimeOut());
        binding.txtViewTeacherName.setText(""+user.getName());
        if(user.getImage()!=null){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+user.getRole()+"/"+user.getImage())).into(binding.profileImageTeacher);
        }
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
//        float pdfWidth = dpWidth * 0.75f * 72f; // 75% of the screen width in PDF points
//        float pdfHeight = dpHeight * 0.75f * 72f;
//        String extstoragedir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/PdfTett/";
//        File fol = new File(extstoragedir, "pdf");
//        File folder=new File(fol,"pdf");
//        if(!folder.exists()) {
//            boolean bool = folder.mkdir();
//        }
//        try {
//            final File file = new File(folder, "sample.pdf");
//            file.createNewFile();
//            FileOutputStream fOut = new FileOutputStream(file);
//
//
//            PdfDocument document = new PdfDocument();
//            PdfDocument.PageInfo pageInfo = new
//                    PdfDocument.PageInfo.Builder(100, 100, 1).create();
//            PdfDocument.Page page = document.startPage(pageInfo);
//            Canvas canvas = page.getCanvas();
//            Paint paint = new Paint();
//
//            canvas.drawText("welcome", 10, 10, paint);
//
//
//
//            document.finishPage(page);
//            document.writeTo(fOut);
//            document.close();
//
//        }catch (IOException e){
//            Log.i("error",e.getLocalizedMessage());
//        }



        // below code is used for
        // checking our permissions.

binding.btnGeneratePDF.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (checkPermission()) {
            Toast.makeText(getApplication(), "Permission Granted", Toast.LENGTH_SHORT).show();
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

//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    private String getTextFromView(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            stringBuilder.append(editText.getText().toString()).append("\n");
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            stringBuilder.append(textView.getText().toString()).append("\n");
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = viewGroup.getChildAt(i);
                stringBuilder.append(getTextFromView(childView));
            }
        }

        return stringBuilder.toString();
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
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));

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
        title.setColor(ContextCompat.getColor(this, R.color.purple_200));
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
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
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
                    Toast.makeText(this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // below line is used
                    // to handle error
                    e.printStackTrace();
                    binding.txtCourse.setText(e.toString());
                    Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
            } else {
    // The app has not been granted the MANAGE_EXTERNAL_STORAGE permission
    // Request the permission from the user
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }



        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
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
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}