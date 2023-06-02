package com.example.meyepro.TeacherDashBoard.PDFGenerate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {
    private static final String TAG = "PdfGenerator";
    Context context;

    public void generatePdf(Context context, View view, String fileName) {
        // Create a new document
        this.context=context;
        Document document = new Document();
        File pdfDir = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                // The app has been granted the MANAGE_EXTERNAL_STORAGE permission
//                File file = new File(String.valueOf(Environment.getExternalStorageDirectory()), fileName);
                try {
                    // after creating a file name we will
                    // write our PDF file to that location.
                    // Get the directory for storing the PDF file
                   pdfDir = new File(Environment.getExternalStorageDirectory(), fileName);
                    if (!pdfDir.exists()) {
                        pdfDir.mkdirs();
                    }
                    // Create the PDF file
                    File file = new File(pdfDir, fileName);
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Create a PDF writer instance
                    try {
                        PdfWriter.getInstance(document, new FileOutputStream(file));
                    } catch (DocumentException | IOException e) {
                        e.printStackTrace();
                    }

                    // Open the document
                    document.open();

                    // Create an image from the view
                    Bitmap bitmap = loadBitmapFromView(view);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    // Add the image to the PDF document
                    try {
                        Image image = Image.getInstance(stream.toByteArray());
                        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                        document.add(image);
                    } catch (DocumentException | IOException e) {
                        e.printStackTrace();
                    }
                    // below line is to print toast message
                    // on completion of PDF generation.
                    Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // below line is used
                    // to handle error
                    Toast.makeText(context, ""+e.toString(), Toast.LENGTH_SHORT).show();

                }
            } else {
                // The app has not been granted the MANAGE_EXTERNAL_STORAGE permission
                // Request the permission from the user
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" +context.getPackageName()));
                context.startActivity(intent);
            }
        }

//        // Get the directory for storing the PDF file
//        File pdfDir = new File(Environment.getExternalStorageDirectory(), "PDFs");
//        if (!pdfDir.exists()) {
//            pdfDir.mkdirs();
//        }



        // Close the document
        document.close();

//        Log.d(TAG, "PDF file generated: " + file.getAbsolutePath());
    }

    private static Bitmap loadBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(android.graphics.Color.WHITE);
        }
        view.draw(canvas);
        return bitmap;
    }

}

