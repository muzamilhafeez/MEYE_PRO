package com.example.meyepro.api;

import android.content.Context;
import android.net.Uri;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import androidx.annotation.NonNull;

import com.example.meyepro.DirectorDashBoard.Model.ScheduleDetailsAndCHR;
import com.example.meyepro.StudentDashBoard.Model.StudentCourse;
import com.example.meyepro.StudentDashBoard.Model.StudentNotification;
import com.example.meyepro.StudentDashBoard.Model.StudentOFCourseAttendance;
import com.example.meyepro.models.Attendance;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.ClaimVideo;
import com.example.meyepro.models.DVR;
import com.example.meyepro.models.DemoVideosResponse;
import com.example.meyepro.models.Get_Rules_Timetable;
import com.example.meyepro.models.MEYE_USER;
import com.example.meyepro.models.PreSchedule;
import com.example.meyepro.models.Reschedule;
import com.example.meyepro.models.Rules;
import com.example.meyepro.models.SectionOffer;
import com.example.meyepro.models.Student;
import com.example.meyepro.models.Swapping;
import com.example.meyepro.models.SwappingUser;
import com.example.meyepro.models.TeacherDemoCHR;
import com.example.meyepro.models.TimeTable;
import com.example.meyepro.models.recordings_details_by_teachername;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    public static String BASE_URL="http://192.168.100.5:8000/";

    //start function
    @GET("api/signin")
    public Call<MEYE_USER> loginUser(@Query("userId") String user, @Query("password") String pass);

    @GET("api/recordings-details-by-teachername/{teacherName}")
    public Call<ArrayList<recordings_details_by_teachername>> recordings_details_by_teachername (@Path("teacherName") String teacherName);

    @GET("api/get-rules-timetable/{teacherName}")
    public Call<Get_Rules_Timetable> get_rules_timetable (@Path("teacherName") String teacherName);

    @GET("api/teacher-timetable-details/{teacherName}")
    public Call<ArrayList<TimeTable>> timetable_teacher_details (@Path("teacherName") String teacherName);

    @GET("api/get-attendance-notification")
    public Call<Integer> api_get_attendance_notification (@Query("aridNumber") String aridNumber);

    @GET("api/timetable-details")
    public Call<ArrayList<TimeTable>> All_timetable_teacher_details ();

    @GET("api/section-offer-details")
    public Call<ArrayList<SectionOffer>> Section_Offer_details ();

    @GET("api/demo")
    public Call<ArrayList<TeacherDemoCHR>> api_demo ();

    @GET("api/check-teacher-reschedule")
    public Call<String> check_teacher_reschedule (@Query("teacherName") String teacherName );

    @GET("api/teacher-claim")
    public Call<ArrayList<ClaimVideo>> api_teacher_claim(@Query("teacherSlotId") int teacherSlotId );

    @GET("api/demovideos")
    public Call<DemoVideosResponse> api_demovideos(@Query("file") String file );

    @GET("api/get-student-notification-data")
    public Call<ArrayList<StudentNotification>> api_get_student_notification_data(@Query("aridNumber") String aridNumber );

    @GET("api/get-teacher-notification-data")
    public Call<ArrayList<StudentNotification>> api_get_teacher_notification_data(@Query("teacherName") String aridNumber );
    @GET("api/dvr-details/")
   public Call<ArrayList<DVR>> dvr_details ();
    @GET("api/book/getbook")
    public Call<String> dvr_details2 ();
    //http://192.168.235.67:8000/api/user-details
    @GET("api/user-details")
    public Call<ArrayList<MEYE_USER>> user_details ();
    @GET("api/camera-details/{dvrID}")
    public Call<Map<String,ArrayList<CAMERA>>> Camera_details (@Path("dvrID") Integer dvrID);

    @GET("api/get-student-courses")
    public Call<String> get_student_courses( @Query("aridNumber") String aridNumber);

    @GET("api/get-course-attendance")
    public Call<ArrayList<StudentOFCourseAttendance>> get_course_attendance(@Query("aridNumber") String aridNumber, @Query("courseName") String courseName);

    @PUT("api/update-reschedule")
    public Call<Reschedule> update_reschedule(@Body Reschedule reschedule);

    @POST("api/add-reschedule")
    public Call<Map<String,String>> add_reschedule(@Body Reschedule reschedule);
    @POST("api/add-preschedule")
    public Call<Map<String,String>> add_preschedule(@Body PreSchedule reschedule);
    @POST("api/add-swapping")
    public Call<Map<String,String>> add_swapping(@Body Swapping swapping);

    @POST("api/student-attendance-claim")
    public Call<String> api_student_attendance_claim(@Body RequestBody claim);

    @GET("api/get-swapping-teacher-data")
    public Call<String> get_swapping_teacher_data(@Query("day") String day, @Query("startTime") String startTime, @Query("endTime") String endTime, @Query("timeTableId") int timeTableId);

    @PUT("api/update-camera-details")
    public Call<Map<String,CAMERA>> update_camera_details(@Body CAMERA camera);

    @GET("api/update-attendance")
    public Call<String> api_update_attendance(@Query("attendanceId") int attendanceId,@Query("status") Boolean status);

    @GET("api/get-teacher-chr")
    public Call<ArrayList<ScheduleDetailsAndCHR>> get_teacher_chr(@Query("teacherName") String teacherName);
    @GET("api/get-all-teacher-chr")
    public Call<String> get_all_teacher_chr();
    @POST("api/add-camera")
    public Call<CAMERA> saveCamera(@Body CAMERA camera);
    @POST("api/add-dvr")
    public Call<DVR> saveDVR(@Body DVR dvr);
    @PUT("api/update-dvr-details")
    public Call<DVR> update_dvr_details(@Body DVR dvr);
    @HTTP(method = "DELETE", path = "api/delete-dvr-details", hasBody = true)
    public Call<Map<String,String>> delete_dvr_details(@Body DVR dvr);

    @HTTP(method = "DELETE", path = "api/delete-camera-details", hasBody = true)
    public Call<Map<String,String>> delete_camera_details(@Body CAMERA camera);

    @POST("api/student-enroll")
    public Call<String> student_enroll(@Body RequestBody jsonArray);

    @POST("api/add-rules/{teacherName}")
    public Call<String> add_rules(@Path("teacherName") String teacherName, @Body ArrayList<Rules> rulesArrayList);

    @POST("api/add-attendance")
    public Call<String> add_attendance(@Body ArrayList<Attendance> attendanceList);

    @POST("api/student-offered-courses")
    public Call<ArrayList<Student>> student_offered_courses(@Body ArrayList<String> CourseName);
