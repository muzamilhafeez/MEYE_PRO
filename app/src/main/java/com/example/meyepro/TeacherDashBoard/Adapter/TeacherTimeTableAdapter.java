package com.example.meyepro.TeacherDashBoard.Adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.databinding.RecyclerviewTeacherTimetableCellBinding;
import com.example.meyepro.models.TimeTable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TeacherTimeTableAdapter extends RecyclerView.Adapter<TeacherTimeTableAdapter.TeacherTimeTableViewHolder> {
    private ArrayList<TimeTable> List;
    private Context context;
//    TimeZone timeZone = TimeZone.getTimeZone("Asia/Karachi");
//    Calendar calendar = Calendar.getInstance(timeZone);
//    Date currentTime = calendar.getTime();


    public TeacherTimeTableAdapter(ArrayList<TimeTable> List, Context context) {
        this.List = List;
        this.context = context;

    }

    @NonNull
    @Override
    public TeacherTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewTeacherTimetableCellBinding b = RecyclerviewTeacherTimetableCellBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TeacherTimeTableViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherTimeTableViewHolder holder, int position) {
        TimeTable obj = List.get(position);
        holder.binding.txtViewTeacherTimetableStatus.setText("held");
        holder.binding.txtViewTeacherTimetableCourse.setText(obj.getCourseName());
        holder.binding.txtViewTeacherTimetableDiscipline.setText(obj.getDiscipline());
        holder.binding.txtViewTeacherTimetableVenue.setText(obj.getVenue());
        holder.binding.txtViewTeacherTimetableDay.setText(obj.getDay());
        holder.binding.txtViewTeacherTimetableStatTime.setText(obj.getStarttime());

//        holder.binding.txtViewTeacherTimetableStatus.setText("00000000000");
        // get the current time in the desired time zone
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Karachi");
        Calendar calendar = Calendar.getInstance(timeZone);
        Date currentTime = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM, EEEE, hh:mm a");
        dateFormat.setTimeZone(timeZone);
        String currentTimeFormatted = dateFormat.format(currentTime);
        String TimeFormattedStrat = null;
        String TimeFormattedEnd;

//        Toast.makeText(this, ""+Integer.parseInt(obj.getStarttime().split(":")[0]), Toast.LENGTH_SHORT).show();
        if(Integer.parseInt(obj.getStarttime().split(":")[0])>=8&&Integer.parseInt(obj.getStarttime().split(":")[0])<12){
            TimeFormattedStrat = currentTimeFormatted.split("-")[0]+"-"+currentTimeFormatted.split("-")[1].split(",")[0]+", "+obj.getDay() + ", " + obj.getStarttime() + " am";
//            Toast.makeText(context, obj.getDay()+":"+TimeFormattedStrat, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "ii", Toast.LENGTH_SHORT).show();
        }else{
            TimeFormattedStrat = currentTimeFormatted.split("-")[0]+"-"+currentTimeFormatted.split("-")[1].split(",")[0]+", "+obj.getDay() + ", " + obj.getStarttime() + " pm";
        }
        if(Integer.parseInt(obj.getStarttime().split(":")[0])>=8&&Integer.parseInt(obj.getStarttime().split(":")[0])<12){
            TimeFormattedEnd = currentTimeFormatted.split("-")[0]+"-"+currentTimeFormatted.split("-")[1].split(",")[0]+", "+obj.getDay() + ", " + obj.getEndtime() + " am";
//            Toast.makeText(this, "ii", Toast.LENGTH_SHORT).show();
        }else{
            TimeFormattedEnd = currentTimeFormatted.split("-")[0]+"-"+currentTimeFormatted.split("-")[1].split(",")[0]+", "+obj.getDay() + ", " + obj.getEndtime() + " pm";
        }

        Date currentTimeDate = null;
        Date TimeFormattedDateStart = null;
        Date TimeFormattedDateEnd = null;
        try {
            currentTimeDate = dateFormat.parse(currentTimeFormatted);
            TimeFormattedDateStart = dateFormat.parse(TimeFormattedStrat);
            TimeFormattedDateEnd = dateFormat.parse(TimeFormattedEnd);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (currentTimeDate != null && TimeFormattedDateStart != null) {
            if (currentTimeDate.before(TimeFormattedDateStart)) {
                long timeDifference = TimeFormattedDateStart.getTime() - currentTimeDate.getTime();
                // Start the countdown timer
//                Toast.makeText(context, ""+timeDifference, Toast.LENGTH_SHORT).show();
                holder.startCountDownTimer(timeDifference);
            } else if (currentTimeDate.after(TimeFormattedDateStart) && currentTimeDate.before(TimeFormattedDateEnd) ) {
                holder.binding.txtViewTeacherTimetableStatus.setText("Running");
            } else {
                holder.binding.txtViewTeacherTimetableStatus.setText("Held");
            }
        }



//        holder.startCountDownTimer(60_000); // set the duration to 60 seconds
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class TeacherTimeTableViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewTeacherTimetableCellBinding binding;
        CountDownTimer timer;

        public TeacherTimeTableViewHolder(@NonNull RecyclerviewTeacherTimetableCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void startCountDownTimer(long countdownMillis) {

            if (timer != null) {
                // cancel the previous timer to avoid overlapping
                timer.cancel();
            }
            timer = new CountDownTimer(countdownMillis, 1000) {
                @Override

                public void onTick(long millisUntilFinished) {
                    // Calculate remaining time in days, hours, and minutes
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;
                    hours %= 24;
                    minutes %= 60;
                    seconds %= 60;
                    // Show the remaining time in the TextView
                    binding.txtViewTeacherTimetableStatus.setText(String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds));
                }

                @Override
                public void onFinish() {
                    // perform actions when the timer finishes
                    binding.txtViewTeacherTimetableStatus.setText("00:00");
                }
            };
            timer.start();
        }
    }
}
