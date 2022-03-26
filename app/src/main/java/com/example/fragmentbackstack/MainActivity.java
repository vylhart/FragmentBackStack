package com.example.fragmentbackstack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        button = findViewById(R.id.button);
        button.setOnClickListener(view->{
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, new FragmentTwo());
            ft.addToBackStack(null);
            ft.commit();
            button.setVisibility(View.INVISIBLE);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameLayout, new FragmentOne());
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {

        if(getSupportFragmentManager().getBackStackEntryCount()==2){
            super.onBackPressed();
            button.setVisibility(View.VISIBLE);
        }
        else{
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if(fragment instanceof FragmentOne){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}