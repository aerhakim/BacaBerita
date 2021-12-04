package io.github.aerhakim.bacaberita.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import io.github.aerhakim.bacaberita.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView title, source;
    ImageView iv_headline;
    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.tv_title);
        source = itemView.findViewById(R.id.tv_source);
        iv_headline = itemView.findViewById(R.id.iv_headline);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
