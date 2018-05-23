package com.locationbasedcompany.pc.laundry;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private SliderAdapter sliderAdapter;

    private TextView headerTextView;
    private TextView descriptionTextView;

    private TextView signInTextView;

    private LinearLayout dotsLayout;
    private ImageView[] mDots;
    private ConstraintLayout constraintLayout;

    private LinearLayout facebook;
    private TextView facebookMessageTextView;

    private LinearLayout google;
    private TextView googleMessageTextView;

    private Button signUp;

    private String[] slide_heading = {
            "Schedule.",
            "Clean.",
            "Returned."
    };

    private String[] slide_description = {
            "Schedule a pick-up, a valet will be at your door within your preferred time range!",
            "Your garments are carefully cleaned at our enviornmentally friendly cleaner!",
            "Like magic, everything is crisp, clean, and back at your door."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mSliderViewPager = findViewById(R.id.switcherLayout);

        // header and description textview
        headerTextView = findViewById(R.id.headerTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        headerTextView.setText(slide_heading[0]);
        descriptionTextView.setText(slide_description[0]);

        // slider for changing the images
        sliderAdapter = new SliderAdapter(this);
        mSliderViewPager.setAdapter(sliderAdapter);


        facebook = findViewById(R.id.facebook);
        facebookMessageTextView = findViewById(R.id.loginFacebook);
        facebookMessageTextView.setText("Sign up with Facebook");

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        google = findViewById(R.id.google);
        googleMessageTextView = findViewById(R.id.loginGoogle);
        googleMessageTextView.setText("Sign up with Google");

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //changing the fonts
        Typeface typeface =  Typeface.createFromAsset(getAssets(), "fonts/Graphik-Regular.otf");
        Typeface typefaceHeader =  Typeface.createFromAsset(getAssets(), "fonts/Graphik-Black.otf");

        headerTextView.setTypeface(typefaceHeader);
        descriptionTextView.setTypeface(typeface);

        constraintLayout = findViewById(R.id.root);
        
        mDots = new ImageView[slide_heading.length];

        dotsLayout = findViewById(R.id.dotsLinearLayout);
          for (int i = 0; i < slide_heading.length; i++) {
            mDots[i] = new ImageView(this);

            if (i == 0) {
                mDots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }else {
                mDots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0 , 8, 0);

            dotsLayout.addView(mDots[i], params);
        }


        mSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (positionOffset > 0.5) {

                    headerTextView.setText(slide_heading[position + 1]);
                    headerTextView.setAlpha(positionOffset);

                    descriptionTextView.setText(slide_description[position + 1]);
                    descriptionTextView.setAlpha(positionOffset);

                } else {
                    headerTextView.setText(slide_heading[position]);
                    headerTextView.setAlpha(1 - positionOffset);

                    descriptionTextView.setText(slide_description[position]);
                    descriptionTextView.setAlpha(1 - positionOffset);


                }

                if ( position == 2) {
                    AutoTransition transition = new AutoTransition();
                    transition.setDuration(1000);
                    TransitionManager.beginDelayedTransition(constraintLayout,transition);

                    ConstraintSet constraint = new ConstraintSet();
                    constraint.clone(MainActivity.this, R.layout.content_main_alt);
                    constraint.applyTo(constraintLayout);

                    AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
                    animation.setDuration(1000);

                    facebook.startAnimation(animation);
                    google.startAnimation(animation);

                }
            }
            @Override
            public void onPageSelected(int position) {
                if(position != 0){
                    mDots[position-1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }
                if(position != mDots.length-1) {
                    mDots[position+1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                mDots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

          signInTextView = findViewById(R.id.signinDialog);
          signInTextView.setText("Already have an account?");

        ((TextView) findViewById(R.id.signinWithEmailOr)).setText("Or");
        ((TextView) findViewById(R.id.signinWithEmailOrWithEmail)).setText("with email.");

        signUp = findViewById(R.id.signInButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
            }
        });
    }



}
