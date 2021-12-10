package io.github.aerhakim.bacaberita.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.balysv.materialripple.MaterialRippleLayout;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.activity.MainActivity;
import io.github.aerhakim.bacaberita.activity.TosActivity;


public class SettingFragment extends Fragment {

    //Inisialisasi Variabel
    MaterialRippleLayout btnTos, btnSave, btnMore;
    SwitchCompat switchCompat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        Toolbar ivBack= view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
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
        view.findViewById(R.id.btn_about).setOnClickListener(action -> aboutDialog());
        return view;
    }
    public void aboutDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity());
        View view = layoutInflaterAndroid.inflate(R.layout.custom_dialog_about, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(view);
        alert.setCancelable(false);
        alert.setPositiveButton(R.string.dialog_ok, (dialog, which) -> dialog.dismiss());
        alert.show();
    }
}