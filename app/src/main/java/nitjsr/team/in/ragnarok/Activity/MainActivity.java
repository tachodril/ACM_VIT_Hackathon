package nitjsr.team.in.ragnarok.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import nitjsr.team.in.ragnarok.R;
import nitjsr.team.in.ragnarok.utils.AppConstants;

import static nitjsr.team.in.ragnarok.utils.AppConstants.fetchGoodsItemList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ImageView openDrawerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        openDrawerBtn=findViewById(R.id.drawer_open_btn);

        getSupportActionBar().hide();

        setUpDrawer();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_search, R.id.navigation_shopping_list, R.id.navigation_featured, R.id.navigation_scan_list, R.id.navigation_start_shopping)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void setUpDrawer() {
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_homed:
//                        startActivity(new Intent(MainActivity.this,MainActivity.class));
//                        finish();
                        dl.close();
                        break;
                    case R.id.nav_galleryd:
//                        startActivity(new Intent(MainActivity.this,MainActivity.class));
//                        finish();
                        dl.close();
                        break;
                    case R.id.nav_slideshowd:
//                        startActivity(new Intent(MainActivity.this,MainActivity.class));
//                        finish();
                        dl.close();
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });

        openDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.open();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (mSharedPreferences.getBoolean("firstTime", true) == true) {
//            fetchItemList();
//        }
        if (AppConstants.mItemList.size() == 0) {
            fetchGoodsItemList(this);
        }
    }

}