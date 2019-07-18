package com.example.eatit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   Button btnSignUp, btnSignIn;
   TextView textSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.btnSignActive);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        textSlogan = (TextView)findViewById(R.id.txtSlogan);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-Regular.ttf");
        textSlogan.setTypeface(typeface);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent signIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(signIn);


            }


        });


        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUp);

            }


        });
    }
}
