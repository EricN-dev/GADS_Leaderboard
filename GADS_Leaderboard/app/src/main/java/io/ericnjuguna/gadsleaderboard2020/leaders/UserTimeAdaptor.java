

package io.ericnjuguna.gadsleaderboard2020.leaders;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import io.ericnjuguna.data_gads.data.UserTime;
import io.ericnjuguna.gadsleaderboard2020.BR;
import io.ericnjuguna.gadsleaderboard2020.GlideApp;
import io.ericnjuguna.gadsleaderboard2020.databinding.HourItemBinding;

public class UserTimeAdaptor extends ListAdapter<UserTime, UserTimeAdaptor.UserTimeViewHolder> {


    private static final DiffUtil.ItemCallback<UserTime> callback = new DiffUtil.ItemCallback<UserTime>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserTime oldItem, @NonNull UserTime newItem) {
            return oldItem.uid == newItem.uid;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserTime oldItem, @NonNull UserTime newItem) {
            return oldItem.uid == newItem.uid && oldItem.name.equals(newItem.name) && oldItem.country.equals(newItem.country);
        }
    };

    protected UserTimeAdaptor(){
        super(callback);
    }

    @NonNull
    @Override
    public UserTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTimeViewHolder holder, int position) {
        holder.bind( getItem(position));
    }

    class UserTimeViewHolder extends RecyclerView.ViewHolder {

        private final HourItemBinding binding;

        public UserTimeViewHolder(@NonNull HourItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserTime userTime){

            GlideApp.with(binding.getRoot().getContext())
                    .load(userTime.badgeUrl)
                    .centerCrop()
                    .into(binding.userImage);

            binding.setVariable(BR.user, userTime);
            binding.executePendingBindings();
        }

        public void clear(){
            binding.unbind();
        }

    }

    private UserTimeViewHolder createViewHolder(ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        HourItemBinding binding = HourItemBinding.inflate(layoutInflater, parent, false);

        return new UserTimeViewHolder(binding);
    }
}
