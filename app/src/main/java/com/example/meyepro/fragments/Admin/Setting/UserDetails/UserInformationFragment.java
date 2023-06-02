package com.example.meyepro.fragments.Admin.Setting.UserDetails;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.api.RetrofitClient;
import com.example.meyepro.databinding.AddDvrCustomLayoutBinding;
import com.example.meyepro.databinding.CameraAndGalleyOpenCustomDesignBinding;
import com.example.meyepro.databinding.FragmentUserInformationBinding;
import com.example.meyepro.models.MEYE_USER;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserInformationFragment extends Fragment {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    FragmentUserInformationBinding binding;
    // Define a request code for capturing an image
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final int REQUEST_IMAGE_SELECT = 2;
    byte[] imgeArr;
    Uri imgeUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentUserInformationBinding.inflate(getLayoutInflater());
        //code start

        binding.floatingbtnCameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open camera code


                if (hasCameraPermission()) {
                  CameraAndGalleyOpenCustomDesignBinding Camera_and_gallery_Binding=CameraAndGalleyOpenCustomDesignBinding.inflate(getLayoutInflater());
                    // Create dialog and set content view to custom layout
                    Dialog customDialog = new Dialog(getActivity(),R.style.Model);
                    customDialog.setContentView(Camera_and_gallery_Binding.getRoot());
                    // Set additional properties for dialog
                    customDialog.setTitle("Custom Dialog");
                    customDialog.setCancelable(true);
                    customDialog.setCanceledOnTouchOutside(true);
                    customDialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
                    // customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    //btn camera
                    Camera_and_gallery_Binding.btnCameraCustom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //code

                            //open camera code
                            Intent intent = new Intent(
                                    MediaStore.ACTION_IMAGE_CAPTURE);

                            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                            customDialog.dismiss();

                        }
                    });
                    //btn gallery
                    Camera_and_gallery_Binding.btnGalleryCustom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //code
                            //open gallery code
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, REQUEST_IMAGE_SELECT);
                            customDialog.dismiss();
                        }
                    });
                    // Show dialog
                    customDialog.show();

                } else {
                    requestCameraPermission();
                }


//            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                }
            }
        });
        // Launch the camera app to capture an image

        RetrofitClient client= RetrofitClient.getInstance();
        Api api= client.getMyApi();

        String data[] = {"Teacher","Admin","Student"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, data);
        binding.spinnerUserRole.setAdapter(adapter);
       //save btn
binding.btnSave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String userid = binding.txtUserId.getText().toString();
        String name = binding.txtUserName.getText().toString();
        String password = binding.txtUserPassword.getText().toString();
        String role = binding.spinnerUserRole.getSelectedItem().toString();
        String image="";
        MultipartBody.Part  image3 = null;
       // ArrayList<MultipartBody.Part> imageList =
        //        new ArrayList<>();
     //   File file = new File(getRealPathFromURI(getContext(), imgeUri));

// Convert the File to a RequestBody object
       // RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);


        try {
            //image uri save imageuri variable
            if(imgeUri!=null){
             //   Toast.makeText(getActivity(), "eee", Toast.LENGTH_SHORT).show();
                //image passing by part
                 image3 =
                        api.prepareFilePart("file",
                                imgeUri,
                                getContext());
               // imageList.add(image3);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        MEYE_USER user =new MEYE_USER(0,userid,name,image,password,role) ;


        if(userid!=null&& name!=null& password!=null&& role!=null& image!=null){

        }

        // Create request body for fields
//        RequestBody idBody = RequestBody.create(MediaType.parse("text/plain"), "1");
//        RequestBody userIDBody = RequestBody.create(MediaType.parse("text/plain"), "ali");
//        RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), "ali");
//        RequestBody imagebody = RequestBody.create(MediaType.parse("text/plain"), "ali");
//        RequestBody passwordBody = RequestBody.create(MediaType.parse("text/plain"), "123");
//        RequestBody roleBody = RequestBody.create(MediaType.parse("text/plain"), "Teacher");

          Toast.makeText(getActivity(), "api", Toast.LENGTH_SHORT).show();

//        Uri imageUri = imgeUri;
//        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(imageUri.getPath()));
//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", "image.jpg", fileBody);
//        File imageFile = new File(getRealPathFromURI(imageUri));
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
//        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
//        api.createUser(user, image3 ).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Toast.makeText(getActivity(), "tt"+response.code(), Toast.LENGTH_SHORT).show();
//                if (response.code() == 200) {
//
//                    Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });

        if(binding.spinnerUserRole.getSelectedItem().toString().contains("Student")){
            api.add_student(0, userid, name , "0",password,
                    image3).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getActivity(), +response.code()+response.body(), Toast.LENGTH_SHORT).show();
//                if(image3!=null){
//
//                }
                    if (response.code() == 200) {
                        String map=response.body();
//                    Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "tt"+t.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("dddddddd"+t.toString());
                }
            });
        }else  if(binding.spinnerUserRole.getSelectedItem().toString().contains("Teacher")){
            api.SaveUser(0, userid, name , password, role,
                    image3).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getActivity(), "tt"+response.code()+response.body(), Toast.LENGTH_SHORT).show();
