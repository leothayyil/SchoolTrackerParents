package com.example.user.schooltrackerparents;

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
    Call<JsonElement>getClass(@Field("action") String action);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getDivision(@Field("action") String action, @Field("user_id") int classa);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getStudeList(@Field("action") String action, @Field("user_id") String user_id, @Field("class") String classa,
                                  @Field("division") String division);

    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getAttendance(@Field("action") String action, @Field("user_id") String userId);
    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>getAttReport(@Field("action") String action, @Field("user_id") String userId, @Field("year") String year,
                                   @Field("month") String month);


    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>sentMessage(@Field("action") String action, @Field("user_id") String userId, @Field("message_type") String msgType,
                                 @Field("title") String msgTitle, @Field("message") String msgMsg, @Field("student") String student,
                                 @Field("class") String classa, @Field("division") String division);
    @FormUrlEncoded
    @POST("/sample/school_app/api/parents_api.php")
    Call<JsonElement>replyDetails(@Field("action") String action, @Field("user_id") String userId, @Field("message_id") String msgId);
}
