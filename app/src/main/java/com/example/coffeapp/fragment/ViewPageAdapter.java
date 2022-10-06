package com.example.coffeapp.fragment;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.coffeapp.TruyenUser;
import com.example.coffeapp.User;

public class ViewPageAdapter extends FragmentStateAdapter implements TruyenUser {
    User user;

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity,User user) {
        super(fragmentActivity);
        this.user=user;
    }


    public ViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ListProductFragment(user);
            case 1:
                return new ListProductLikeFragment();
            case 2:
                return  new InfomationFragment();
            default:
                return new ListProductFragment();
        }



    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void userData(User user) {
       this.user=user;
    }
}
