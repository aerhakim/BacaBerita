package io.github.aerhakim.bacaberita.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.fragment.CategoryFragment;
import io.github.aerhakim.bacaberita.fragment.HomeFragment;
import io.github.aerhakim.bacaberita.fragment.SearchFragment;
import io.github.aerhakim.bacaberita.fragment.SettingFragment;


public class MainActivity extends AppCompatActivity  {

    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipNavigationBar = findViewById(R.id.navigation);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case  R.id.category:
                        fragment = new CategoryFragment();
                        break;

                    case R.id.search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.setting:
                        fragment = new SettingFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
            }
        });
    }
    @Override
    public void onBackPressed() {

        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Ingin Keluar?")
                .setMessage("Apakah Anda ingin keluar dari Baca Berita?")
                .setCancelable(false)
                .setPositiveButton("Keluar", R.drawable.ic_baseline_exit_to_app_24, new MaterialDialog.OnClickListener() {


                    @Override
                    public void onClick(dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Batal", R.drawable.ic_baseline_close_24, new MaterialDialog.OnClickListener() {


                    @Override
                    public void onClick(dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .build();
        // Show Dialog
        mDialog.show();

    }
}