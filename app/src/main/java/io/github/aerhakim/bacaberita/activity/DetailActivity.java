package io.github.aerhakim.bacaberita.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.models.Article;

public class DetailActivity extends AppCompatActivity {

    Article article;
    TextView title, author, date, detail, content, url, tv_readmore;
    LinearLayout share, copy;
    ImageView iv_detail;


    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.tv_detail_title);
        author = findViewById(R.id.tv_detail_author);
        date = findViewById(R.id.tv_detail_date);
        detail = findViewById(R.id.tv_detail_detail);
        url = findViewById(R.id.tv_url);
        content = findViewById(R.id.tv_detail_content);
        iv_detail = findViewById(R.id.iv_detail);
        share = findViewById(R.id.tv_share);
        copy = findViewById(R.id.tv_copy);
        tv_readmore = findViewById(R.id.tv_readmore);
        article = (Article) getIntent().getSerializableExtra("data");
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        date.setText((article.getPublishedAt()));
        url.setText((article.getUrl()));
        detail.setText(article.getDescription());
        content.setText(article.getContent());
        Picasso.get().load(article.getUrlToImage()).into(iv_detail);

        //Intent share
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Baca Berita App's\n\n" + article.getTitle() + "\n\nBaca Selengkapnya : " + (article.getUrl()));
                sendIntent.setType("text/plain");
                Intent shareIntent =  Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        //Intent kembali ke MainActivity
        Toolbar ivBack=findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        //Intent Baca berita di browser
        tv_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (article.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //Copy Link
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text;
                text = (article.getUrl());
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(), "Link Berita Berhasil dicopy", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();

    }
}