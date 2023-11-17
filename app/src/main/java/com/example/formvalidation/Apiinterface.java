package com.example.formvalidation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apiinterface {

    //@FormUrlEncoded is used because requests made with this annotation
    //will have application/x-www-form-urlencoded MIME type instead of jSON
    //like the regular POST request
    @FormUrlEncoded
    @GET(" https://api.npoint.io/b8bb67258d14242e04ad")
    @POST
    Call<User> getUserInformation(
            @Field("FirstName") String firstName,
            @Field("LastName") String lastName,
            @Field("Email") String email,
            @Field("DOB") String dob,
            @Field("BuildingNo") String buildingNo,
            @Field("Street") String street,
            @Field("City") String city,
            @Field("State") String state,
            @Field("PinCode") String pinCode,
            @Field("ContactNo") String contactNo,
            @Field("IsMale") boolean isMale,
            @Field("IsFemale") boolean isFemale,
            @Field("KnowsHTML") boolean knowsHTML,
            @Field("KnowsCSS") boolean knowsCSS,
            @Field("KnowsJS") boolean knowsJS
    );
}
