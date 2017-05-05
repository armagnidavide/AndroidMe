package com.example.android.androidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.androidme.R;


public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    //strings for the bundle we attach to the intent
    public static final String HEAD_INDEX = "headListIndex";
    public static final String BODY_INDEX = "bodyListIndex";
    public static final String LEGS_INDEX = "legsListIndex";
    //values for the bundle we attach to the intent
    private int headListIndex;
    private int bodyListIndex;
    private int legsListIndex;
    private Bundle bundle;
    private Button nextButton;//button that opens AndroidMeActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = new Bundle();
        nextButton = (Button) findViewById(R.id.btn_next);
        //add an onClickListener to the button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AndroidMeActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    //add the position of the image clicked to a bundle
    @Override
    public void onImageSelected(int position) {
        int bodyPart = position / 12;
        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        switch (bodyPart) {
            case 0:
                headListIndex = position;
                bundle.putInt(HEAD_INDEX, headListIndex);
                break;
            case 1:
                bodyListIndex = position - 12;
                bundle.putInt(BODY_INDEX, bodyListIndex);
                break;
            default:
                legsListIndex = position - 24;
                bundle.putInt(LEGS_INDEX, legsListIndex);
                break;
        }

    }
}
