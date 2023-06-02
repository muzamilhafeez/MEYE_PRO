package com.example.meyepro.adapters.LiveCameraViewAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meyepro.R;
import com.example.meyepro.fragments.Admin.AdminHomeFragment;
import com.example.meyepro.models.CAMERA;

import org.videolan.libvlc.LibVLC;
import java.util.List;

public class LiveCameraAdapter extends RecyclerView.Adapter<LiveCameraAdapter.ViewHolder> {
    private Context context;
    private List<LiveCamera> cameras;
    private LibVLC libVLC;
    private Fragment fragment;
    private List<CAMERA> cameraslist;
    Spinner[][] allSpinners = new Spinner[2][5];


    public LiveCameraAdapter(Context context, LibVLC libVLC, List<LiveCamera> cameras) {
        this.context = context;
        this.cameras = cameras;
        this.libVLC = libVLC;


    }

    public LiveCameraAdapter(Context context, LibVLC libVLC, List<LiveCamera> cameras, Fragment fragment) {
        this.context = context;
        this.cameras = cameras;
        this.libVLC = libVLC;
        this.fragment = fragment;
    }
    public LiveCameraAdapter(Context context, LibVLC libVLC, List<LiveCamera> cameras,List<CAMERA> cameraslist, Fragment fragment) {
        this.context = context;
        this.cameras = cameras;
        this.libVLC = libVLC;
        this.fragment = fragment;
        this.cameraslist=cameraslist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_live_video_play_cell, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onViewRecycled(ViewHolder holder) {
        // Stop the camera player
        LiveCameraPlayer cameraPlayer = (LiveCameraPlayer) holder.cameraSurfaceView.getTag();


        if (cameraPlayer != null) {
            cameraPlayer.stop();
        }
    }

    @Override
    public int getItemCount() {
        return cameras.size();
    }
    EditText[][] allEditTexts = new EditText[5][7];
    int[][] array= new int[5][5];
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cameraName;
        public SurfaceView cameraSurfaceView;

        LinearLayout linearLayout;
        LinearLayout linearLayoutLive;
        TextView textView ;
        public ViewHolder(View view) {
            super(view);
          //  cameraName = view.findViewById(R.id.camera_name);
            cameraSurfaceView = view.findViewById(R.id.camera_surface_view);
            textView = view.findViewById(R.id.text_view);
            linearLayout=view.findViewById(R.id.lineraLayout);
            linearLayoutLive=view.findViewById(R.id.lineraLayout_Live);
        }

//        private Spinner[][] spinners; // declare as a member variable

        // Declare a two-dimensional array to store all the spinners


//        public void bind(int RowPosition) {
//            linearLayout.removeAllViews();
//            Spinner[] spinners = new Spinner[5];
//            String[][] items = new String[5][];
//
//            for (int i = 0; i < spinners.length ; i++) {
//                TableRow.LayoutParams params = new TableRow.LayoutParams(
//                        220,
//                        TableRow.LayoutParams.MATCH_PARENT, 1f);
//                params.gravity = Gravity.RIGHT;
//// Create Spinners for each TableRow
//                Spinner spinner = new Spinner(context);
//                spinner.setLayoutParams(params);
//
//// Create Spinners for each TableRow
////                Spinner tuesdaySpinner = new Spinner(context);
////                Spinner spinner = new Spinner(itemView.getContext());
////                spinner.setLayoutParams(new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT));
////                spinner.setLayoutParams(new LinearLayout.LayoutParams(
////                        LinearLayout.LayoutParams.WRAP_CONTENT-10,
////                        LinearLayout.LayoutParams.WRAP_CONTENT));
//                items[i] = new String[] {"", "Spinner " + i + " Item 1", "Spinner " + i + " Item 2", "Spinner " + i + " Item 3"};
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, items[i]);
////                adapter.setDropDownViewResource(R.layout.spinner_layout_timetable);
//                spinner.setAdapter(adapter);
//                final int finalI = i;
////                Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.teacher_recyclerview_background_cell);
////                spinner.setBackground(drawable);
////                spinner.setBackgroundResource(R.drawable.teacher_recyclerview_background_cell);
//                spinners[i] = spinner;
//
//                allSpinners[RowPosition][i] = spinner; // Store the spinner in the array
//                Spinner emptySpinner = spinners[i];
//                emptySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if (position != 0) {
//                            // Disable all other spinners
//                            for (int row = 0; row < allSpinners.length; row++) {
//                                for (int col = 0; col < allSpinners[row].length; col++) {
//                                    Spinner spinner = allSpinners[row][col];
//                                    if (spinner != null && spinner.getSelectedItem().toString().equals("")) {
//                                        // Disable empty spinner
//                                        spinner.setEnabled(false);
//                                    } else if (row != RowPosition || col != finalI) {
//                                        // Disable non-selected spinners
//                                        spinner.setEnabled(false);
//                                    } else {
//                                        // Enable selected spinner
//                                        spinner.setEnabled(true);
//                                    }
//                                }
//                            }
//                            Toast.makeText(context, ""+emptySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//                        } else {
//                            // Enable all spinners
//                            for (int row = 0; row < allSpinners.length; row++) {
//                                for (int col = 0; col < allSpinners[row].length; col++) {
//                                    Spinner spinner = allSpinners[row][col];
//                                    if (spinner != null) {
//                                        spinner.setEnabled(true);
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//                linearLayout.addView(spinner);
//            }
//        }

        public void bind(int position) {
            linearLayout.removeAllViews();
            TableRow tableRow = new TableRow(context);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            ));
            for (int i = 0; i < 5; i++) {
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                EditText editText = new EditText(itemView.getContext());
                editText.setLayoutParams(layoutParams);
                editText.setHint("Text " + i);
                int finalI = i;
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(charSequence.length()>0) {
                            try {

                                array[position][finalI] = Integer.parseInt(charSequence.toString());
                                int columnIndex = finalI; // specify the column index you want to count
                                int count = 0;
                                for (int CountIndex = 0; CountIndex < array.length; CountIndex++) {
                                    count += array[CountIndex][columnIndex];
                                }
                                Toast.makeText(context, "" + count, Toast.LENGTH_SHORT).show();
                                System.out.println("The total count of column " + columnIndex + " is " + count);
                            } catch (NumberFormatException e) {
                                Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show();
                            }
                        }else
                        {
                            array[position][finalI] = 0;
                        }
//                        Toast.makeText(itemView.getContext(), "EditText "  +charSequence+"2D ["+position+"]["+col+"]", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                        //  Toast.makeText(itemView.getContext(), "EditText "  +editable, Toast.LENGTH_SHORT).show();
                    }
                });
                tableRow.addView(editText);

                allEditTexts[position][i] = editText; // Store the EditText in the array
            }
            linearLayout.addView(tableRow);
        }



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LiveCamera camera = cameras.get(position);
        if (holder != null && holder.itemView != null) {
            String url = String.valueOf(cameras.get(position));
//            holder.cameraName.setText(camera.getName());
            for (int i=0;i<5;i++) {
                holder.textView.setText(""+i+camera.getName());
            }
            // Initialize the camera player
            LiveCameraPlayer cameraPlayer = new LiveCameraPlayer(libVLC, holder.cameraSurfaceView, camera.getUrl());
            holder.cameraSurfaceView.setTag(cameraPlayer);
            cameraPlayer.start();
//create edittext
//                holder.bind(position);
            holder.linearLayoutLive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdminHomeFragment AdminHome = (AdminHomeFragment) fragment;
                    AdminHome.recyclerviewAdminHomeViewLiveCellClick(fragment);
                }
            });



        }

    }
}
