package com.example.android.androidme.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.androidme.R;


public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Define the behavior for onImageSelected; create a Toast that displays the position clicked
    @Override
    public void onImageSelected(int position) {
        Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
    }
}
