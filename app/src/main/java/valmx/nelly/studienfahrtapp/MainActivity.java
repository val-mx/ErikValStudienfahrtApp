package valmx.nelly.studienfahrtapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import valmx.nelly.studienfahrtapp.activities.fahrtenpicker.FahrtenPicker;
import valmx.nelly.studienfahrtapp.activities.lehrerlogin.LehrerLogInActivity;
import valmx.nelly.studienfahrtapp.data.DataManager;

public class MainActivity extends AppCompatActivity {

    public static DataManager dataManager;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dataManager == null)
            dataManager = new DataManager(this);

        if (!dataManager.isTripLoaded()) {
            setContentView(R.layout.nicht_eingerichtet);
            Button b = findViewById(R.id.button);

            findViewById(R.id.buttonlehrer).setOnClickListener(l -> {
                Intent intent = new Intent(MainActivity.this, LehrerLogInActivity.class);
                startActivity(intent);
            });


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataManager.setTripLoaded(true);
                    Intent intent = new Intent(MainActivity.this, FahrtenPicker.class);
                    startActivity(intent);
                }
            });

            return;
        }

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


}