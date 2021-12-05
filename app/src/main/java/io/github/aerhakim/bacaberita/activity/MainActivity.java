package io.github.aerhakim.bacaberita.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.fragment.CategoryFragment;
import io.github.aerhakim.bacaberita.fragment.HomeFragment;
import io.github.aerhakim.bacaberita.fragment.SettingFragment;
import io.github.aerhakim.bacaberita.fragment.SourceFragment;

public class MainActivity extends AppCompatActivity  {
//public class MainActivity extends AppCompatActivity implements SelectListener {

//    RecyclerView recyclerView;
//    CustomAdapter adapter;
//    ProgressDialog dialog;
    public BottomNavigationView navigation;
    public ViewPager viewPager;
    MenuItem prevMenuItem;
    int pager_number = 4;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(android.R.id.content);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(pager_number);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_category:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_source:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_setting:
                    viewPager.setCurrentItem(3);
//                    Jika mau pindah dari fragment ke activity
//                            Intent i = new Intent(this, SettingActivity.class);
//                            startActivity(i);
                    return true;
            }
            return false;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        dialog = new ProgressDialog(this);
//        dialog.setTitle("Memuat Berita...");
//        dialog.show();
//        Config config = new Config(this);
//        config.getNewsHealines(listener,"general", null);
    }
    public class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new CategoryFragment();
                case 2:
                    return new SourceFragment();
                case 3:
                    return new SettingFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return pager_number;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    private final DataListener<Response> listener = new DataListener<Response>() {
//        @Override
//        public void onFetchData(List<Article> list, String message) {
//            showNews(list);
//            dialog.dismiss();
//        }
//
//        @Override
//        public void onError(String message) {
//
//        }
//    };

//    private void showNews(List<Article> list) {
//        recyclerView = findViewById(R.id.rv_main);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
//        adapter = new CustomAdapter(this, list, this);
//        recyclerView.setAdapter(adapter);
//    }

//    @Override
//    public void OnNewsClicked(Article article) {
//        startActivity(new Intent(MainActivity.this, DetailActivity.class)
//        .putExtra("data", article));
//    }
}