
package io.ericnjuguna.data_gads.data;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;

import io.ericnjuguna.data_gads.utility.LogHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  Implementation of {@link GadsRepository}
 *
 */

public class DefaultRepository implements GadsRepository {

    private final GadsDataSource<UserTime, UserIq> localDataSource;
    private final GadsDataSource<UserTime, UserIq> remoteDataSource;
    private final Executor executor;
    private final String TAG = DefaultRepository.class.getSimpleName();
    private UpdateDataActionListener listener;

    /**
     *
     * @param localDataSource save and get data from Room DB
     * @param remoteDataSource get data from API using retrofit
     * @param executor for running background threads
     */
    public DefaultRepository(
            GadsDataSource<UserTime, UserIq> localDataSource,
            GadsDataSource<UserTime, UserIq> remoteDataSource,
            Executor executor
    ){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.executor = executor;
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }

    @Override
    public List<UserTime> getUserHours() {
        return localDataSource.getUserList();
    }

    @Override
    public List<UserIq> getSkillIqs() {
        return localDataSource.getSkillIqList();
    }

    @Override
    public void updateUserHoursFromApi() {

        remoteDataSource.getUserHours()
                .enqueue(new Callback<List<UserTime>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserTime>> call, @NonNull Response<List<UserTime>> response) {
                        updateUserTimeDb( response.body());
                        if(listener != null)
                            listener.setUserHourIsLoading(false);

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserTime>> call, @NonNull Throwable t) {
                        if(listener != null)
                            listener.setUserHourIsLoading(false);
                        // Log error
                        LogHelper.log(TAG, t.getMessage());
                    }
                });

    }

    @Override
    public void updateUserIqFromApi() {

        remoteDataSource.getUserIqs()
                .enqueue(new Callback<List<UserIq>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserIq>> call, @NonNull Response<List<UserIq>> response) {
                        updateUserIqDb(response.body());
                        if(listener != null)
                            listener.setSkillIqIsLoading(false);

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserIq>> call, @NonNull Throwable t) {
                        // Log error
                        LogHelper.log(TAG, t.getMessage());
                        if(listener != null)
                            listener.setSkillIqIsLoading(false);
                    }
                });

    }

    @Override
    public void updateUserIqDb(List<UserIq> userIqList) {
        executor.execute(() -> {
            localDataSource.deleteUserIqs();
            localDataSource.saveUserIqList(userIqList);
            listener.updateSkillIq();
        });

    }

    @Override
    public void updateUserTimeDb(List<UserTime> userTimeList) {

        executor.execute(() -> {
            localDataSource.deleteUserHours();
            localDataSource.saveUserTimeList(userTimeList);
            listener.updateUserHours();
            LogHelper.log(TAG, "data is saved");
        });

    }

    @Override
    public void setUpdateActionListener(UpdateDataActionListener listener) {
        this.listener = listener;
    }

    /**
     *  action listener to update viewModel
     */

    public interface UpdateDataActionListener {

        /**
         * @param value  f true , inform UI , API is updating user {@link UserTime} from
         *               database
         */
        void setUserHourIsLoading(Boolean value);

        /**
         * @param value  f true , inform UI , API is updating user {@link UserIq} from
         *               database
         */
        void setSkillIqIsLoading(Boolean value);

        /**
         *  informs viewModel of Updates {@link UserTime} data in database
         */
        void updateUserHours();

        /**
         *  informs viewModel of Updates {@link UserIq} data in database
         */
        void updateSkillIq();
    }

}
