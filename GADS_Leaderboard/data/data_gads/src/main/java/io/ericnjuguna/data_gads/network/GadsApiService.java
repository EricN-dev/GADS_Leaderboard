
package io.ericnjuguna.data_gads.network;

import java.util.List;

import io.ericnjuguna.data_gads.data.UserIq;
import io.ericnjuguna.data_gads.data.UserTime;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GadsApiService {

    String GADS_HOURS = "/api/hours";
    String GADS_IQ = "/api/skilliq";

    /**
     * @return leader board list based on hours
     */
    @GET(GADS_HOURS)
    Call<List<UserTime>> getUserHours();

    /**
     * @return leader board list based on IQ
     */
    @GET(GADS_IQ)
    Call<List<UserIq>> getUserIqs();
}
