package com.example.meyepro.StudentDashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.StudentDashBoard.Adapter.StudentCourseAdapter;
import com.example.meyepro.StudentDashBoard.Adapter.StudentNotificationAdapter;
import com.example.meyepro.StudentDashBoard.Course.StudentCourseFragment;
import com.example.meyepro.StudentDashBoard.Model.StudentCourse;
import com.example.meyepro.StudentDashBoard.Model.StudentNotification;
import com.example.meyepro.StudentDashBoard.Notification.StudentNotificationActivity;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.ActivityStudentDashBoardBinding;
import com.example.meyepro.login;
import com.example.meyepro.models.MEYE_USER;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentDashBoardActivity extends AppCompatActivity {
ActivityStudentDashBoardBinding binding;

    int notification=0;
    int count=0;
    ArrayList<StudentNotification> notificationArrayList= new ArrayList<StudentNotification>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStudentDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String IntentData= getIntent().getStringExtra("IntentData");
        MEYE_USER user= new Gson().fromJson(IntentData,MEYE_USER.class);

        RetrofitClient retrofitClient= RetrofitClient.getInstance();
        Api api= retrofitClient.getMyApi();
//        Toast.makeText(this, ""+ login.userInfoDetail.getUserID(), Toast.LENGTH_SHORT).show();
        api.api_get_student_notification_data(login.userInfoDetail.getUserID()).enqueue(new Callback<ArrayList<StudentNotification>>() {
            @Override
            public void onResponse(Call<ArrayList<StudentNotification>> call, Response<ArrayList<StudentNotification>> response) {
//                Toast.makeText(getApplicationContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
//                    notifyThis("ali", "muzamil");
                    notificationArrayList.clear();
                    notificationArrayList.addAll(response.body());
//                    showMultipleNotifications(notificationArrayList);
                    // Initialize the handler and the runnable
                    showNotification();
                    /****** Create Thread that will sleep for 5 seconds****/
                    Thread background = new Thread() {
                        public void run() {
                            try {
                                while (true) {
                                    sleep(5 * 1000);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Update the notificationArrayList here
                                            // ...


                                            api.api_get_attendance_notification("2019-ARID-2931").enqueue(new Callback<Integer>() {
                                                @Override
                                                public void onResponse(Call<Integer> call, Response<Integer> response) {
                                                    if(response.isSuccessful()){
//                                                        Toast.makeText(StudentDashBoardActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                                                        if(response.body()!=-1&& notification!=response.body()){
                                                            showMultipleNotifications(notificationArrayList);

//                                                            notification= response.body();
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<Integer> call, Throwable t) {

                                                }
                                            });

                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    background.start();



//                    for (StudentNotification student: notificationArrayList) {
//
//                        showNotification(student.getCourseName(),student.getName()+"\n"+student.getDate());
//                    }


//                    Toast.makeText(StudentNotificationActivity.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();
//                    StudentNotificationAdapter adapter = new
//                            StudentNotificationAdapter(response.body(), StudentNotificationActivity.this);
//                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
//                    binding.RecycerviewStudentNotification.setLayoutManager(manager);
//                    binding.RecycerviewStudentNotification.setHasFixedSize(true);
//                    binding.RecycerviewStudentNotification.
//                            setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StudentNotification>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
        binding.ImageViewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), StudentNotificationActivity.class);
                startActivity(i);
            }
        });
        binding.txtViewStudentName.setText(user.getName());
        if(!user.getImage().isEmpty()){
            Picasso.get().load(Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Student"+"/"+user.getImage())).into(binding.profileImageStudent);
        }
        // Create a new Bundle object with arguments
        Bundle bundle = new Bundle();
        bundle.putString("IntentData", IntentData);

        // Create a new instance of the StudentCourseFragment class
        StudentCourseFragment fragment = new StudentCourseFragment();

        // Set the arguments for the fragment
        fragment.setArguments(bundle);

        // Load the fragment
        loadFragment(fragment);
    }

    private void loadFragment(Fragment f){
        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();

        //manage back track
//        ft.add(R.id.frament_container_admin, f);
        ft.replace(R.id.frament_container_admin, f);
        //   ft.addToBackStack(null);
        ft.commit();
    }
    private String channelId = "my_channel";
    private NotificationManager notifManager;
// array list show data
    private void showMultipleNotifications(ArrayList<StudentNotification> notificationArrayList) {
        int notificationId = 1;
        final int NOTIFY_ME_ID = 1337;

        /*********** Create notification channel ***********/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "MyChannel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Bitmap bitmap = null;
        /*********** Create notifications ***********/
        for (StudentNotification student : notificationArrayList) {
            String imageURL = Api.BASE_URL + "api/get-user-image/UserImages/" + "Student" + "/" + student.getImage();
//           Uri uri= Uri.parse(Api.BASE_URL+"api/get-user-image/UserImages/"+"Student"+"/"+student.getImage());
//            try {
//                URL url = new URL(imageURL);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream inputStream = connection.getInputStream();
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                inputStream.close();
//                connection.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
           NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(student.getCourseName())
                    .setContentText(student.getName() + "\n" + student.getDate())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true);

            // This pending intent will open after notification click
            Intent intent = new Intent(this, StudentNotificationActivity.class);
            PendingIntent i = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(i);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(notificationId++, builder.build());
        }
    }

//  public  void notifyThis(String title, String message){
//      Intent intent = new Intent(getApplicationContext(), SomeActvity.class);
//      PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(),
//              (int) System.currentTimeMillis(), intent, 0);
//
//      NotificationCompat.Builder mBuilder =
//              new NotificationCompat.Builder(getApplicationContext())
//                      .setSmallIcon(R.drawable.your_notification_icon)
//                      .setContentTitle("Notification title")
//                      .setContentText("Notification message!")
//                      .setContentIntent(pIntent);
//
//      NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//      notificationManager.notify(0, mBuilder.build());
//
//  }
private void showNotification() {
//    int notificationId = 1;
//    final int NOTIFY_ME_ID=1337;
//    /*********** Create notification channel ***********/
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        NotificationChannel channel = new NotificationChannel(channelId, "MyChannel", NotificationManager.IMPORTANCE_DEFAULT);
//        NotificationManager notificationManager = getSystemService(NotificationManager.class);
//        notificationManager.createNotificationChannel(channel);
//    }
//    /*********** Create notification ***********/
//    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("Android Example Notification Title")
//            .setContentText("This is the android example notification message")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setAutoCancel(true);
//
//    // This pending intent will open after notification click
//    Intent intent = new Intent(this,StudentNotificationActivity.class);
//    PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this, StudentNotificationActivity.class), PendingIntent.FLAG_IMMUTABLE);
//
//    builder.setContentIntent(i);
//
//    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//    notificationManagerCompat.notify(NOTIFY_ME_ID, builder.build());

    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

// Notification 1
    NotificationCompat.Builder notificationBuilder1 = new NotificationCompat.Builder(this, "normal")
            .setContentTitle("Notification 1")
            .setContentText("This is the content for Notification 1")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
            .setAutoCancel(true);

    Notification notification1 = notificationBuilder1.build();
    notificationManager.notify(1, notification1);

// Notification 2
    NotificationCompat.Builder notificationBuilder2 = new NotificationCompat.Builder(this, "normal")
            .setContentTitle("Notification 2")
            .setContentText("This is the content for Notification 2")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
            .setAutoCancel(true);

    Notification notification2 = notificationBuilder2.build();
    notificationManager.notify(2, notification2);

// Notification 3
    NotificationCompat.Builder notificationBuilder3 = new NotificationCompat.Builder(this, "normal")
            .setContentTitle("Notification 3")
            .setContentText("This is the content for Notification 3")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
            .setAutoCancel(true);

    Notification notification3 = notificationBuilder3.build();
    notificationManager.notify(3, notification3);

}

}