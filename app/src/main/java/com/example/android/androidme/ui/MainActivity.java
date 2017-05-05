package com.example.android.androidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.androidme.R;
import com.example.android.androidme.data.AndroidImageAssets;


public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    //strings for the bundle we attach to the intent
    public static final String HEAD_INDEX = "headListIndex";
    public static final String BODY_INDEX = "bodyListIndex";
    public static final String LEGS_INDEX = "legsListIndex";
    private Bundle bundle;
    private Button nextButton;//button that opens AndroidMeActivity
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = new Bundle();
        nextButton = (Button) findViewById(R.id.next_button);
        if (findViewById(R.id.body_container) != null) {
            mTwoPane = true;
            nextButton.setVisibility(View.GONE);
        } else {
            mTwoPane = false;
        }
        // display, add new BodyPartFragments to create an initial Android-Me image
        // Also, for the two-pane display, get rid of the "Next" button in the master list fragment
        if (mTwoPane) {

            // Create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();

            // Set the list of image id's for the head fragment and set the position to the second image in the list
            headFragment.setImageIds(AndroidImageAssets.getHeads());

            // Get the correct index to access in the array of head images from the intent
            headFragment.setListIndex(1);

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            // Create and display the body and leg BodyPartFragments

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(1);

            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            legFragment.setListIndex(1);

            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        } else {
            //add an onClickListener to nextButton
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AndroidMeActivity.class);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            });
        }
    }

    //add the position of the image clicked to a bundle
    @Override
    public void onImageSelected(int position) {
        int bodyPartNumber = position / 12;
        int listIndex = position - bodyPartNumber * 12;
        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        if (!mTwoPane) {
            switch (bodyPartNumber) {
                case 0:
                    bundle.putInt(HEAD_INDEX, listIndex);
                    break;
                case 1:
                    bundle.putInt(BODY_INDEX, listIndex);
                    break;
                case 2:
                    bundle.putInt(LEGS_INDEX, listIndex);
                    break;
                default:
                    break;
            }

        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {

                case 0:
                    BodyPartFragment headFragment = new BodyPartFragment();

                    // Set the list of image id's for the head fragment
                    headFragment.setImageIds(AndroidImageAssets.getHeads());
                    // Get the correct index to access in the array of head images from the intent
                    headFragment.setListIndex(listIndex);

                    // Add the fragment to its container using a FragmentManager and a Transaction
                    fragmentManager.beginTransaction()
                            .replace(R.id.head_container, headFragment)
                            .commit();
                    break;
                case 1:
                    BodyPartFragment bodyFragment = new BodyPartFragment();

                    // Set the list of image id's for the body fragment
                    bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                    // Get the correct index to access in the array of body images from the intent
                    bodyFragment.setListIndex(listIndex);

                    fragmentManager.beginTransaction()
                            .replace(R.id.body_container, bodyFragment)
                            .commit();
                    break;
                case 2:
                    BodyPartFragment legsFragment = new BodyPartFragment();

                    // Set the list of image id's for the legs fragment
                    legsFragment.setImageIds(AndroidImageAssets.getLegs());
                    // Get the correct index to access in the array of legs images from the intent
                    legsFragment.setListIndex(listIndex);

                    fragmentManager.beginTransaction()
                            .replace(R.id.leg_container, legsFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        }
    }
}
