/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.androidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.androidme.R;
import com.example.android.androidme.data.AndroidImageAssets;


// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        if (savedInstanceState == null) {
            // Retrieve list index values that were sent through an intent; use them to display the desired Android-Me body part image
            // Use setListIndex(int index) to set the list index for all BodyPartFragments
            int headListIndex;
            int bodyListIndex;
            int legsListIndex;
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");
            headListIndex = bundle.getInt(MainActivity.HEAD_INDEX, 1);
            bodyListIndex = bundle.getInt(MainActivity.BODY_INDEX, 1);
            legsListIndex = bundle.getInt(MainActivity.LEGS_INDEX, 1);

            // Create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();


            // Set the list of image id's for the head fragment and set the position of the image that will be displayed
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(headListIndex);

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();
            // Create and display the body and leg BodyPartFragments

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(bodyListIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            legFragment.setListIndex(legsListIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }
    }
}
