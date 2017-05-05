package com.example.android.androidme.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.androidme.R;
import com.example.android.androidme.data.AndroidImageAssets;


public class MasterListFragment extends Fragment {

    //we need to provide an empty constructor to create instances of this class
    public MasterListFragment(){}

    OnImageClickListener mCallBack;

    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    // The callback is a method named onImageSelected(int position) that contains information about
    // which position on the grid of images a user has clicked
    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    //Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //this make sure that the host activity has implemented the OnImageClickListener interface
        try{
            mCallBack=(OnImageClickListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ " must implement OniMageClickListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the fragment_master_list layout
        View rootView=inflater.inflate(R.layout.fragment_master_list,container,false);
        MasterListAdapter masterListAdapter=new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        GridView gridView=(GridView)rootView.findViewById(R.id.images_grid_view);
        gridView.setAdapter(masterListAdapter);

        // Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked
        //set a clickListener to the GridView and trigger the callBack OnImageSelected when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mCallBack.onImageSelected(position);
            }
        });
        //return the rootView
        return rootView;
    }
}
