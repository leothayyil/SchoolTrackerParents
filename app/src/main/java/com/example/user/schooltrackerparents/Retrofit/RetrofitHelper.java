package com.example.user.schooltrackerparents.Retrofit;

import com.example.user.schooltrackerparents.AttendanceActivity;
import com.example.user.schooltrackerparents.GeneralMsgsActivity;
import com.example.user.schooltrackerparents.LoginActivity;
import com.example.user.schooltrackerparents.ProfileActivity;
import com.example.user.schooltrackerparents.ProgressReportActivity;
import com.example.user.schooltrackerparents.TeacherMsgsActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by USER on 01-02-2018.
 */

public class RetrofitHelper {
    private static API api;

    public RetrofitHelper(LoginActivity loginActivity) {
        initRestAdapter();
    }

    public RetrofitHelper(ProfileActivity profileActivity) {
        initRestAdapter();
    }

    public RetrofitHelper(AttendanceActivity attendanceActivity) {
        initRestAdapter();
    }

    public RetrofitHelper(ProgressReportActivity progressReportActivity) {
        initRestAdapter();
    }

    public RetrofitHelper(TeacherMsgsActivity teacherMsgsActivity) {
        initRestAdapter();
    }

    public RetrofitHelper(GeneralMsgsActivity generalMsgs) {
        initRestAdapter();
    }

    public RetrofitHelper(GeneralMsgsActivity.GeneralMsgs generalMsgs) {
        initRestAdapter();
    }


    public static API getApi() {
        return api;
    }

    public static void setApi(API api) {
        RetrofitHelper.api = api;
    }
    private void initRestAdapter(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://comcubeindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setApi(retrofit.create(API.class));
    }
}
