package io.github.aerhakim.bacaberita.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.activity.DetailActivity;
import io.github.aerhakim.bacaberita.models.Article;
import io.github.aerhakim.bacaberita.models.Response;
import io.github.aerhakim.bacaberita.utils.SearchAdapter;
import io.github.aerhakim.bacaberita.utils.SelectListener;
import io.github.aerhakim.bacaberita.utils.api.Config;
import io.github.aerhakim.bacaberita.utils.api.DataListener;


public class SearchFragment extends Fragment implements SelectListener {


    RecyclerView recyclerView;
    SearchAdapter adapter;
    ProgressDialog dialog;
    SearchView searchView;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        shimmerFrameLayout = view.findViewById(R.id.shimmerLayout);
        searchView = view.findViewById(R.id.search_bar);
        Config config = new Config(getActivity());
        config.getNewsHealines(listener,"general", null);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Config config = new Config(getActivity());
                config.getNewsHealines(listener,"general", query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }



    private final DataListener<Response> listener = new DataListener<Response>() {
        @Override
        public void onFetchData(List<Article> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(getActivity(), "Data Tidak di Temukan!", Toast.LENGTH_SHORT).show();
            } else {
                showNews(list);
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getActivity(), "Gagal Mengambil Data!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<Article> list) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapter = new SearchAdapter(getActivity(), list, this);
        recyclerView.setAdapter(adapter);
        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnNewsClicked(Article article) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        startActivity(intent.putExtra("data", article));
    }


    @Override
    public void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }
}