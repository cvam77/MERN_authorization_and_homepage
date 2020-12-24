package com.example.watchman_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    FloatingActionButton mFabAddWatchman;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView mTvName, mUrlTv, mEtEnterKeyword, mTvJsoupText, mTvFoundKeyword;
    Button mButtonStartWatching;
    String url, TAG = "aquaman";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        fragmentManager = getSupportFragmentManager();

        setTitle("Home Screen");
        mFabAddWatchman = findViewById(R.id.fabAddWathcman);
        mFabAddWatchman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWatchmanButtonClicked();
            }
        });
        mFirebaseAuth = FirebaseAuth.getInstance();

    }


    private void AddWatchmanButtonClicked() {
        Fragment fragment = new AddWatchmanFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutContainer,fragment,"testing");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.log_out:
                LogOut();
                return true;
            case R.id.info:
                ShowProfileScreen();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ShowProfileScreen() {

    }

    private void LogOut() {
        mFirebaseAuth.signOut();

        //going back to the main activity screen
        Intent intent = new Intent(HomeScreen.this,MainActivity.class);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
        else
        {
            super.onBackPressed();
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
    }

}