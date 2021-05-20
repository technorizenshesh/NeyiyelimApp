package com.laapesca.neyiyelimapp.utils;

import com.laapesca.neyiyelimapp.Model.BannerMoodel;
import com.laapesca.neyiyelimapp.Model.Branch;
import com.laapesca.neyiyelimapp.Model.CategoryModel;
import com.laapesca.neyiyelimapp.Model.CountryNameModel;
import com.laapesca.neyiyelimapp.Model.DeliveryAddressModelAdreess;
import com.laapesca.neyiyelimapp.Model.GetCardOne;
import com.laapesca.neyiyelimapp.Model.GetCityModel;
import com.laapesca.neyiyelimapp.Model.GetMenu;
import com.laapesca.neyiyelimapp.Model.GetSummeryModel;
import com.laapesca.neyiyelimapp.Model.MyOrderModel;
import com.laapesca.neyiyelimapp.Model.MyProfileModel;
import com.laapesca.neyiyelimapp.Model.RegionModel;
import com.laapesca.neyiyelimapp.Model.RestaurentModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface
Api {

    String signup ="signup";
    String login ="login";
    String social_login ="social_login";
    String forgot_password ="forgot_password";
    String get_profile ="get_profile";
    String update_profile ="update_profile";
    String change_password ="change_password";
    String get_category ="get_category";
    String get_banner ="get_banner";
  //String get_all_restaurant ="get_all_restaurant";
    //String get_all_restaurant ="get_all_restaurant_new";
    String get_all_restaurant ="rest";
    String get_menu ="get_menu";
    String get_sub_menu ="get_sub_menu";
    String add_to_cart ="add_to_cart";
    String get_card_item ="get_card_item";
    String empty_cart ="empty_cart";
    String add_delivery_address ="add_delivery_address";
    String get_delivery_address ="get_delivery_address";
    String add_select_address ="add_select_address";
    String get_country ="get_country";
    String get_region ="get_region";
    String get_city ="get_city";
    String get_summary ="get_summary";
    String add_placeorder ="add_placeorder";
    String get_my_order ="get_my_order";

    @FormUrlEncoded
    @POST(signup)
    Call<ResponseBody>signup(
            @Field("user_name") String user_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("register_id") String register_id,
            @Field("lat") String lat,
            @Field("lon") String lon,
            @Field("countryID") String countryID,
            @Field("city") String city,
            @Field("town") String town ,
            @Field("town2") String town2 ,
            @Field("address_type") String address_type,
            @Field("address_details") String address_details
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
    @POST(get_my_order)
    Call<MyOrderModel>get_my_order(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(get_delivery_address)
    Call<DeliveryAddressModelAdreess>get_delivery_address(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(get_summary)
    Call<GetSummeryModel>get_summary(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(add_select_address)
    Call<ResponseBody>add_select_address(
            @Field("delivery_address_id") String delivery_address_id,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST(add_delivery_address)
    Call<ResponseBody>add_delivery_address(
            @Field("user_id") String user_id,
            @Field("location") String location,
            @Field("address") String address,
            @Field("address_direction") String address_direction,
            @Field("address_type") String address_type,
            @Field("other_address") String other_address
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


    /*@POST(get_all_restaurant)
    Call<RestaurentModel>get_all_restaurant();*/

    @POST(get_country)
    Call<CountryNameModel>get_country();



    @FormUrlEncoded
    @POST(get_city)
    Call<GetCityModel>get_city(
            @Field("country_id") String country_id
    );

    @FormUrlEncoded
    @POST(get_all_restaurant)
    Call<RestaurentModel>get_all_restaurant(
            @Field("customer_region_id") String customer_region_id
    );

    @FormUrlEncoded
    @POST(get_region)
    Call<RegionModel>get_region(
            @Field("city_id") String city_id
    );


    @POST(get_category)
    Call<CategoryModel> get_category();

    @POST(get_banner)
    Call<BannerMoodel> get_banner();

    @FormUrlEncoded
    @POST(get_profile)
    Call<MyProfileModel> getProfile(@Field("user_id") String str);


    @FormUrlEncoded
    @POST(add_placeorder)
    Call<ResponseBody>add_placeorder(
            @Field("customer_id") String customer_id,
            @Field("branch_id") String branch_id,
            @Field("paymentTypeID") String paymentTypeID,
            @Field("order_date") String order_date,
            @Field("order_time") String order_time,
            @Field("delivery_date") String delivery_date,
            @Field("cart_id") String cart_id,
            @Field("delivery_time") String delivery_time,
            @Field("total_amount") String total_amount,
            @Field("customerLat") String customerLat,
            @Field("customerLag") String customerLag,
            @Field("customerPingpoint") String customerPingpoint,

            @Field("menu_id") String menu_id,
            @Field("submenu_id") String submenu_id,
            @Field("quantity") String quantity,
            @Field("amount") String amount
    );



}
