package io.github.aerhakim.bacaberita.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.balysv.materialripple.MaterialRippleLayout;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.activity.TosActivity;


public class SettingFragment extends Fragment {

    //Inisialisasi Variabel
    MaterialRippleLayout btnTos, btnSave, btnMore;
    SwitchCompat switchCompat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            getActivity().setTheme(R.style.Theme_BacaBeritaDark);
        } else {
            getActivity().setTheme(R.style.Theme_BacaBerita);
        }
        View view = inflater
        .inflate(R.layout.fragment_setting, container, false);


        btnTos = view.findViewById(R.id.btn_privacy_policy);
        btnSave = view.findViewById(R.id.btn_save);
        btnMore = view.findViewById(R.id.btn_more);

        //Intent ke TosActivity
        btnTos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TosActivity.class);
                startActivity(i);
            }
        });

        //Intent ke url Repo project
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/aerhakim/bacaberita/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //Intent ke url Portfolio
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://aerhakim.github.io/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        switchCompat = view.findViewById(R.id.switch_theme);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        return view;
    }

}