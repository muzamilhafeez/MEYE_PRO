package com.example.meyepro.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.databinding.RecyclerviewRescheduleFreeSlotCellBinding;
import com.example.meyepro.fragments.Admin.Schedule.PreSchedule.AdminSchedulePreScheduleFreeSlotShowActivity;
import com.example.meyepro.fragments.Admin.Schedule.Reschedule.AdminScheduleReScheduleFreeSlotActivity;
import com.example.meyepro.fragments.Admin.Schedule.Swapping.AdminScheduleSwappingFreeSlotActivity;
import com.example.meyepro.models.TimeTable;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AdminScheduleFreeSlotAdapter extends RecyclerView.Adapter<AdminScheduleFreeSlotAdapter.AdminScheduleFreeSlotViewHolder>{
    private ArrayList<TimeTable> TimeTableList;
    private Context context;
    private  Fragment fragment;
    ArrayList<String> uniqueStartTimes= new ArrayList<>();
    ArrayList<String> uniqueEndTimes= new ArrayList<>();
    ArrayList<String> uniqueDays= new ArrayList<>();
    ArrayList<String> uniqueVenue= new ArrayList<>();
    ArrayList<HashMap<String, ArrayList<String>>> venueMap = new ArrayList<>();
    Spinner[][] allSpinners = new Spinner[uniqueDays.size()][uniqueDays.size()];
    ArrayList<String> NameOfDays;
    ArrayList<Integer> enableIndexList = new ArrayList<Integer>();
    String ActivityName;

    public AdminScheduleFreeSlotAdapter(ArrayList<TimeTable> TimeTableList, Context context) {
        this.TimeTableList = TimeTableList;
        this.context = context;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
            uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
            uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
            uniqueVenue.addAll(TimeTableList.stream().distinct().map(t -> t.getVenue()).distinct().collect(Collectors.toList()));
            allSpinners = null;
            allSpinners = new Spinner[uniqueDays.size()][uniqueDays.size()];

        }
    }
    public AdminScheduleFreeSlotAdapter(ArrayList<TimeTable> TimeTableList, Context context, String ActivityName) {
        this.TimeTableList = TimeTableList;
        this.context = context;
        this.ActivityName=ActivityName;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
            uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
            uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
            uniqueVenue.addAll(TimeTableList.stream().distinct().map(t -> t.getVenue()).distinct().collect(Collectors.toList()));
            allSpinners = null;
            allSpinners = new Spinner[uniqueDays.size()][uniqueDays.size()];

        }
    }
 public AdminScheduleFreeSlotAdapter(ArrayList<TimeTable> TimeTableList, Context context,ArrayList<String> NameOfDays)
        {
            this.TimeTableList = TimeTableList;
            this.context = context;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
                uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
                uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
                uniqueVenue.addAll(TimeTableList.stream().distinct().map(t -> t.getVenue()).distinct().collect(Collectors.toList()));
                allSpinners = null;
                allSpinners = new Spinner[uniqueDays.size()][uniqueDays.size()];
                this.NameOfDays = NameOfDays;
                // Find the position of the list with the name NameOfDays
                for (int i = 0; i < NameOfDays.size(); i++) {
                    final int positionF = i;
                    String searchItem = NameOfDays.get(positionF);
                    int index = -1;
                    for (int j = 0; j < uniqueDays.size(); j++) {
                        if (uniqueDays.get(j).equalsIgnoreCase(searchItem)) {
                            index = j;
                            break;
                        }
                    }
                    if (index != -1) {
                        enableIndexList.add(index);
                    }
                }
//                Toast.makeText(context, "22"+new Gson().toJson(enableIndexList), Toast.LENGTH_SHORT).show();

            }


//        for (String Day : uniqueDays) {
//            HashMap<String, ArrayList<String>> map = new HashMap<>();
//            map.put(Day, new ArrayList<String>());
//            venueMap.add(map);
//        }
//
////
//
//        for (TimeTable t : TimeTableList ){
//            if (t.getStarttime() ==  ) {
//                String venueName = t.getVenue();
//                for (HashMap<String, ArrayList<String>> map : venueMap) {
//                    ArrayList<String> venues = map.get(t.getDay());
//                    if (venues != null && venues.contains(venueName)) {
//                        venues.remove(venueName);
//                        break;
//                    }
//                }
//            }
//        }

