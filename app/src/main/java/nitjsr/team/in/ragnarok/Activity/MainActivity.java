package nitjsr.team.in.ragnarok;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import nitjsr.team.in.ragnarok.utils.AppConstants;

import static nitjsr.team.in.ragnarok.utils.AppConstants.fetchGoodsItemList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        getSupportActionBar().hide();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_search, R.id.navigation_shopping_list, R.id.navigation_featured, R.id.navigation_scan_list, R.id.navigation_start_shopping)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (mSharedPreferences.getBoolean("firstTime", true) == true) {
//            fetchItemList();
//        }
        if (AppConstants.mItemList.size() == 0) {
            fetchGoodsItemList(MainActivity.this);
        }
    }

}