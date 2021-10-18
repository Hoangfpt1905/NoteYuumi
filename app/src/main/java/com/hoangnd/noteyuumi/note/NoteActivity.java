package com.hoangnd.noteyuumi.note;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.hoangnd.noteyuumi.R;
import com.hoangnd.noteyuumi.statistics.Statistics_Fragment;

/**
 * Author by Hoangnd on 18/10/2021
 */


public class NoteActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    Fragment fragment = null;
    private NavigationView navView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initViews();

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        toolbar.setTitle("Note App");
        toolbar.setTitleTextColor(getColor(R.color.colorTitleToolBar));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameContent, new Note_Fragment());
        ft.commit();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                displayFragment(menuItem.getItemId());
                drawerLayout.closeDrawer(Gravity.START);
                return false;
            }
        });
    }

    private void displayFragment(int item){

        switch (item){
            case R.id.menu_tasks_act:
                fragment = new Note_Fragment();
                break;
            case R.id.menu_statistics:
                fragment = new Statistics_Fragment();
                break;
            default:
                fragment = new Note_Fragment();
                break;
        }
        if (fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameContent, fragment);
            ft.commit();
        }
    }

    private void initViews(){
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.nav_view);
    }
}
