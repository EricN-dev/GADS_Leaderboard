
package io.ericnjuguna.data_gads.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.ericnjuguna.data_gads.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  contains all helper methods to create a [{@link GadsApiService}] instance
 */

public class ApiFactory {

    private ApiFactory(){
        // don't create an instance of this class , oh how i miss kotlin
    }

    /**
     * @return [OkHttpClient] instance + logs only for debug build
     */
    private static OkHttpClient provideHttpClient(){

        if (BuildConfig.DEBUG ){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY);

            return new OkHttpClient()
                    .newBuilder()
                    .addInterceptor( loggingInterceptor )
                    .build();
        }else {

            return new OkHttpClient()
                    .newBuilder()
                    .build();
        }
    }

    private static Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    private static Retrofit retrofit(){
        String BASE_URL = "https://gadsapi.herokuapp.com";
        return new Retrofit.Builder()
                .client( provideHttpClient() )
                .baseUrl(BASE_URL)
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
    }

    private static Retrofit retrofitSubmit(){

        String BASE_URL = "https://docs.google.com/forms/d/e/";

        return new Retrofit.Builder()
                .client( provideHttpClient() )
                .baseUrl(BASE_URL)
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

    }

    public static synchronized GadsApiService provideGadsApi(){
        return retrofit().create(GadsApiService.class);
    }

    public static synchronized GadsSubmitApiService provideGadsSubmitApi(){
        return retrofitSubmit().create(GadsSubmitApiService.class);
    }

}
