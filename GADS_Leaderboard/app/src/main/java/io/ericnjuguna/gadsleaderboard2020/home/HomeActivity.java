
package io.ericnjuguna.gadsleaderboard2020.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

import io.ericnjuguna.data_gads.utility.LogHelper;
import io.ericnjuguna.gadsleaderboard2020.R;
import io.ericnjuguna.gadsleaderboard2020.databinding.ActivityHomeBinding;
import io.ericnjuguna.gadsleaderboard2020.submit.SubmitActivity;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = HomeActivity.class.getSimpleName();
    private final io.ericnjuguna.gadsleaderboard2020.home.SubmitButtonClickListener _listener = () -> startActivity( new Intent(this, SubmitActivity.class));

    private  ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_home
        );
        binding.setLifecycleOwner(this);
        binding.setListener(_listener);

//        Objects.requireNonNull(getSupportActionBar()).setCustomView();

        LogHelper.log(TAG, "HomeAct onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        setupViews(binding);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        LogHelper.log(TAG, "HomeAct onResume");
    }

    private void setupViews(ActivityHomeBinding binding) {
        final io.ericnjuguna.gadsleaderboard2020.home.HomePagerAdaptor adaptor = new io.ericnjuguna.gadsleaderboard2020.home.HomePagerAdaptor(this);

        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        binding.viewPager.setAdapter( adaptor );


        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                ((tab, position) -> {

                    switch (position){
                        case 0:
                            tab.setText(R.string.learning_tab_tile);
                            break;
                        case 1:
                            tab.setText(R.string.iq_tab_title);
                            break;
                    }

                })
        ).attach();
    }
}