//
//        for (String s:uniqueVenue) {
//            System.out.println("dddd"+s);
//        }

    }

    public AdminScheduleFreeSlotAdapter(ArrayList<TimeTable> TimeTableList, Context context,ArrayList<String> NameOfDays, String ActivityName)
    {
        this.TimeTableList = TimeTableList;
        this.context = context;
        this.ActivityName=ActivityName;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            uniqueStartTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getStarttime()).distinct().collect(Collectors.toList()));
            uniqueEndTimes.addAll(TimeTableList.stream().distinct().map(t -> t.getEndtime()).distinct().collect(Collectors.toList()));
            uniqueDays.addAll(TimeTableList.stream().distinct().map(t -> t.getDay()).distinct().collect(Collectors.toList()));
            uniqueVenue.addAll(TimeTableList.stream().distinct().map(t -> t.getVenue()).distinct().collect(Collectors.toList()));
            allSpinners = null;
            allSpinners = new Spinner[uniqueDays.size()][uniqueDays.size()];
            this.NameOfDays = NameOfDays;
            // Find the position of the list with the name NameOfDays
            for (int i = 0; i < NameOfDays.size(); i++) {
                final int positionF = i;
                String searchItem = NameOfDays.get(positionF);
                int index = -1;
                for (int j = 0; j < uniqueDays.size(); j++) {
                    if (uniqueDays.get(j).equalsIgnoreCase(searchItem)) {
                        index = j;
                        break;
                    }
                }
                if (index != -1) {
                    enableIndexList.add(index);
                }
            }
//                Toast.makeText(context, "22"+new Gson().toJson(enableIndexList), Toast.LENGTH_SHORT).show();

        }


//        for (String Day : uniqueDays) {
//            HashMap<String, ArrayList<String>> map = new HashMap<>();
//            map.put(Day, new ArrayList<String>());
//            venueMap.add(map);
//        }
//
////
//
//        for (TimeTable t : TimeTableList ){
//            if (t.getStarttime() ==  ) {
//                String venueName = t.getVenue();
//                for (HashMap<String, ArrayList<String>> map : venueMap) {
//                    ArrayList<String> venues = map.get(t.getDay());
//                    if (venues != null && venues.contains(venueName)) {
//                        venues.remove(venueName);
//                        break;
//                    }
//                }
//            }
//        }