//                if(image3!=null){
//
//                }
                    if (response.code() == 200) {
                        String map=response.body();
//                    Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "tt"+t.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("dddddddd"+t.toString());
                }
            });
        }else  if(binding.spinnerUserRole.getSelectedItem().toString().contains("Director")){
            api.SaveUser(0, userid, name , password, role,
                    image3).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getActivity(), "tt"+response.code()+response.body(), Toast.LENGTH_SHORT).show();
//                if(image3!=null){
//
//                }
                    if (response.code() == 200) {
                        String map=response.body();
//                    Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getActivity(), "tt"+t.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("dddddddd"+t.toString());
                }
            });
        }




//        api.saveUserDetail( api.createPartFromString("0"),api.createPartFromString(userid),
//                api.createPartFromString(name),
//                api.createPartFromString(image),
//                api.createPartFromString(password),
//                api.createPartFromString(role) ,image3 ).enqueue(new Callback<RequestBody>() {
//            @Override
//            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
//                Toast.makeText(getActivity(), "tt"+response.code(), Toast.LENGTH_SHORT).show();
//                if (response.code() == 200) {
//
//                    Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RequestBody> call, Throwable t) {
//
//            }
//        });
//
//            @Override
//            public void onFailure(Call<RequestBody> call, Throwable t) {
//                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//        api.SaveUser(user).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//                if (response.code() == 200) {
//
//                    Toast.makeText(getContext(), "Save data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
});
        //api


        //code end
        return binding.getRoot();
    }


    // Helper method to convert a Uri to a File path
    private String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        cursor.close();
        return filePath;
    }
    // Handle the result of the image capture
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
////            Bundle extras = data.getExtras();
////            Bitmap imageBitmap = (Bitmap) extras.get("data");
////
////            // Convert the image to a byte array
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
////            byte[] imageBytes = stream.toByteArray();
//
//            // Save the image in binary format
//            // You can write the bytes to a file or store them in a database
//            // For example, to write the bytes to a file:
////            try {
////                FileOutputStream fos = new FileOutputStream("/path/to/image.jpg");
////                fos.write(imageBytes);
////                fos.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
//    }
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);//CAMERA_PERMISSION_REQUEST_CODE=100
    }
    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 @Nullable Intent data) {
//camera user
        if(requestCode == REQUEST_IMAGE_CAPTURE){
            if(resultCode == Activity.RESULT_OK){
                Bundle bundleObj = data.getExtras();
                Bitmap bmpImage = (Bitmap) bundleObj.get("data");
                binding.imageViewUser.setImageBitmap(bmpImage);
                //save database convert byte
                BitmapDrawable bmpDrawble = (BitmapDrawable)
                        binding.imageViewUser.getDrawable();
                // Bitmap bmpImage = bmpDrawble.getBitmap();
                ByteArrayOutputStream outputStream =
                        new ByteArrayOutputStream();
                bmpImage.compress(Bitmap.CompressFormat.PNG,
                        100,outputStream);
                imgeArr = outputStream.toByteArray();
                //byte to bitmap

            }
        }//gallery use code
        else if(requestCode == REQUEST_IMAGE_SELECT){
            if(resultCode == Activity.RESULT_OK){
                 imgeUri = data.getData();
                binding.imageViewUser.setImageURI(imgeUri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Handle the result of the activity
  //  @Override
   // public void onActivityResult(int requestCode, int resultCode, Intent data) {
   //     super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == REQUEST_IMAGE_CAPTURE) {
//                // Image captured and saved to fileUri specified in the Intent
//                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//                byte[] imageBytes = outputStream.toByteArray();
//                // Now you can use the imageBytes as needed
//            } else if (requestCode == REQUEST_IMAGE_SELECT) {
//                // Image selected from gallery
//                Uri selectedImageUri = data.getData();
//                Bitmap bitmap = null;
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//                byte[] imageBytes = outputStream.toByteArray();
//                // Now you can use the imageBytes as needed
//            }
//        }
  //  }
}