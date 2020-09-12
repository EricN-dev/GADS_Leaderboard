

package io.ericnjuguna.gadsleaderboard2020;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.ericnjuguna.data_gads.data.DefaultRepository;
import io.ericnjuguna.data_gads.data.GadsRepository;
import io.ericnjuguna.data_gads.data.UserIq;
import io.ericnjuguna.data_gads.data.UserTime;

public class SharedViewModel extends ViewModel {

    private final GadsRepository repository;
    private final String TAG = SharedViewModel.class.getSimpleName();

    private final MutableLiveData<Boolean> _isLearningHoursLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLearningHoursLoading = _isLearningHoursLoading;

    private final MutableLiveData<Boolean> _isSkillIqLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isSkillIqLoading = _isSkillIqLoading;

    private MutableLiveData<List<UserIq>> _userIqList = new MutableLiveData<>();
    public final LiveData<List<UserIq>> userIqList = _userIqList;

    private final MutableLiveData<List<UserTime>> _userList = new MutableLiveData<>();
    public final LiveData<List<UserTime>> userList = _userList;

    public SharedViewModel(GadsRepository repository) {
        super();
        this.repository = repository;

        init();
    }

    public void init(){
        repository.getExecutor().execute( () -> {
            _userIqList.postValue( repository.getSkillIqs());
            _userList.postValue( repository.getUserHours() );
        });

        repository.setUpdateActionListener(new DefaultRepository.UpdateDataActionListener() {
            @Override
            public void setUserHourIsLoading(Boolean value) {
                _isLearningHoursLoading.postValue(value);
            }

            @Override
            public void setSkillIqIsLoading(Boolean value) {
                _isSkillIqLoading.postValue(value);
            }

            @Override
            public void updateUserHours() {
                repository.getExecutor().execute(() -> {
                    _userList.postValue( repository.getUserHours() );
                });
            }

            @Override
            public void updateSkillIq() {
                repository.getExecutor().execute(() -> {
                    _userIqList.postValue( repository.getSkillIqs());
                });
            }
        });
    }

    public void loadUserHours(){

        _isLearningHoursLoading.postValue(true);

        repository.updateUserHoursFromApi();
    }

    public void loadUserIq(){

        _isSkillIqLoading.postValue(true);

        repository.updateUserIqFromApi();
    }

}

