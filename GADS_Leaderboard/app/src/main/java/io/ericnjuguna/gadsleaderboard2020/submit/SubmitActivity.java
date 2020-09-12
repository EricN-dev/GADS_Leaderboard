
package io.ericnjuguna.gadsleaderboard2020.submit;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import io.ericnjuguna.data_gads.ServiceLocator;
import io.ericnjuguna.gadsleaderboard2020.R;
import io.ericnjuguna.gadsleaderboard2020.databinding.ActivitySubmitBinding;

public class SubmitActivity extends AppCompatActivity {

    private ActivitySubmitBinding binding;
    private SubmitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_submit);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, new SubmitViewModelFactory(ServiceLocator.provideSubmitRepository()) )
                .get(SubmitViewModel.class);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home )
            finish();

        return super.onOptionsItemSelected(item);
    }
}