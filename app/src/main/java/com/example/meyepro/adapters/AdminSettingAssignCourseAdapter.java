package com.example.meyepro.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.databinding.RecyclerviewAdminSettingAssignCourseCellBinding;
import com.example.meyepro.fragments.Admin.Setting.AssignCourse.AdminSettingAssignCourseActivity;
import com.example.meyepro.models.SectionOffer;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminSettingAssignCourseAdapter extends RecyclerView.Adapter<AdminSettingAssignCourseAdapter.AdminSettingAssignCourseViewHolder>{

    private ArrayList<SectionOffer> List1;
    private Context context;

    public AdminSettingAssignCourseAdapter(ArrayList<SectionOffer> List1, Context context) {
        this.List1 = List1;
        this.context = context;
     //   Toast.makeText(context, ""+List1.size(), Toast.LENGTH_SHORT).show();
    }
    @NonNull
    @Override
    public AdminSettingAssignCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewAdminSettingAssignCourseCellBinding b  =RecyclerviewAdminSettingAssignCourseCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

       AdminSettingAssignCourseViewHolder vh = new AdminSettingAssignCourseViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSettingAssignCourseViewHolder holder, int position) {
        SectionOffer obj = List1.get(position);
        holder.binding.RecyclerviewAssignCourseName.setText(obj.getCourseName()+"");
     //   Toast.makeText(context, "jj"+obj.courseName, Toast.LENGTH_SHORT).show();
        holder.binding.RecyclerviewAssignCourseSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                BitmapDrawable bmpDrawble = (BitmapDrawable)
//                        holder.binding.RecyclerviewAssignCourseSelected.getDrawable();
//
//                Bitmap bitmap = bmpDrawble.getBitmap();
//                Bitmap bitmap2 = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.yes_icon)).getBitmap();
                if (compareTwoDrawable(holder.binding.RecyclerviewAssignCourseSelected.getDrawable(), context.getResources().getDrawable(R.drawable.close_square_icon))) {
                    // Change the background to a different image
                    AdminSettingAssignCourseActivity assignCourseActivity= (AdminSettingAssignCourseActivity) context;
                    assignCourseActivity.RecyclerviewAssignCourseSelect(obj, context,"Remove");
                    holder.binding.linearLayoutAssignCourseSelected.setBackgroundResource(android.R.color.transparent);
                    holder.binding.RecyclerviewAssignCourseSelected.setImageResource(R.drawable.yes_icon);

                }
                else {
                    // Set the background to R.drawable.yes_icon
                    AdminSettingAssignCourseActivity assignCourseActivity= (AdminSettingAssignCourseActivity) context;
                    assignCourseActivity.RecyclerviewAssignCourseSelect(obj, context,"ADD");
                //    Toast.makeText(context, ""+obj.courseName, Toast.LENGTH_SHORT).show();
                    holder.binding.RecyclerviewAssignCourseSelected.setImageResource(R.drawable.close_square_icon);
                    holder.binding.linearLayoutAssignCourseSelected.setBackgroundResource(R.drawable.border_top_right_left_recyclerview);
                }

//                if()
//                holder.binding.RecyclerviewAssignCourseSelected.setBackgroundResource(R.drawable.yes_icon);


            }
        });
    }

    @Override
    public int getItemCount() {
        return List1.size();
    }

    class  AdminSettingAssignCourseViewHolder extends RecyclerView.ViewHolder{
      RecyclerviewAdminSettingAssignCourseCellBinding binding;//<----
        public AdminSettingAssignCourseViewHolder(
                @NonNull RecyclerviewAdminSettingAssignCourseCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
    }

    public static boolean compareTwoDrawable(Drawable drawable1, Drawable drawable2) {
        int h=drawable1.getIntrinsicHeight();
        int w=drawable1.getIntrinsicWidth();
        Bitmap map= Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        int h2=drawable2.getIntrinsicHeight();
        int w2=drawable2.getIntrinsicWidth();
        Bitmap map2= Bitmap.createBitmap(w2,h2, Bitmap.Config.ARGB_8888);
       //binary code match
//        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
//        map.compress(Bitmap.CompressFormat.PNG, 100, stream1);
//        byte[] bytes1 = stream1.toByteArray();
//
//        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
//        map2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
//        byte[] bytes2 = stream2.toByteArray();
//
//       boolean same = Arrays.equals(bytes1, bytes2);

        boolean same = map.sameAs(map2);
    return same;
    }

}
