package com.example.meyepro.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.databinding.RecyclerviewAdminSettingAssignStudentCellBinding;
import com.example.meyepro.fragments.Admin.Setting.AssignCourse.AdminSettingAssignCourseActivity;
import com.example.meyepro.fragments.Admin.Setting.AssignCourse.AdminSettingAssignCourseAssignStudentActivity;
import com.example.meyepro.models.SectionOffer;
import com.example.meyepro.models.Student;
import com.example.meyepro.models.Venue;

import java.util.ArrayList;

public class AdminSettingAssignStudentCourseAdapter extends RecyclerView.Adapter<AdminSettingAssignStudentCourseAdapter.AdminSettingAssignStudentCourseViewHolder>
    {
        private ArrayList<Student> List;
        private Context context;
        public AdminSettingAssignStudentCourseAdapter(ArrayList<Student> List, Context context) {
            this.List = List;
            this.context = context;
        }

        @NonNull
        @Override
        public AdminSettingAssignStudentCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerviewAdminSettingAssignStudentCellBinding b  =RecyclerviewAdminSettingAssignStudentCellBinding.
                    inflate(LayoutInflater
                                    .from(parent.getContext()),
                            parent, false);

            AdminSettingAssignStudentCourseViewHolder vh = new AdminSettingAssignStudentCourseViewHolder(b);
            return vh;

        }

        @Override
        public void onBindViewHolder(@NonNull AdminSettingAssignStudentCourseViewHolder holder, int position) {
            Student obj = List.get(position);
            holder.binding.txtViewRecyclerviewAssignStudentName.setText(obj.getName());
            holder.binding.iconRecyclerviewAssignStudentSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (compareTwoDrawable(holder.binding.iconRecyclerviewAssignStudentSelected.getDrawable(), context.getResources().getDrawable(R.drawable.close_square_icon))) {
                        // Change the background to a different image
                        AdminSettingAssignCourseAssignStudentActivity assignCourseStudentActivity= (AdminSettingAssignCourseAssignStudentActivity) context;
                        assignCourseStudentActivity.RecyclerviewAssignCourseSelectStudent(obj, context,"Remove");
                holder.binding.linearLayoutAssignCourseSelectedStudent.setBackgroundResource(android.R.color.transparent);
                        holder.binding.iconRecyclerviewAssignStudentSelected.setImageResource(R.drawable.yes_icon);

                    }
                    else {
                        // Set the background to R.drawable.yes_icon
                        AdminSettingAssignCourseAssignStudentActivity assignCourseStudentActivity= (AdminSettingAssignCourseAssignStudentActivity) context;
                        assignCourseStudentActivity.RecyclerviewAssignCourseSelectStudent(obj, context,"Add");
//                            Toast.makeText(context, ""+obj.courseName, Toast.LENGTH_SHORT).show();
                        holder.binding.iconRecyclerviewAssignStudentSelected.setImageResource(R.drawable.close_square_icon);
                holder.binding.linearLayoutAssignCourseSelectedStudent.setBackgroundResource(R.drawable.border_top_right_left_recyclerview);
                    }
                }
            });

//            holder.binding.textViewVenue2.setText(obj.getNAME()+"");

        }

        @Override
        public int getItemCount() {
            return List.size();
        }

        class AdminSettingAssignStudentCourseViewHolder extends RecyclerView.ViewHolder {
            RecyclerviewAdminSettingAssignStudentCellBinding binding;//<----

            public AdminSettingAssignStudentCourseViewHolder(
                    @NonNull RecyclerviewAdminSettingAssignStudentCellBinding itemView) {
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



