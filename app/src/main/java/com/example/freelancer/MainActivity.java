package com.example.freelancer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText email,password;
    Button login;
    ProgressBar progressBar;
  //  public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView5);
        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button);
        login.setOnClickListener(v -> createAccount());

        getSupportActionBar().hide();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, loginavtivity.class);
                startActivity(intent);
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, loginavtivity.class);
//                startActivity(intent);
//            }
//        });

    }

    void createAccount()
    {

        String Email=email.getText().toString();
        String Password=password.getText().toString();


        //boolean isvalidate=validateData(Email,Password);
        if(validateData(Email,Password))
        {
            login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, loginavtivity.class);
                startActivity(intent);
                }
             });
        }
        createAccountInFirebase(Email, Password);

    }



    void createAccountInFirebase(String Email,String Password) {

        changeInProgress(true);
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful())   {
                     Toast.makeText(MainActivity.this, "Succesfully create account, check email to verify", Toast.LENGTH_SHORT).show();
                firebaseAuth.getCurrentUser().sendEmailVerification();
                firebaseAuth.signOut();
                finish();
                 }
                 else{
                     Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
   }



   void changeInProgress(boolean inProgress) {
        if(inProgress)
        {

            login.setVisibility(View.GONE);

        }
        else
        {
            login.setVisibility(View.VISIBLE);
        }

    }




    boolean validateData(String Email,String Password)
    {
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            email.setError("Email is invalid");
            return  false;

        }
        if(Password.length()<6)
        {
            password.setError("Password length is invalid");
            return  false;

        }

        return  true;
    }

}