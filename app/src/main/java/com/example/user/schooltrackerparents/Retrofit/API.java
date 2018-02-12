package com.example.user.schooltrackerparents.Retrofit;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by USER on 01-02-2018.
 */

public interface API {

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>login(@Field("action") String action, @Field("user_name") String user_name, @Field("password") String password);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getProfile(@Field("action") String action, @Field("user_id") int userId);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getProgress_page(@Field("action") String action,@Field("user_id") String userId);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getExams(@Field("action") String action);


    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getProgressReport(@Field("action") String action, @Field("user_id") String user_id, @Field("exam") String exam,
                                  @Field("year") String year);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getAttendance(@Field("action") String action, @Field("user_id") String userId);
    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getAttReport(@Field("action") String action, @Field("user_id") String userId, @Field("year") String year,
                                   @Field("month") String month);
    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getMsgDetails(@Field("action") String action, @Field("user_id") String userId, @Field("message_id") String message_id);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>sentReply(@Field("action") String action, @Field("message_id") String message_id,
                               @Field("title") String title,@Field("reply") String reply);
    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getGeneralMsg(@Field("action")String action, @Field("user_id") String userId,
                                   @Field("class") String classa, @Field("division") String division);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getMessages(@Field("action") String action, @Field("user_id") String userId);
}
