package com.laapesca.neyiyelimapp.utils;

import com.laapesca.neyiyelimapp.Model.BannerMoodel;
import com.laapesca.neyiyelimapp.Model.Branch;
import com.laapesca.neyiyelimapp.Model.CategoryModel;
import com.laapesca.neyiyelimapp.Model.GetCardOne;
import com.laapesca.neyiyelimapp.Model.GetMenu;
import com.laapesca.neyiyelimapp.Model.MyProfileModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    String signup ="signup";
    String login ="login";
    String social_login ="social_login";
    String forgot_password ="forgot_password";
    String get_profile ="get_profile";
    String update_profile ="update_profile";
    String change_password ="change_password";
    String get_category ="get_category";
    String get_banner ="get_banner";
    String get_all_restaurant ="get_all_restaurant";
    String get_menu ="get_menu";
    String get_sub_menu ="get_sub_menu";
    String add_to_cart ="add_to_cart";
    String get_card_item ="get_card_item";
    String empty_cart ="empty_cart";

    @FormUrlEncoded
    @POST(signup)
    Call<ResponseBody>signup(
            @Field("user_name") String user_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("register_id") String register_id,
            @Field("lat") String lat,
            @Field("lon") String lon
    );

    @FormUrlEncoded
    @POST(login)
    Call<ResponseBody>login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("register_id") String register_id,
            @Field("lat") String lat,
            @Field("lon") String lon
    );


    @FormUrlEncoded
    @POST(social_login)
    Call<ResponseBody>social_login(
            @Field("email") String email,
            @Field("user_name") String user_name,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("register_id") String register_id,
            @Field("social_id") String social_id,
            @Field("lat") String lat,
            @Field("lon") String lon
    );

/* @FormUrlEncoded
    @POST(update_profile)
    Call<ResponseBody>update_profile(
            @Field("user_id") String user_id,
            @Field("user_name") String user_name,
            @Field("mobile") String mobile
    );*/

    @FormUrlEncoded
    @POST(update_profile)
    Call<ResponseBody>update_profile(
            @Field("user_id") String user_id,
            @Field("user_name") String user_name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("profile_image") String profile_image
    );

    @FormUrlEncoded
    @POST(change_password)
    Call<ResponseBody>change_password(
            @Field("user_id") String user_id,
            @Field("current_password") String current_password,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(forgot_password)
    Call<ResponseBody>forgot_password(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST(get_menu)
    Call<Branch>get_menu(
            @Field("branch_id") String branch_id
    );

    @FormUrlEncoded
    @POST(get_sub_menu)
    Call<GetMenu>get_sub_menu(
            @Field("branch_id") String branch_id,
            @Field("menuID") String menuID
    );

    @FormUrlEncoded
    @POST(get_card_item)
    Call<GetCardOne>get_card_item(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(empty_cart)
    Call<ResponseBody>empty_cart(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(add_to_cart)
    Call<ResponseBody>add_to_cart(
            @Field("user_id") String user_id,
            @Field("product_id") String product_id,
            @Field("quantity") String quantity,
            @Field("remark") String remark,
            @Field("restaurant_name") String restaurant_name,
            @Field("restaurant_image") String restaurant_image
    );


    @POST(get_all_restaurant)
    Call<RestaurentModel>get_all_restaurant();


    @POST(get_category)
    Call<CategoryModel> get_category();

    @POST(get_banner)
    Call<BannerMoodel> get_banner();

    @FormUrlEncoded
    @POST(get_profile)
    Call<MyProfileModel> getProfile(@Field("user_id") String str);


}
