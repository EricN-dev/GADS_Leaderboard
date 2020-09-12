

package io.ericnjuguna.data_gads.data;

import androidx.annotation.NonNull;

import io.ericnjuguna.data_gads.network.GadsSubmitApiService;
import io.ericnjuguna.data_gads.utility.LogHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitRepository implements GadSubmitRepository {

    private final String TAG = SubmitRepository.class.getSimpleName();

    private final GadsSubmitApiService apiService;

    public SubmitRepository(GadsSubmitApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public void submit(SubmitDetails submitDetails) {
        apiService.submit(submitDetails.firstName, submitDetails.lastName, submitDetails.email,
                submitDetails.track, submitDetails.github )
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        LogHelper.log(TAG, "The response was " + response.body() );
                        LogHelper.log(TAG, "The response code is " + response.code() );
                        LogHelper.log(TAG, "Successful ? : " + response.isSuccessful() );
                        LogHelper.log(TAG, "The message is " + response.errorBody() );
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        LogHelper.log(TAG, "Error submitting !!!!!! \n Error msg :  " + t.getMessage()  );
                    }
                });
    }
}
