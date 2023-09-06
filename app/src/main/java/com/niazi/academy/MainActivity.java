package com.niazi.academy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.niazi.academy.databinding.ActivityMainBinding;
import com.niazi.academy.fragment.Main_fragment;
import com.niazi.academy.fragment.Upload_Detail;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolBar);

        DrawerLayout drawer = binding.drawerlay;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.upload, R.id.showDetail, R.id.Exit)
                .setOpenableLayout(drawer)
                .build();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, binding.drawerlay, R.string.OpenDrawer, R.string.CloseDrawer);
        binding.drawerlay.addDrawerListener(toggle);
        toggle.syncState();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.upload) {
                    Upload_Detail uploadDetail = new Upload_Detail();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.commit();

                    transaction.replace(R.id.layoutcon,uploadDetail); // Handle the click on Item 1, e.g., launch an intent or perform an action.

                    } else if (itemId == R.id.showDetail) {

                    Main_fragment mainFragment = new Main_fragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.commit();

                    transaction.replace(R.id.layoutcon,mainFragment); // Handle the click on Item 1, e.g., launch an intent or perform an action.

                    // Handle the click on Item 2, e.g., launch an intent or perform an action.
                }else if (itemId == R.id.Exit) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Exit");
                    alert.setMessage("Are you sure you want to exit the app?");

                    alert.setCancelable(false);
                    alert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            finish(); // Close the activity and exit the app
                        }}
                    ).
                            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert.show();
                    // Handle the click on Item 2, e.g., launch an intent or perform an action.
                }
                // Close the drawer
                binding.drawerlay.closeDrawer(GravityCompat.START);
                return true;
            }

          });

/*

        Navigation.navigate(R.id.action_nav_home_to_nav_UploadCategory);
*/




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerlay.isDrawerOpen(GravityCompat.START)) {
            binding.drawerlay.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }



    @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }
    }