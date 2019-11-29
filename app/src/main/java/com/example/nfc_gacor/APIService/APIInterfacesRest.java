package com.example.nfc_gacor.APIService;

/**
 * Created by user on 1/10/2018.
 */






import com.example.nfc_gacor.model.produk.ModelProduk;
import com.example.nfc_gacor.model.topup.ModelTopup;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface APIInterfacesRest {


 @GET("produk/all")
 Call <ModelProduk> getProduk(@Query ("X-Api-Key") String apikey);

 @GET("topup/all")
 Call<ModelTopup> getTopup(@Query ("X-Api-Key") String apikey);





 /*@POST("api/survey/add")
 Call<ModelPOST> setData(@Field("id") String id,
                         @Field("dari") String dari,
                         @Field("text") String text,
                         @Field("time") String time,
                         @Field("status") String status);

 @Multipart
 @POST("player/add")
 Call<ModelPlayer> sendDataPlayer(

         @Part("name") RequestBody nama,
         @Part("email") RequestBody alamat,
         @Part("handphone") RequestBody umur,
         // @Part MultipartBody.Part foto,
         @Part("password") RequestBody password,
         @Part("repassword") RequestBody repassword

 );

 @GET("skill/all")
    Call<ModelSkill> getSkill(@Query("api-key") String apikey);

 @GET("roles/all")
 Call<ModelRole> getRoll(@Query("api-key") String apikey);

 @GET("room/all")
 Call<ModelRoom> getRoom(@Query("api-key") String apikey);
 @Multipart
 @POST("room/add")
 Call<ModelRoom> sendRoom(

         @Part("room_name") RequestBody nama,
         @Part("player") RequestBody player,
         @Part("status") RequestBody status
         // @Part MultipartBody.Part foto,
         //@Part("password") RequestBody password,
         // @Part("repassword") RequestBody repassword

 ); */

}




