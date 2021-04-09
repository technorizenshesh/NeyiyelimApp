package com.laapesca.neyiyelimapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.laapesca.neyiyelimapp.R;
import com.laapesca.neyiyelimapp.databinding.ActivityHomeScreenBinding;
import com.laapesca.neyiyelimapp.fragment.CartDetailFragment;
import com.laapesca.neyiyelimapp.fragment.HomeFragment;
import com.laapesca.neyiyelimapp.listner.FragmentListener;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class HomeScreenActivity extends AppCompatActivity implements Function1<MeowBottomNavigation.Model, Unit>, FragmentListener {

    ActivityHomeScreenBinding binding;
    Fragment fragment;
    private AdvanceDrawerLayout drawer;
    private int CELL_ID;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_menu);
        getSupportActionBar().setTitle("Home");


        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
       // navigationView.setNavigationItemSelectedListener(this);

        drawer.setViewScale(Gravity.START, 0.8f);
        drawer.setRadius(Gravity.START, 35);
        drawer.setViewElevation(Gravity.START, 80);

        Toast.makeText(getApplicationContext(), "Swipe From Left To open Drawer", Toast.LENGTH_LONG).show();


        fragment = new HomeFragment(this);
        loadFragment(fragment);


        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.card));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.search));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.profile));

        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(this);

        Init();

    }

    private void Init() {

        binding.navId.txNe.setOnClickListener(v -> {
            // startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });


        binding.navId.txAddMerchant.setOnClickListener(v -> {
            // startActivity(new Intent(this,.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.txCuisene.setOnClickListener(v -> {
            // startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });


        binding.navId.txPromotiones.setOnClickListener(v -> {
            startActivity(new Intent(this, PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.txtUpdateProfi.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.txtRestaurant.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.txtInbox.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });
        binding.navId.myTats.setOnClickListener(v -> {
            startActivity(new Intent(this, MyTatsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.tellFrend.setOnClickListener(v -> {
            startActivity(new Intent(this, TellAFriendActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.latestMerchant.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.tatStore.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.faq.setOnClickListener(v -> {
            startActivity(new Intent(this, FAQActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });


        binding.navId.help.setOnClickListener(v -> {
            startActivity(new Intent(this, HelpActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.aboutUs.setOnClickListener(v -> {
            startActivity(new Intent(this, AboutActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.pressMedia.setOnClickListener(v -> {
            startActivity(new Intent(this, PressMediaActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.partner.setOnClickListener(v -> {
            startActivity(new Intent(this, PartnerActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });


        binding.navId.job.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.contactUs.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.chngLang.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.chngCity.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });

        binding.navId.logout.setOnClickListener(v -> {
            //startActivity(new Intent(this,PromotionsActivity.class));
            drawer.closeDrawer(GravityCompat.START);

        });


    }


    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().addToBackStack("home").replace(R.id.mainContent, fragment).commit();
        }
    }


    /*   public void loadFragment(Fragment fragment) {
           // load fragment
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           transaction.replace(R.id.mainContent, fragment);
           transaction.addToBackStack("home");
           transaction.commit();
       }
   */
   /* @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
*/
    @Override
    public Unit invoke(MeowBottomNavigation.Model model) {
        switch (model.getId()) {


            case 1:
                Toast.makeText(HomeScreenActivity.this, "item 1", Toast.LENGTH_SHORT).show();
                fragment = new HomeFragment(this);
                loadFragment(fragment);
                break;
            case 2:
                Toast.makeText(HomeScreenActivity.this, "item 2", Toast.LENGTH_SHORT).show();
                fragment = new CartDetailFragment(this);
                loadFragment(fragment);
                break;
            case 3:
                Toast.makeText(HomeScreenActivity.this, "item 3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(HomeScreenActivity.this, "item 0", Toast.LENGTH_SHORT).show();
                break;
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle("Home");
    }

    @Override
    public void click(Fragment fragment) {
        loadFragment(fragment);
    }

}