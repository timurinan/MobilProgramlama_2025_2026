package com.example.uygulama3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {

    ArrayList<HesapFragment> hesaplar=new ArrayList<HesapFragment>();

    public void add(HesapFragment hf){
        hesaplar.add(hf);
    }
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return hesaplar.get(position);
    }

    @Override
    public int getItemCount() {
        return hesaplar.size();
    }
}
