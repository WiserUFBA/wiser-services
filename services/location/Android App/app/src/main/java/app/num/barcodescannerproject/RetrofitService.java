package app.num.barcodescannerproject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by RenanTeles on 24/07/16.
 * Consumir Json no Android
 */
public interface RetrofitService {


    @FormUrlEncoded
    @POST("/{gatewayws}")
    Call<Boolean> addLocation(@Path("gatewayws") String gatewayws, @Field("latitude") Double latitude, @Field("longitude") Double longitude);

}
