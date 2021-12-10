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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.activity.DetailActivity;
import io.github.aerhakim.bacaberita.models.Article;
import io.github.aerhakim.bacaberita.models.Response;
import io.github.aerhakim.bacaberita.utils.CustomAdapter;
import io.github.aerhakim.bacaberita.utils.SelectListener;
import io.github.aerhakim.bacaberita.utils.api.Config;
import io.github.aerhakim.bacaberita.utils.api.DataListener;


public class HomeFragment extends Fragment implements SelectListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    ShimmerFrameLayout shimmerFrameLayout1, shimmerFrameLayout2;
    LinearLayout lin1, lin2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        shimmerFrameLayout1 = view.findViewById(R.id.shimmerLayout1);
        shimmerFrameLayout2 = view.findViewById(R.id.shimmerLayout2);
        lin1 = view.findViewById(R.id.lin1);
        lin2 = view.findViewById(R.id.lin2);
        Config config = new Config(getActivity());
        config.getNewsHealines(listener,"general", null);
        cekKoneksi();
        return view;
    }



    private final DataListener<Response> listener = new DataListener<Response>() {
        @Override
        public void onFetchData(List<Article> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getActivity(), "Gagal Mengambil Data!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<Article> list) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter = new CustomAdapter(getActivity(), list, this);
        recyclerView.setAdapter(adapter);
        shimmerFrameLayout1.startShimmer();
        shimmerFrameLayout1.setVisibility(View.GONE);
        shimmerFrameLayout2.startShimmer();
        shimmerFrameLayout2.setVisibility(View.GONE);
        lin1.setVisibility(View.GONE);
        lin2.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnNewsClicked(Article article) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        startActivity(intent.putExtra("data", article));
    }


    public void cekKoneksi () {
        if(isNetworkAvailable()) {
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Memuat Berita");
            dialog.setMessage("Mohon Tunggu Sebentar...");
            dialog.dismiss();
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
    @Override
    public void onResume() {
        shimmerFrameLayout1.startShimmer();
        shimmerFrameLayout2.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout1.stopShimmer();
        shimmerFrameLayout2.stopShimmer();
        super.onPause();
    }

}