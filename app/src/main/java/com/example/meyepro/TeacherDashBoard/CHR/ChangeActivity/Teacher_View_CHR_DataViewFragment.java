package com.example.meyepro.TeacherDashBoard.CHR.ChangeActivity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.provider.Settings;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.meyepro.DirectorDashBoard.Adapter.DirectorHomeDataTableAdapter;
import com.example.meyepro.DirectorDashBoard.Home.DirectorDataTableFragment;
import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.FragmentDirectorDataTableBinding;
import com.example.meyepro.databinding.FragmentTeacherViewCHRDataViewBinding;
import com.example.meyepro.fragments.Admin.AdminSettingFragment;
import com.example.meyepro.login;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Teacher_View_CHR_DataViewFragment extends Fragment {
FragmentTeacherViewCHRDataViewBinding binding;
    DirectorHomeDataTableAdapter adapter;
    ArrayList<ScheduleDetailsAndCHR> scheduleDetailsAndCHRS=new ArrayList<>();
    int pageHeight = 1120;
    int pagewidth = 800;
    private static final int PERMISSION_REQUEST_CODE = 200;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentTeacherViewCHRDataViewBinding.inflate(getLayoutInflater());
      

        //start code

        binding.imageviewAdminHomeSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });
        RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
        api.get_teacher_chr(login.userInfoDetail.getName()).enqueue(new Callback<ArrayList<ScheduleDetailsAndCHR>>() {
            @Override
            public void onResponse(Call<ArrayList<ScheduleDetailsAndCHR>> call, Response<ArrayList<ScheduleDetailsAndCHR>> response) {
                if(response.isSuccessful()){

                    scheduleDetailsAndCHRS.clear();

                    scheduleDetailsAndCHRS.addAll(response.body());
//                    Toast.makeText(getContext(), ""+scheduleDetailsAndCHRS.size(), Toast.LENGTH_SHORT).show();
                    adapter = new
                            DirectorHomeDataTableAdapter(scheduleDetailsAndCHRS, getActivity(),Teacher_View_CHR_DataViewFragment.this);
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    binding.RecycerviewDirectorDatatable.setLayoutManager(manager);
                    binding.RecycerviewDirectorDatatable.setHasFixedSize(true);
                    binding.RecycerviewDirectorDatatable.
                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ScheduleDetailsAndCHR>> call, Throwable t) {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });


        //end coee
        return binding.getRoot();
    }
    public void showPopup() {
        if(scheduleDetailsAndCHRS.size()!=0){
            PopupMenu popup = new PopupMenu(getContext(), binding.imageviewAdminHomeSearchIcon);
            popup.getMenuInflater().inflate(R.menu.popup_director_search_menu, popup.getMenu());
            Object menuHelper;
            Class[] argTypes;
            ArrayList<ScheduleDetailsAndCHR> filteredList = new ArrayList<>();
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_Director_Name:
                            // call member class or function here

                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getTeacherName().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
//
//                        DirectorHomeAdapter adapter = new
//                                DirectorHomeAdapter(filteredList, getActivity(),DirectorRecyclerViewCalingHomeFragment.this);
//                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//                        binding.RecycerviewDirectorHome.setLayoutManager(manager);
//                        binding.RecycerviewDirectorHome.setHasFixedSize(true);
//                        binding.RecycerviewDirectorHome.
//                                setAdapter(adapter);
//                            Toast.makeText(getContext(), "name", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.action_Director_Course:

                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getCourseName().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.action_Director_Date:
                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getDate().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
                            // call My Buddies class or function here
                            break;
                        case R.id.action_Director_Discipline:
                            for (ScheduleDetailsAndCHR checkItem : scheduleDetailsAndCHRS) {
                                if (checkItem.getDiscipline().toLowerCase().contains(binding.editTextAdminHomeSearch.getText().toString().toLowerCase())) {
                                    filteredList.add(checkItem);
                                }
                            }
                            adapter.filterList(filteredList);
                            if(filteredList.size()==0){

                                Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
                            }
                            // call My Buddies class or function here
                            return true;
                    }
                    return true;
                }
            });
            popup.show();
        }

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
//        BitmapDrawable bmpDrawable = (BitmapDrawable) binding.profileImageTeacher.getDrawable();
//        Bitmap bmpImage = bmpDrawable.getBitmap();

        // Calculate the desired resolution based on the zoom level
        float zoomLevel = 2.0f; // Adjust the zoom level as needed
        int desiredWidth = (int) (50* zoomLevel);
        int desiredHeight = (int) (50 * zoomLevel);

