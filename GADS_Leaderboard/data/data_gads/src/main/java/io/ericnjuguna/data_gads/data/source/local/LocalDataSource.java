

package io.ericnjuguna.data_gads.data.source.local;

import java.util.List;

import io.ericnjuguna.data_gads.data.GadsDataSource;
import io.ericnjuguna.data_gads.data.UserIq;
import io.ericnjuguna.data_gads.data.UserTime;
import io.ericnjuguna.data_gads.utility.LogHelper;
import retrofit2.Call;

public class LocalDataSource implements GadsDataSource<UserTime,UserIq> {

    private UserIQDao userIQDao;
    private UserTimeDao userTimeDao;
    private final String TAG = LocalDataSource.class.getSimpleName();

    public LocalDataSource(UserIQDao userIQDao, UserTimeDao userTimedao){
        this.userIQDao = userIQDao;
        this.userTimeDao = userTimedao;
    }

    @Override
    public List<UserTime> getUserList() {
        try {
            return userTimeDao.getTimeList();
        }catch (Exception e){
            LogHelper.log(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserIq> getSkillIqList() {
        try {
            return userIQDao.getIqList();
        }catch (Exception e){
            LogHelper.log(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    public Call<List<UserTime>> getUserHours() {
        // Not required
        return null;
    }

    @Override
    public Call<List<UserIq>> getUserIqs() {
        // Not required
        return null;
    }

    @Override
    public void saveUserTime(UserTime obj) {
        userTimeDao.save(obj);
    }

    @Override
    public void saveUserIq(UserIq obj) {
        userIQDao.save(obj);
    }

    @Override
    public void deleteUserTime(UserTime obj) {
        userTimeDao.delete(obj);
    }

    @Override
    public void deleteUserIq(UserIq obj) {
        userIQDao.delete(obj);
    }

    @Override
    public void saveUserIqList(List<UserIq> userIqList) {
        userIQDao.saveAll(userIqList);
    }

    @Override
    public void saveUserTimeList(List<UserTime> userTimeList) {
        userTimeDao.saveAll(userTimeList);
    }

    @Override
    public void deleteUserHours() {
        userTimeDao.deleteAll();
    }

    @Override
    public void deleteUserIqs() {
        userIQDao.deleteAll();
    }
}
