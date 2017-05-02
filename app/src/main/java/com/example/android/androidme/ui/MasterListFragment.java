package com.example.android.androidme.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.androidme.R;
import com.example.android.androidme.data.AndroidImageAssets;


public class MasterListFragment extends Fragment {

    //we need to provide an empty constructor to create instances of this class
    public MasterListFragment(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the fragment_master_list layout
        View rootView=inflater.inflate(R.layout.fragment_master_list,container,false);
        MasterListAdapter masterListAdapter=new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        GridView gridView=(GridView)rootView.findViewById(R.id.images_grid_view);
        gridView.setAdapter(masterListAdapter);
        //return the rootView
        return rootView;
    }
}
