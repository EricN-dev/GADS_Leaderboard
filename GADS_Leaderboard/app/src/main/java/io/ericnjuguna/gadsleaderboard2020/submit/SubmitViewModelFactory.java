
package io.ericnjuguna.gadsleaderboard2020.submit;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.ericnjuguna.data_gads.data.GadSubmitRepository;

public class SubmitViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final GadSubmitRepository repository;

    public SubmitViewModelFactory(GadSubmitRepository repository){
        super();
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SubmitViewModel(repository);
    }
}
