
package io.ericnjuguna.gadsleaderboard2020.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import io.ericnjuguna.gadsleaderboard2020.leaders.FragmentType;
import io.ericnjuguna.gadsleaderboard2020.leaders.SharedFragment;

public class HomePagerAdaptor extends FragmentStateAdapter {

    public HomePagerAdaptor(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return SharedFragment.newInstance(FragmentType.LEARNING_HOURS);

            case 1:
                return SharedFragment.newInstance(FragmentType.LEARNING_SKILL_IQ);

            default:
                return null;
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
