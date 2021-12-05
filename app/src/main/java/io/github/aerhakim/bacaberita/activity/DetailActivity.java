package io.github.aerhakim.bacaberita.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.models.Article;

public class DetailActivity extends AppCompatActivity {

    Article article;
    TextView title, author, date, detail, content;
    ImageView iv_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.tv_detail_title);
        author = findViewById(R.id.tv_detail_author);
        date = findViewById(R.id.tv_detail_date);
        detail = findViewById(R.id.tv_detail_detail);
        content = findViewById(R.id.tv_detail_content);
        iv_detail = findViewById(R.id.iv_detail);
        article = (Article) getIntent().getSerializableExtra("data");
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        date.setText((article.getPublishedAt()));
        detail.setText(article.getDescription());
        content.setText(article.getContent());
        Picasso.get().load(article.getUrlToImage()).into(iv_detail);
    }
}