package com.example.tmdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //atadjuk constructorba
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        bottomNavigationView.setVisibility(View.INVISIBLE);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment login = new LoginFragment(bottomNavigationView);
        ft.add(R.id.fg_placeholder,login);
        ft.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.nav_favorite:
                            selectedFragment = new FavoriteFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fg_placeholder,selectedFragment).commit();

                    return true;
                }
            };

}
