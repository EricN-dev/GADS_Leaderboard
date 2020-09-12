
package io.ericnjuguna.data_gads.data.source.remote;

import java.util.List;

import io.ericnjuguna.data_gads.data.GadsDataSource;
import io.ericnjuguna.data_gads.data.UserIq;
import io.ericnjuguna.data_gads.data.UserTime;
import io.ericnjuguna.data_gads.network.GadsApiService;
import retrofit2.Call;

public class RemoteDataSource implements GadsDataSource<UserTime, UserIq> {

    private GadsApiService apiService;

    public RemoteDataSource(GadsApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public List<UserTime> getUserList() {
        // Not required
        return null;
    }

    @Override
    public List<UserIq> getSkillIqList() {
        // Not required
        return null;
    }

    @Override
    public Call<List<UserTime>> getUserHours() {
        return apiService.getUserHours();
    }

    @Override
    public Call<List<UserIq>> getUserIqs() {
        return apiService.getUserIqs();
    }

    @Override
    public void saveUserTime(UserTime obj) {
        // Not required
    }

    @Override
    public void saveUserIq(UserIq obj) {
        // Not required
    }

    @Override
    public void deleteUserTime(UserTime obj) {
        // Not required
    }

    @Override
    public void deleteUserIq(UserIq obj) {
        // Not required
    }

    @Override
    public void saveUserIqList(List<UserIq> userIqList) {
        // Not required
    }

    @Override
    public void saveUserTimeList(List<UserTime> userTimeList) {
        // Not required
    }

    @Override
    public void deleteUserHours() {
        // Not required
    }

    @Override
    public void deleteUserIqs() {
        // Not required
    }

}