//// Create a new bitmap with the desired resolution
//        Bitmap highResBitmap = Bitmap.createScaledBitmap(bmpImage, desiredWidth, desiredHeight, true);
//        paint.setAntiAlias(true);
//        paint.setFilterBitmap(true);
//        // Calculate the x coordinate to align the image to the right
//        int x = pagewidth-140;
//// Draw the high-resolution bitmap onto the PDF canvas
//        canvas.drawBitmap(highResBitmap, x, 20, paint);



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
//        canvas.drawText(binding.txtViewTeacherName.getText().toString(), 209, 70, title);
//        canvas.drawText("MEYE Pro", 209, 80, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));
        title.setTextSize(15);

        Paint linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.blue_500)); // Set the desired color for the lines
        linePaint.setStrokeWidth(1); // Set the desired width for the lines
        float starttopX = 0; // Starting X coordinate for the lines
        float startTopY = 10; // Starting Y coordinate for the lines
        float endTopX = canvas.getWidth(); // Ending X coordinate for the lines
        float endTopY = canvas.getHeight(); // Ending Y coordinate for the lines

        canvas.drawLines(new float[]{starttopX, startTopY, endTopX, startTopY, starttopX, endTopY, endTopX, endTopY}, linePaint);
        // Calculate the width for each column based on the page width and the number of columns
        int columnCount = 8;
        int columnWidth = pagewidth / columnCount;
        Paint paintTxt= new Paint();
        paintTxt.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        paintTxt.setTextSize(15);
        int index=1;
//        Toast.makeText(getContext(), "check", Toast.LENGTH_SHORT).show();
        // Draw column headers
        int x = 20;
        canvas.drawText("St. No", x, 30*index, paintTxt);
        x += columnWidth;
        canvas.drawText("Teacher", x, 30*index, paintTxt);
        x += columnWidth;
        canvas.drawText("Course", x, 30*index, paintTxt);
        x += columnWidth;
        canvas.drawText("Date", x, 30*index, paintTxt);
        x += columnWidth;
        canvas.drawText("Sit", x, 30*index, paintTxt);
        x += columnWidth;
        canvas.drawText("Strand", x, 30*index, paintTxt);
        x += columnWidth;
//        canvas.drawText("Mobile", x, 30*index, paintTxt);
//        x += columnWidth;
        canvas.drawText("Status", x, 30*index, paintTxt);
        float startX = 0; // Starting X coordinate for the lines
        float startY = 40*index; // Starting Y coordinate for the lines
        float endX = canvas.getWidth(); // Ending X coordinate for the lines
        float endY = canvas.getHeight(); // Ending Y coordinate for the lines

        canvas.drawLines(new float[]{startX, startY, endX, startY, startX, endY, endX, endY}, linePaint);

        for (ScheduleDetailsAndCHR scheduleDetailsAndCHR:scheduleDetailsAndCHRS) {
            ++index;
            x = 20;
            // Draw each column with adjusted width
            drawTextWithWrapping(canvas, (index-1)+"", (x+10), 30*index, columnWidth, paintTxt);
            x += columnWidth;
            drawTextWithWrapping(canvas, scheduleDetailsAndCHR.getTeacherName(), x, 30*index, columnWidth, paintTxt);
            x += columnWidth;
            drawTextWithWrapping(canvas, scheduleDetailsAndCHR.getCourseName(), x, 30*index, columnWidth, paintTxt);
            x += columnWidth;
            drawTextWithWrapping(canvas, scheduleDetailsAndCHR.getDate(), x, 30*index, columnWidth, paintTxt);
            x += columnWidth;
            drawTextWithWrapping(canvas,""+scheduleDetailsAndCHR.getSit(), (8+x), 30*index, columnWidth,paintTxt);
            x += columnWidth;
            drawTextWithWrapping(canvas,""+scheduleDetailsAndCHR.getStand(), (20+x), 30*index, columnWidth,paintTxt);
            x += columnWidth;
//            drawTextWithWrapping(canvas,""+scheduleDetailsAndCHR.getMobile(), (20+x), 30*index, columnWidth,paintTxt);
//            x += columnWidth;
            drawTextWithWrapping(canvas,""+scheduleDetailsAndCHR.getStatus(), x, 30*index, columnWidth,paintTxt);
        }






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
        File file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                // The app has been granted the MANAGE_EXTERNAL_STORAGE permission
                file = new File(String.valueOf(Environment.getExternalStorageDirectory()), "muzamil.pdf");
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
//                    binding.txtCourse.setText(e.toString());
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

        // Generate a content URI using FileProvider
//        Uri contentUri = FileProvider.getUriForFile(getActivity(), "com.example.meyepro.fileprovider", file);
        // Generate a content URI using FileProvider
        Uri contentUri = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".fileprovider", file);
//        Toast.makeText(cw, ""+getContext().getPackageName() + ".fileprovider", Toast.LENGTH_SHORT).show();
        // Create an 'Intent' to view the PDF
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(contentUri, "application/pdf");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Grant read permission to the receiving app

        // Start the activity with the 'Intent'
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "No PDF viewer application found.", Toast.LENGTH_SHORT).show();
        }
    }
    // Helper method to draw text with wrapping
    private void drawTextWithWrapping(Canvas canvas, String text, float x, float y, int width, Paint paint) {
        CharSequence charSequence = text; // Convert String to CharSequence

        TextPaint textPaint = new TextPaint();
        textPaint.set(paint); // Copy attributes from the original Paint

        StaticLayout staticLayout = new StaticLayout(charSequence, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate(x, y);
        staticLayout.draw(canvas);
        canvas.restore();
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