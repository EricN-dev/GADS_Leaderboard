
package io.ericnjuguna.gadsleaderboard2020.leaders;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import io.ericnjuguna.data_gads.data.UserIq;
import io.ericnjuguna.gadsleaderboard2020.BR;
import io.ericnjuguna.gadsleaderboard2020.GlideApp;
import io.ericnjuguna.gadsleaderboard2020.databinding.SkillIqItemBinding;

public class UserIqAdaptor extends ListAdapter<UserIq , UserIqAdaptor.UserIqViewHolder> {

    private static final DiffUtil.ItemCallback<UserIq> callback = new DiffUtil.ItemCallback<UserIq>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserIq oldItem, @NonNull UserIq newItem) {
            return oldItem.uid == newItem.uid;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserIq oldItem, @NonNull UserIq newItem) {
            return oldItem.uid == newItem.uid && oldItem.name.equals(newItem.name) && oldItem.country.equals(newItem.country);
        }
    };

    protected UserIqAdaptor() {
        super(callback);
    }

    @NonNull
    @Override
    public UserIqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UserIqViewHolder holder, int position) {
        holder.bind( getItem(position));
    }

    class UserIqViewHolder extends RecyclerView.ViewHolder {

        private final SkillIqItemBinding binding;

        public UserIqViewHolder(@NonNull SkillIqItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserIq userIq){

            GlideApp.with(binding.getRoot().getContext())
                    .load(userIq.badgeUrl)
                    .centerCrop()
                    .into(binding.userImage);


            binding.setVariable(BR.user, userIq);
            binding.executePendingBindings();
        }

        public void clear(){
            binding.unbind();
        }
    }

    private UserIqViewHolder createViewHolder(ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SkillIqItemBinding binding = SkillIqItemBinding.inflate(layoutInflater, parent, false);

        return new UserIqViewHolder(binding);
    }
}
