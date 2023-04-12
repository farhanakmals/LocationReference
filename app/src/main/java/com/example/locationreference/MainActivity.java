package com.example.locationreference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    EditText username, password;
    Button btn_login;
    TextView mShow;
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usName = username.getText().toString();
                String pwd = password.getText().toString();

                if(usName.equals("pakjoko") && pwd.equals("yangpentingcuan")){
                    Intent inten = new Intent(view.getContext(),category.class);
                    startActivity(inten);
                } else {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setTitle("Username atau password salah!");
                    alertBuilder.setMessage("silahkan masukan username dan password yang benar!");

                    alertBuilder.setNeutralButton("Ok", (dialog, which) ->{
                        Toast.makeText(getApplicationContext(), "Masukan username dan password anda kembali", Toast.LENGTH_LONG).show();

                    });

                    alertBuilder.show();

                    username.setText("");
                    password.setText("");
                }
            }
        });

        mShow = findViewById(R.id.btn_show);
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFrame();
                } else {
                    closeFrame();
                }
            }
        });

    }

    public void displayFrame() {
        mFragment simpleFragment = mFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.mfragment,simpleFragment).addToBackStack(null).commit();
        mShow.setText("Hide Author");
        isFragmentDisplayed = true;
    }

    public void closeFrame() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragment fragment = (mFragment) fragmentManager.findFragmentById(R.id.mfragment);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment).commit();
        }
        mShow.setText("Show Author");
        isFragmentDisplayed = false;
    }

}