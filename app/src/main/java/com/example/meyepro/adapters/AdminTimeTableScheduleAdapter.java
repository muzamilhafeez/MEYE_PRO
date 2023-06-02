package com.example.meyepro.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import androidx.fragment.app.Fragment;

import com.example.meyepro.TeacherDashBoard.CHR.TeacherSelectTimTableCHRActivity;
import com.example.meyepro.databinding.RecyclerviewTimetableScheduleCellBinding;
import com.example.meyepro.fragments.Admin.Schedule.PreSchedule.AdminPreScheduleTimeTableShowSelectActivity;
import com.example.meyepro.fragments.Admin.Setting.RuleSetting.AdminSettingRuleSettingSetRuleActivity;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.TimeTable;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AdminTimeTableScheduleAdapter extends RecyclerView.Adapter<AdminTimeTableScheduleAdapter.AdminTimeTableScheduleViewHolder>{
    private ArrayList<TimeTable> TimeTableList;
    ArrayList<TimeTable> TimeTableSchrduleList;
    private Context context;
    private Fragment fragment;
    Boolean SelectAll=false;
    String ActivityName ="";

    ArrayList<String> uniqueStartTimes= new ArrayList<>();
    ArrayList<String> uniqueEndTimes= new ArrayList<>();
    ArrayList<String> uniqueDays= new ArrayList<>();

    public AdminTimeTableScheduleAdapter(ArrayList<TimeTable> TimeTableList, Context context) {
        this.TimeTableList = TimeTableList;
        this.context = context;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
            uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
            uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
        }
    }
    public AdminTimeTableScheduleAdapter(ArrayList<TimeTable> TimeTableList ,ArrayList<TimeTable> TimeTableSchrduleList, Context context,String ActivtyName) {
        this.ActivityName=ActivtyName;
        this.TimeTableList = TimeTableList;
        this.context = context;
        this.TimeTableSchrduleList=TimeTableSchrduleList;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
            uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
            uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
        }
        for (TimeTable t :TimeTableList) {
            System.out.println("TimeTable"+t.getVenue());
        }
    }
    public AdminTimeTableScheduleAdapter(ArrayList<TimeTable> TimeTableList ,ArrayList<TimeTable> TimeTableSchrduleList, Context context,Boolean SelectAll,String ActivityName) {
        this.TimeTableList = TimeTableList;
        this.context = context;
        this.TimeTableSchrduleList=TimeTableSchrduleList;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
            uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
            uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
        }
        for (TimeTable t :TimeTableList) {
            System.out.println("TimeTable"+t.getVenue());
        }
        this.SelectAll=SelectAll;
    }
    public AdminTimeTableScheduleAdapter(ArrayList<TimeTable> TimeTableList , ArrayList<TimeTable> TimeTableSchrduleList, Context context, Fragment fragment) {
        this.TimeTableList = TimeTableList;
        this.context = context;
        this.TimeTableSchrduleList=TimeTableSchrduleList;
        this.fragment=fragment;
        this.ActivityName=ActivityName;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
            uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
            uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
        }
        for (TimeTable t :TimeTableList) {
            System.out.println("TimeTable"+t.getVenue());
        }

    }
    @NonNull
    @Override
    public AdminTimeTableScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTimetableScheduleCellBinding b  =RecyclerviewTimetableScheduleCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminTimeTableScheduleViewHolder vh = new AdminTimeTableScheduleViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTimeTableScheduleViewHolder holder, int position) {
        //row one only
        if(position==0){
            holder.bindDays(position);
        }
        String obj = uniqueStartTimes.get(position);
        //multiple column create
        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return uniqueStartTimes.size();
    }

    //show Rows
    class AdminTimeTableScheduleViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewTimetableScheduleCellBinding binding;//<----
        public AdminTimeTableScheduleViewHolder(
                @NonNull RecyclerviewTimetableScheduleCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
        // row one Day Name
        public void bindDays(int position) {
            binding.linearLayoutDayOfName.removeAllViews();

            // Create the first TextView with a width of 0 and weight of 1 to distribute equal width
            TextView textViewFirst = new TextView(itemView.getContext());
            TableRow.LayoutParams firstParams = new TableRow.LayoutParams(
                    98, TableRow.LayoutParams.MATCH_PARENT);
            textViewFirst.setLayoutParams(firstParams);
            textViewFirst.setGravity(Gravity.CENTER);
            binding.linearLayoutDayOfName.addView(textViewFirst);

            // Create the remaining TextViews with equal width and a margin of 5dp between them
            for (int i = 0; i < uniqueDays.size(); i++) {
                TextView textView = new TextView(itemView.getContext());
                TableRow.LayoutParams params = new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.MATCH_PARENT, 1f);
//                params.setMarginStart((int) itemView.getContext().getResources().getDimension(R.dimen.spacing_xxlarge));
                textView.setLayoutParams(params);
                textView.setBackgroundResource(R.drawable.teacher_recyclerview_background_cell);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setGravity(Gravity.CENTER);
                textView.setText(uniqueDays.get(i).substring(0, 3));
                textView.setTextSize(18);
                binding.linearLayoutDayOfName.addView(textView);
            }
        }
        //end row of Days
        // start Stat Time And End Time
        public void bind(int RowPosition) {
            binding.linearLayoutTimeTableSchedule.removeAllViews();
            TextView textViewFirst = new TextView(context);
            // Set the text

            textViewFirst.setText(uniqueStartTimes.get(RowPosition)+"\n"+uniqueEndTimes.get(RowPosition));
            // Set the width and height
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;; // in pixels
            int height =200;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
            textViewFirst.setLayoutParams(layoutParams);
            /// Set the gravity and layout gravity
            textViewFirst.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textViewFirst.setForegroundGravity(Gravity.CENTER);
            }
