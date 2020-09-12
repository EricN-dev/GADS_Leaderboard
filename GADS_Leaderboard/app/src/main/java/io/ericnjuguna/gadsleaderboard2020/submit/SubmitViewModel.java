

package io.ericnjuguna.gadsleaderboard2020.submit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.ericnjuguna.data_gads.data.GadSubmitRepository;
import io.ericnjuguna.data_gads.data.SubmitDetails;
import io.ericnjuguna.data_gads.utility.LogHelper;

public class SubmitViewModel extends ViewModel {

    private final GadSubmitRepository repository;
    private final String TAG =  SubmitViewModel.class.getSimpleName();

    public final MutableLiveData<String> firstName = new MutableLiveData<>();
    public final MutableLiveData<String> lastName = new MutableLiveData<>();
    public final MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public final MutableLiveData<String> githubUrl = new MutableLiveData<>();

    public SubmitViewModel(GadSubmitRepository repository){
        super();
        this.repository = repository;
    }

    public void submit(){
        SubmitDetails submitDetails = new SubmitDetails(firstName.getValue(), lastName.getValue() ,
                emailAddress.getValue() , githubUrl.getValue() );

        repository.submit(submitDetails);
        LogHelper.log(TAG, "First name is " + firstName.getValue() );
    }
}
