package com.locationbasedcompany.pc.laundry;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    private TextView dontHaveAccount;
    private TextView googleSignup;
    private TextView facebookSignup;
    private TextView loginTextView;

    private EditText emailText;
    private EditText passwordText;

    private Guideline guideline1;
    private Guideline guideline2;
    private Guideline guideline3;

    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        dontHaveAccount = findViewById(R.id.signupDialog);
        dontHaveAccount.setText("Dont have a account? Click to");

        googleSignup = findViewById(R.id.loginGoogle);
        googleSignup.setText("Sign in with Google");

        facebookSignup = findViewById(R.id.loginFacebook);
        facebookSignup.setText("Sign in with Facebook");

        loginTextView = findViewById(R.id.loginButtonText);
        loginTextView.setText("Login");

        emailText = findViewById(R.id.textEmail);
        passwordText = findViewById(R.id.textPassword);

        guideline1 = findViewById(R.id.guideline);
        guideline2 = findViewById(R.id.guideline2);
        guideline3 = findViewById(R.id.guideline3);

        constraintLayout = findViewById(R.id.root);

        addOnClick(emailText);
        addOnClick(passwordText);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                AutoTransition transition = new AutoTransition();
                transition.setDuration(1000);
                TransitionManager.beginDelayedTransition(constraintLayout,transition);

                ConstraintSet constraint = new ConstraintSet();
                constraint.clone(LoginScreen.this, R.layout.activity_login_screen);
                constraint.applyTo(constraintLayout);

                hideSoftKeyBoard();
                return false;
            }
        });
    }

    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void addOnClick(EditText button) {
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AutoTransition transition = new AutoTransition();
//                transition.setDuration(1000);
//                TransitionManager.beginDelayedTransition(constraintLayout,transition);
//
//                ConstraintSet constraint = new ConstraintSet();
//                constraint.clone(LoginScreen.this, R.layout.activity_login_screen_alt);
//                constraint.applyTo(constraintLayout);
//            }
//        });

    }
}
