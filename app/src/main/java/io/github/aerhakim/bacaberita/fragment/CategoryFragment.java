package io.github.aerhakim.bacaberita.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;

import java.util.List;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.activity.DetailActivity;
import io.github.aerhakim.bacaberita.models.Article;
import io.github.aerhakim.bacaberita.models.Response;
import io.github.aerhakim.bacaberita.utils.CustomAdapter;
import io.github.aerhakim.bacaberita.utils.SelectListener;
import io.github.aerhakim.bacaberita.utils.api.Config;
import io.github.aerhakim.bacaberita.utils.api.DataListener;


public class CategoryFragment extends Fragment implements SelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button teknologi, olahraga, bisnis, kesehatan, hiburan, sains;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.rv_main_cat);
        teknologi = view.findViewById(R.id.btn_tekonologi);
        teknologi.setOnClickListener(this);
        olahraga = view.findViewById(R.id.btn_olahraga);
        olahraga.setOnClickListener(this);
        bisnis = view.findViewById(R.id.btn_bisnis);
        bisnis.setOnClickListener(this);
        kesehatan = view.findViewById(R.id.btn_kesehatan);
        kesehatan.setOnClickListener(this);
        hiburan = view.findViewById(R.id.btn_hiburan);
        hiburan.setOnClickListener(this);
        sains = view.findViewById(R.id.btn_sains);
        sains.setOnClickListener(this);
        Config config = new Config(getActivity());
        config.getNewsHealines(listener,"technology", null);

        cekKoneksi();

        return view;
    }


    public void cekKoneksi () {
        if(isNetworkAvailable()) {
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Memuat Berita");
            dialog.setMessage("Mohon Tunggu Sebentar...");
            dialog.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(true);
            builder.setTitle("Tidak ada Koneksi Internet!");
            builder.setMessage("Silahkan Periksa Koneksi Internet Anda dan Coba Kembali!");
            builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent j = getActivity().getIntent();
                    j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().finish();
                    startActivity(j);
                }
            });

            builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            });

            AlertDialog dialog  = builder.create();
            dialog.show();
            Toast.makeText(getActivity(),"Koneksi Internet Tidak Ada", Toast.LENGTH_SHORT).show();

        }
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    private final DataListener<Response> listener = new DataListener<Response>() {
        @Override
        public void onFetchData(List<Article> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<Article> list) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter = new CustomAdapter(getActivity(), list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(Article article) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        startActivity(intent.putExtra("data", article));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        dialog.setTitle("Memuat kategori " + category);
        dialog.setMessage("Mohon Tunggu Sebentar...");
        dialog.show();
        Config config = new Config(getActivity());
        config.getNewsHealines(listener,category, null);
    }
}