//    @POST("add-user/")
//    public Call<String> SaveUser(@Body MEYE_USER user);
@Multipart
@POST("api/add-user")
Call<Void> createUser(
        @Query("user") MEYE_USER user,
        @Part MultipartBody.Part file
);
    @Multipart
    @POST("api/add-user")
    Call<String> SaveUser(
            @Query("id") int  id,
            @Query("userID") String userID,
            @Query("name") String name,
            @Query("password") String password,
            @Query("role") String role,
            @Part MultipartBody.Part file);


    @Multipart
    @POST("api/add-student")
    Call<String> add_student(
            @Query("id") int  id,
            @Query("aridNo") String aridNo,
            @Query("name") String name,
            @Query("image") String image,
            @Query("password") String password,
            @Part MultipartBody.Part file);

    @Multipart
    @POST("api/add-timetable")
    public Call<Map<String,String>> UploadTimetable(
            @Part MultipartBody.Part file);
    @Headers("Timeout: 60000")
    @Multipart
    @POST("api/mark-attendance")
    public Call<ArrayList<Attendance>> mark_attendance(
            @Part MultipartBody.Part file);



//    @Multipart
//    @POST("api/add-user")
//    public Call<RequestBody> saveUserDetail(
//                                            @Part("id") RequestBody id,
//                                            @Part("userID") RequestBody userID,
//                                            @Part("name") RequestBody name,
//                                            @Part("image") RequestBody image,
//                                            @Part("password") RequestBody password,
//                                            @Part("role") RequestBody role,
//                                            @Part MultipartBody.Part file);

    //end Function
    //public static String BASE_URL="https://jsonplaceholder.typicode.com/";
//    @GET("math/getuser/{id}")
//    public Call<List<User>> getUser(@Path("id") int id);
//    @GET("math/getusers")
//    public Call<List<User>> getUsers();
//    @GET("Customer/GetAllCus")
//    public Call<List<Customer>> GetAllCus();
//
//    @POST("Customer/SaveCustomer/")
//    public Call<String> saveCustomer(@Body Customer c);
//
//
//
//    @GET("math/divide")
//    public Call<Integer> divide(@Query("n1") int n1, @Query("n2") int n2);
//    @POST("book/getbook")
//    public Call<String> getbook(@Body book user);
//    @GET("book/SaveBook")
//    public Call<String> savebook(@Query("id") int id, @Query("title") String title , @Query("price") int price, @Query("qty") int qty);
//    @GET("Customer/getallcustomers")
//    public Call<ArrayList<Customer>> getAllCustomers();
//    @GET("book/SaveClass")
//    public Call<String> SaveClass(@Query("CName") String CName);
//    @GET("book/getclass")
//    public Call<ArrayList<Map<String,ArrayList<patientsfullrecord>>>> getjSection();
//
//    @POST("/video3")
//    @Multipart
//    Call<ResponseBody> uploadVideo(@Part MultipartBody.Part video);
//    //@POST("/live-video")
//    //Call<LiveVideoResponse> sendLiveVideo(@Body LiveVideoRequest request);
//    //testing
//    @GET("posts")
//    public Call<List<Comments>> getData();
//    @GET("book/GetImage")
//    public Call<String> getimage();
//    @Multipart
//    @POST("book/Postflower")
//    public Call<RequestBody> saveimg(@Part ArrayList<MultipartBody.Part> part, @Part("jj")  ArrayList<Customer> h);
//
    @FormUrlEncoded
    @NonNull
    public default MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) throws IOException, IOException {
        File file = FileUtil.from(context, fileUri);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    public default RequestBody createPartFromString(String descriptionString){
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
        return  description;

    }

//    public class LiveVideoRequest {
//        private String videoData;
//
//        public LiveVideoRequest(String videoData) {
//            this.videoData = videoData;
//        }
//    }


}