//
//        for (String s:uniqueVenue) {
//            System.out.println("dddd"+s);
//        }

    }
    public AdminScheduleFreeSlotAdapter (ArrayList<TimeTable> TimeTableList, Context context, Fragment fragment ) {
        this.TimeTableList = TimeTableList;
        this.context = context;
        this.fragment=fragment;


    }
    @NonNull
    @Override
    public AdminScheduleFreeSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewRescheduleFreeSlotCellBinding b  =RecyclerviewRescheduleFreeSlotCellBinding.
                inflate(LayoutInflater
                                .from(parent.getContext()),
                        parent, false);

        AdminScheduleFreeSlotViewHolder vh = new AdminScheduleFreeSlotViewHolder(b);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminScheduleFreeSlotViewHolder holder, int position) {
       if(position==0){
           holder.bindDays(position);
       }
        holder.bind(position);
        String obj = uniqueStartTimes.get(position);
//        holder.binding.txtViewStart.setText(obj);

//        holder.binding.textViewVenue1.setText(obj.getNAME()+"");
//        holder.binding.textViewVenue2.setText(obj.getNAME()+"");
    }

    @Override
    public int getItemCount() {
        return uniqueStartTimes.size();
    }

    class AdminScheduleFreeSlotViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewRescheduleFreeSlotCellBinding binding;//<----

        public AdminScheduleFreeSlotViewHolder(
                @NonNull RecyclerviewRescheduleFreeSlotCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;//<----
        }
        //create column in row
        public void bind(int RowPosition) {
            binding.linearLayoutFreeSlot.removeAllViews();
            TextView textView = new TextView(context);
            // Set the text
            textView.setText(uniqueStartTimes.get(RowPosition)+"\n"+uniqueEndTimes.get(RowPosition));
            // Set the width and height
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;; // in pixels
            int height =150;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
            textView.setLayoutParams(layoutParams);
            // Set the gravity and layout gravity
            textView.setGravity(Gravity.CENTER);
            binding.linearLayoutFreeSlot.addView(textView);
            //create Spinner
            Spinner[] spinners = new Spinner[uniqueDays.size()];
            String[][] items = new String[uniqueDays.size()][];

            for (int i = 0; i < spinners.length; i++) {
//                Toast.makeText(context, ""+spinners.length, Toast.LENGTH_SHORT).show();
                TableRow.LayoutParams params = new TableRow.LayoutParams(
                        220,
                        TableRow.LayoutParams.MATCH_PARENT, 1f);
                params.gravity = Gravity.RIGHT;
// Create Spinners for each TableRow
                Spinner spinner = new Spinner(context);

                spinner.setLayoutParams(params);
//              unique venue
                List<String> timeTables1= new ArrayList<>();
                final  int finalvalue=i;
                timeTables1.add("");
                timeTables1.addAll(uniqueVenue);
                //remove venue
                    for (TimeTable t :TimeTableList){
                        if (t.getStarttime().toString().equals(uniqueStartTimes.get(RowPosition)) ) {
                            if (t.getDay().equals(uniqueDays.get(finalvalue))) {
                                if(timeTables1.equals(t.getVenue()));
                                timeTables1.remove(t.getVenue());

                            }
                        }
                    }
                //remove venue not free slot

//                use linq i
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    timeTables1 = uniqueVenue.stream()
//                            .filter(venue -> TimeTableList.stream()
//                                    .noneMatch(t -> t.getStarttime().equals(uniqueStartTimes.get(RowPosition))
//                                            && t.getDay().equals(uniqueDays.get(finalvalue))
//                                            && t.getVenue().equals(venue)))
//                            .collect(Collectors.toList());
//                }

                items[i] = timeTables1.toArray(new String[0]);

//                items[i] = new String[]{"", "Spinner " + i + " Item 1", "Spinner " + i + " Item 2", "Spinner " + i + " Item 3"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(),android.R.layout.simple_spinner_dropdown_item , items[i]);
//                adapter.setDropDownViewResource(R.layout.spinner_layout_timetable);
                spinner.setAdapter(adapter);
                final int finalI = i;
//                Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.teacher_recyclerview_background_cell);
//                spinner.setBackground(drawable);
//                spinner.setBackgroundResource(R.drawable.teacher_recyclerview_background_cell);
                spinners[i] = spinner;


                allSpinners[RowPosition][i] = spinner; // Store the spinner in the array
                Spinner emptySpinner = spinners[i];

                emptySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(ActivityName.contains("AdminScheduleReScheduleFreeSlotActivity")){
                            AdminScheduleReScheduleFreeSlotActivity reschedule= (AdminScheduleReScheduleFreeSlotActivity) context;
//                        Toast.makeText(reschedule, "start:"++"End:"++""+, Toast.LENGTH_SHORT).show();
                            reschedule.recyclerviewSelectVenueDrowpdown(emptySpinner.getSelectedItem().toString() ,uniqueStartTimes.get(RowPosition),uniqueEndTimes.get(RowPosition),uniqueDays.get(finalI),context );
                        }
                       else if(ActivityName.contains("AdminSchedulePreScheduleFreeSlotShowActivity")){
                            AdminSchedulePreScheduleFreeSlotShowActivity reschedule= (AdminSchedulePreScheduleFreeSlotShowActivity) context;
//                        Toast.makeText(reschedule, "start:"++"End:"++""+, Toast.LENGTH_SHORT).show();
                            reschedule.recyclerviewSelectVenueDrowpdown(emptySpinner.getSelectedItem().toString() ,uniqueStartTimes.get(RowPosition),uniqueEndTimes.get(RowPosition),uniqueDays.get(finalI),context );
                        }
                        else if(ActivityName.contains("AdminScheduleSwappingFreeSlotActivity")){
                            AdminScheduleSwappingFreeSlotActivity swapping= (AdminScheduleSwappingFreeSlotActivity) context;
//                        Toast.makeText(reschedule, "start:"++"End:"++""+, Toast.LENGTH_SHORT).show();
                            swapping.recyclerviewSelectVenueDrowpdown(emptySpinner.getSelectedItem().toString() ,uniqueStartTimes.get(RowPosition),uniqueEndTimes.get(RowPosition),uniqueDays.get(finalI),context );
                        }

                        if (position != 0) {
                            // Disable all other spinners
                            for (int row = 0; row < allSpinners.length; row++) {
                                for (int col = 0; col < allSpinners[row].length; col++) {
                                    Spinner spinner = allSpinners[row][col];
                                    if (spinner != null && spinner.getSelectedItem().toString().equals("")) {
                                        // Disable empty spinner
                                        spinner.setEnabled(false);
                                    } else if (row != RowPosition || col != finalI) {
                                        // Disable non-selected spinners
                                        spinner.setEnabled(false);
                                    } else {
                                        // Enable selected spinner
//                                        Toast.makeText(reschedule, "ll", Toast.LENGTH_SHORT).show();
                                            spinner.setEnabled(true);

                                    }
                                }
                            }

//                            Toast.makeText(context, "" + emptySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            // Enable all spinners
                            for (int row = 0; row < allSpinners.length; row++) {
                                for (int col = 0; col < allSpinners[row].length; col++) {
                                    Spinner spinnerIN = allSpinners[row][col];
                                    if (spinnerIN != null) {
                                        // specific column not enable
//                                        Toast.makeText(reschedule, "ff", Toast.LENGTH_SHORT).show();
                                        if(enableIndexList.size()!=0){
                                            for (int indexDay = 0; indexDay < enableIndexList.size(); indexDay++) {
                                                if (col==enableIndexList.get(indexDay)) { // Check if the spinner is at position
                                                    spinnerIN.setEnabled(true);
                                                }
                                        }
                                            }
                                        else{
                                                spinnerIN.setEnabled(true);
                                            }


//                                        if( col!=0 && col!=1 && col!=2 && col!=4){
//                                            spinner.setEnabled(true);
//                                        }

                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

//                if(finalI==2){
//                    Toast.makeText(context, "kk"+i, Toast.LENGTH_SHORT).show();
//                    emptySpinner.setEnabled(false);
//                }
//                Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
                for (int indexDay = 0; indexDay < enableIndexList.size(); indexDay++) {
                    if (i!=enableIndexList.get(indexDay)) { // Check if the spinner is at position
//                    Toast.makeText(context, "kk" + finalI, Toast.LENGTH_SHORT).show();
                        emptySpinner.setEnabled(false); // Disable the spinner at position
                    }
                }

                binding.linearLayoutFreeSlot.addView(spinner);

            }
        }

        //create end

        public void bindDays(int position) {
            binding.linearLayoutDayOfName.removeAllViews();
            TextView textViewFirst = new TextView(itemView.getContext());
            textViewFirst.setLayoutParams(new LinearLayout.LayoutParams(
                   120,
                    140,1f));

            // Set the gravity and layout gravity
            textViewFirst .setGravity(Gravity.CENTER);
            binding.linearLayoutDayOfName.addView(textViewFirst);
            for (int i = 0; i < uniqueDays.size(); i++) {
                TextView textView = new TextView(itemView.getContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                       100,1f));
                textView.setBackgroundResource(R.drawable.teacher_recyclerview_background_cell);
                textView.setTypeface(null, Typeface.BOLD);
                textView .setGravity(Gravity.CENTER);
                textView.setText(uniqueDays.get(i));
                binding.linearLayoutDayOfName.addView(textView);
            }
        }


    }
}