//            // Create a TableRow to contain the TextViews
//            TableRow tableRow = new TableRow(itemView.getContext());
//            tableRow.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT));
            binding.linearLayoutTimeTableSchedule.addView(textViewFirst);
            for (int i = 0; i < uniqueDays.size(); i++) {
                TableRow.LayoutParams params = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        200, 1f);
                params.gravity = Gravity.RIGHT;
                TextView textView = new TextView(itemView.getContext());
                textView.setLayoutParams(params);
                textView.setTypeface(null, Typeface.BOLD);
                textView .setGravity(Gravity.CENTER);

                //enter TextView Data
                for (TimeTable timeTable :TimeTableSchrduleList){
                    if (timeTable.getStarttime().toString().equals(uniqueStartTimes.get(RowPosition)) ) {
                        if (timeTable.getDay().equals(uniqueDays.get(i))) {
                            if(SelectAll){
                                textView.setBackgroundColor(Color.parseColor("#03A9F4"));
                            }

                            textView.setText(""+timeTable.getDiscipline()+"\n"+timeTable.getCourseName()+"\n"+timeTable.getVenue());

                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(ActivityName.contains("AdminPreScheduleTimeTableShowSelectActivity")){
                                        AdminPreScheduleTimeTableShowSelectActivity adminPreScheduleTimeTableShowSelectActivity= (AdminPreScheduleTimeTableShowSelectActivity) context;
                                        adminPreScheduleTimeTableShowSelectActivity.recyclerViewSelectedTimeTableSlot(timeTable, context, textView);
                                    }  if(ActivityName.contains("AdminSettingRuleSettingSetRuleActivity")){
                                        AdminSettingRuleSettingSetRuleActivity adminPreScheduleTimeTableShowSelectActivity= (AdminSettingRuleSettingSetRuleActivity) context;
                                        adminPreScheduleTimeTableShowSelectActivity.recyclerViewSelectedTimeTableSlot(timeTable, context, textView);
                                    }


//                                    Intent i = new Intent(context, TeacherSelectTimTableCHRActivity.class);
//                                    context.startActivity(i);
//                                    Toast.makeText(context, ""+textView.getText().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }

                //end enter TextView Data
              // Add a ScrollView to the parent layout
                ScrollView scrollView = new ScrollView(itemView.getContext());
                scrollView.setLayoutParams(params);
                scrollView.setVerticalScrollBarEnabled(true);

                // Add the TextView to the ScrollView
                scrollView.addView(textView);
                scrollView.setBackgroundResource(R.drawable.teacher_recyclerview_background_cell);
//                tableRow.addView(scrollView);
//                textView.setMovementMethod(new ScrollingMovementMethod());
                binding.linearLayoutTimeTableSchedule.addView(scrollView);

//                binding.linearLayoutTimeTableSchedule.addView(textView);
            }
//            binding.linearLayoutTimeTableSchedule.addView(tableRow);
        }

        // End start Stat Time And End Time
    }
    public void filterList(ArrayList<TimeTable> filteredList, Boolean SelectAll) {
        this.SelectAll=SelectAll;
        TimeTableSchrduleList = filteredList;
        notifyDataSetChanged();
    }
}
