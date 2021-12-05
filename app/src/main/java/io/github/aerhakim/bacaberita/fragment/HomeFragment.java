package io.github.aerhakim.bacaberita.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Memuat Berita");
        dialog.setMessage("Mohon Tunggu Sebentar...");
        dialog.show();
        Config config = new Config(getActivity());
        config.getNewsHealines(listener,"general", null);


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

}