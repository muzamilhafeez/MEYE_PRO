package com.example.meyepro.TeacherDashBoard.PDFGenerate;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGeneratorWithText {
    private static final String TAG = "PdfGenerator";

    public static void generatePdf(Context context, View view, String fileName) {
        // Create a new document
        Document document = new Document();

        // Get the directory for storing the PDF file
        File pdfDir = new File(Environment.getExternalStorageDirectory(), "PDFs");
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

        // Copy the text from EditText and TextView
        copyTextFromView(view, document);

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

        // Close the document
        document.close();

        Log.d(TAG, "PDF file generated: " + file.getAbsolutePath());
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

    private static void copyTextFromView(View view, Document document) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            String text = editText.getText().toString();
            if (!text.isEmpty()) {
                Paragraph paragraph = new Paragraph(text);
                try {
                    document.add(paragraph);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            String text = textView.getText().toString();
            if (!text.isEmpty()) {
                Paragraph paragraph = new Paragraph(text);
                try {
                    document.add(paragraph);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = viewGroup.getChildAt(i);
                copyTextFromView(childView, document);
            }
        }
    }

    public static void copyTextToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
    }
}
