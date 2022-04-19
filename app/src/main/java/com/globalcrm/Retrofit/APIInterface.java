package com.globalcrm.Retrofit;

import com.globalcrm.Model.FollowupResonse;
import com.globalcrm.Model.LoginResponse;
import com.globalcrm.Model.OTPGSM;
import com.globalcrm.Model.OTPWPWR;
import com.globalcrm.Model.OtpResponse;
import com.globalcrm.Model.ResponseAllTodayAppoitment;
import com.globalcrm.Model.ResponseExecutive;
import com.globalcrm.Model.ResponseTodaySale;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIInterface {

//    @POST("app_inquiry.php")
//    Call<InquriyResponse> createInquriy(@Body User user);
//
////    @FormUrlEncoded
//    @GET("app_inquiry.php")//endpoint
//    Call<InquriyResponse> createInquriy(@Query("name") String name, @Query("phone") String phone, @Query("email") String email, @Query("fcm_id") String fcm_id);
//
    @FormUrlEncoded
    @POST("WSAdmin/CheckOtpLogin.php")//endpoint
    Call<LoginResponse> Login(@Field("user_name") String username, @Field("Password") String userpassword);


    @FormUrlEncoded
    @POST("WSAdmin/Crmlogin.php")//endpoint
    Call<LoginResponse> Loginotp(@Field("user_name") String username);

    //@FormUrlEncoded
    @GET("WSAdmin/GetOtp.php")//endpoint
    Call<OtpResponse> GetOtp();

    @GET("wp_otp.php")//endpoint
    Call<OTPWPWR> GetwhatsappOtp();

    @GET("wp_otp.php")//endpoint
    Call<OTPWPWR> GetROtp();

    @GET("get_otp.php")//endpoint
    Call<OTPWPWR> GetGSM();

    @GET("WSAdmin/TodayHistory.php")//endpoint
    Call<ResponseAllTodayAppoitment> TodayAllappoitment();

    @GET("WSAdmin/TodayFollowup.php")//endpoint
    Call<FollowupResonse> followuplist();

    @FormUrlEncoded
    @POST("WSAdmin/DateFollowupMonth.php")//endpoint
    Call<FollowupResonse> FollowupDate(@Field("date")String date,
                                       @Field("date1")String date1);

    @FormUrlEncoded
    @POST("WSAdmin/DateHistroy.php")//endpoint
    Call<ResponseAllTodayAppoitment> dateAllappoitment(@Field("date")String date);

    @FormUrlEncoded
    @POST("WSAdmin/ExecutivMonthsales.php")//endpoint
    Call<ResponseTodaySale> Monyhsales(@Field("id") String id);

    @FormUrlEncoded
    @POST("WSAdmin/DateExecutivsale.php")//endpoint
    Call<ResponseTodaySale> dateMonyhsales(@Field("id") String id,
                                       @Field("date")String date,
                                       @Field("date1")String date1);


    @GET("WSAdmin/Executivelist.php")//endpoint
    Call<ResponseExecutive> excutive_list();


//    @FormUrlEncoded
//    @POST("appoitment.php")//endpoint
//    Call<App_Respones> appitmentlist(@Field("id") String id);
//
//    @FormUrlEncoded
//    @POST("followup.php")//endpoint
//    Call<Followup_app_respons> followupapp_list(@Field("id") String id);
//
//
//    @FormUrlEncoded
//    @POST("decline.php")//endpoint
//    Call<AppNotIntersted> decline(@Field("app_id") String ap_id, @Field("remark") String remark);
//
//    @FormUrlEncoded
//    @POST("except.php")//endpoint
//    Call<AppNotIntersted> Accept(@Field("app_id") String ap_id);
//
//    @FormUrlEncoded
//    @POST("reach.php")//endpoint
//    Call<AppNotIntersted> reachedTime(@Field("app_id") String ap_id, @Field("except_time") String except_time);
//
//    @FormUrlEncoded
//    @POST("not_available_app.php")//endpoint
//    Call<AppNotIntersted> notavilable(@Field("reson") String reason, @Field("app_id") String ap_id);
//
//    @FormUrlEncoded
//    @POST("not_intrested_app.php")//endpoint
//    Call<AppNotIntersted> notintrested(@Field("reson") String reason, @Field("app_id") String ap_id);
//
//    @FormUrlEncoded
//    @POST("followup_app.php")//endpoint
//    Call<AppNotIntersted> followupapp(@Field("reson") String reason, @Field("f_date") String fdate, @Field("f_time") String ftime, @Field("app_id") String ap_id, @Field("followp_status") String sttus);
//
//    @FormUrlEncoded
//    @POST("Followup_TM.php")//endpoint
//    Call<AppNotIntersted> followupappTM(@Field("reson") String reason, @Field("app_id") String ap_id, @Field("followp_status") String sttus);
//
//    //@FormUrlEncoded
//    @POST("company_name_list.php")//endpoint
//    Call<Comapnyname_respones> companynamelist();
//
//    //@FormUrlEncoded
//    @POST("product_list.php")//endpoint
//    Call<Productname_Respones> productlist();
//
//    //@FormUrlEncoded
//    @POST("department_list.php")//endpoint
//    Call<Department_Respones> Departmentlist();
//
//    @FormUrlEncoded
//    @POST("app_details.php")//endpoint
//    Call<Appdetails_Respones> appdetails(@Field("eid") String id);
//
//    @FormUrlEncoded
//    @POST("close.php")//endpoint
//    Call<Close_app_response> closeapp(@Field("app_id") String appid,
//                                      @Field("company_name") String cnmae,
//                                      @Field("name") String clientnmae,
//                                      @Field("phone") String cmobile,
//                                      @Field("email") String cemail,
//                                      @Field("Client_gst_no") String Client_gst_no,
//                                      @Field("website") String cwebsite,
//                                      @Field("address") String caddres,
//                                      @Field("category_id") String category,
//                                      @Field("product_id") String cproduct,
//                                      @Field("per_price") String cpprice,
//                                      @Field("product_remark") String creamrk,
//                                      @Field("quentity") String cqty,
//                                      @Field("pro_amount") String cpayamount,
//                                      @Field("g_total") String cgroundtotal,
//                                      @Field("payment_date") String spaydate,
//                                      @Field("payment") String cpaymet,
//                                      @Field("ptm_type") String cpaytype,
//                                      @Field("ag_total") String cremaingdta,
//                                      @Field("c_id") String seleccompunyname,
//                                      @Field("chq") String selectchqno,
//                                      @Field("bank") String selectbankname);
//
//    @FormUrlEncoded
//    @POST("cold_colling_new.php")//endpoint
//    Call<ColdResponse> coldcalling(@Field("id") String appid,
//                                   @Field("calling_type") String calling_type,
//                                   @Field("company_name") String cnmae,
//                                   @Field("name") String clientnmae,
//                                   @Field("phone") String cmobile,
//                                   @Field("email") String cemail,
//                                   @Field("address") String caddress,
//                                   @Field("product_name") String cproname,
//                                   @Field("dis_for") String cdis,
//                                   @Field("curr_date") String cdate,
//                                   @Field("curr_time") String ctime,
//                                   @Field("lead_status") String lead_status,
//                                   @Field("na_res") String na_res,
//                                   @Field("ni_res") String ni_res,
//                                   @Field("fu_reson") String fu_reson,
//                                   @Field("fu_date") String fu_date,
//                                   @Field("fu_time") String fu_time,
//                                   @Field("ex_st_time") String ex_st_time,
//                                   @Field("ex_end_time") String ex_end_time,
//                                   @Field("followp_status") String followp_status,
//                                   @Field("cur_date") String cur_date,
//                                   @Field("note") String cnote);

}