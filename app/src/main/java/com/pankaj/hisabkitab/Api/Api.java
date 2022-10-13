package com.pankaj.hisabkitab.Api;

import com.pankaj.hisabkitab.model.AddCustomerResponce;
import com.pankaj.hisabkitab.model.AddTransactionResponse;
import com.pankaj.hisabkitab.model.CreateUser;
import com.pankaj.hisabkitab.model.GetCustomerResponse;
import com.pankaj.hisabkitab.model.GetTransactionResponse;
import com.pankaj.hisabkitab.model.LogoutUserResponse;
import com.pankaj.hisabkitab.model.UserloginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("createuser.php")
        // String data=>get
    Call<CreateUser> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password
    );



    @FormUrlEncoded
    @POST("userlogin.php")
    Call<UserloginResponse> login(
            @Field("email") String email,
            @Field ("password") String password

    );
    @FormUrlEncoded
    @POST("logoutuser.php")
    Call<LogoutUserResponse> logout(
            @Field("token") String token
     );
    @FormUrlEncoded
    @POST("addcustomer.php")
    Call<AddCustomerResponce> addCustomer(
            @Field("name") String name,
            @Field("email")String email,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("user_id") String user_id
    );
    @FormUrlEncoded
    @POST("getcustomer.php")
    Call<GetCustomerResponse> getCustomerList(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("addtransaction.php")
    Call<AddTransactionResponse> addTransaction(
            @Field("user_id") int user_id,
            @Field("customer_id") int customer_id,
            @Field("amount") String amount,
            @Field("type") String amount_type,
            @Field("title") String title,
            @Field("description") String description
    );
    @FormUrlEncoded
    @POST("gettransaction.php")
    Call<GetTransactionResponse> getAllTransactiondata(
         @Field("user_id") int user_id
    );
    @FormUrlEncoded
    @POST("updateprofile.php")
    Call<CreateUser> UpdateUserDetails(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("user_id") int user_id
            );
    @FormUrlEncoded
    @POST("changepassword.php")
    Call<CreateUser> changePassword(
            @Field("old_pass") String old_pass,
            @Field("new_pass") String new_pass,
            @Field("cnf_pass") String cnf_pass,
            @Field("user_id") int user_id,
            @Field("token") String token,
            @Field("is_login") String is_login
    );
}
