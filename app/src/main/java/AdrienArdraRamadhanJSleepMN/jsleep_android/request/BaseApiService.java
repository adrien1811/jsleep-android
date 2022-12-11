package AdrienArdraRamadhanJSleepMN.jsleep_android.request;

import java.util.ArrayList;
import java.util.List;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Account;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.BedType;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.City;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Facility;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Renter;
import AdrienArdraRamadhanJSleepMN.jsleep_android.model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);
    @POST("account/login")
    Call<Account> login (@Query("email") String username, @Query("password") String password);
    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    @POST("account/register")
    Call<Account> register (@Query("name") String name, @Query("email") String email, @Query("password") String password);
    @POST("account/{id}/registerRenter")
    Call<Renter> registerRenter(@Path("id") int id,
                                @Query("username") String username,
                                @Query("address") String address,
                                @Query("phoneNumber") String phoneNumber);
    @POST("account/{id}/topup")
    Call<Boolean>topUp(@Path("id") int id, @Query("balance") double balance);

    @POST("room/create")
    Call<Room> room(@Query("accountId") int id, @Query("name") String name, @Query("price") int price, @Query("address") String address, @Query("size") int size, @Query("facility") ArrayList<Facility> facility, @Query("city") City city, @Query("bedType") BedType bedType);

    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom(@Query("page") int page, @Query("pageSize") int pageSize);
}