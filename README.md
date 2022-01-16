# Baca Berita
![bacaberita](https://aerhakim.github.io/images/bbR.png)
Baca Berita adalah sebuah aplikasi portal berita lokal yang dibuat menggunakan API dari Newsapi.org

[Download APK File](https://drive.google.com/file/d/1Bvb-I9a8rbvFBVner2Q372vmFGcCLl_Q/view?usp=sharing)

# Preview

Splash screen & OnBoarding screen

https://user-images.githubusercontent.com/79903756/145537677-6d95c4fc-3ad9-492c-a92e-d3c4804710f9.mp4

Home Fragment. Category Fragment, Search Fragment, Info Fragment

https://user-images.githubusercontent.com/79903756/145538442-b4465694-4160-40e3-809d-5fb142ec1b84.mp4

Detail Activity / Detail Artikel

https://user-images.githubusercontent.com/79903756/145538246-99f047d8-61f0-4d6c-8d75-d23f28873160.mp4

Category Fragment

https://user-images.githubusercontent.com/79903756/145538062-7aea4391-3609-41be-8714-5920f40d02c1.mp4

Search Fragment

https://user-images.githubusercontent.com/79903756/145538156-7a8aef39-9333-4e6e-b1a6-3292cda0f4eb.mp4

Info Fragment

https://user-images.githubusercontent.com/79903756/145538201-9001418a-3a10-4954-b39e-c33574e835ca.mp4

Baca Berita merupakan sebuah portal berita mobile yang berisi artikel berita dari situs portal berita besar di Indonesia seperti CNN, CNBC, Sindonews, Tempo, Tribunnews dan lain-lain. Baca Berita menggunakan API dari Newsapi.org, pada masa development, developer masih menggunakan API free yang dikhususkan untuk testing.

Sehingga beberapa fitur menyesuaikan dengan keterbatasan yang ada. Jika anda berkenan untuk menggunakan API berbayar dari Newsapi.org anda hanya perlu melakukan perubahan di String.xml, activity_detail.xml dan DetailActivity.java

string.xml
```
<string name="api_key">YOUR_API_KEY</string>
```
activity_detail.xml
```
//Hapus Textview dengan id tv_readmore
<TextView
android:layout_marginTop="10dp"
android:id="@+id/tv_readmore"
android:layout_width="wrap_content"
android:textSize="16sp"
android:textColor="@color/primary"
android:text="[Baca Selengkapnya]"
android:textStyle="bold"
android:textAppearance="@style/Base.TextAppearance.AppCompat.Body1"
android:layout_height="wrap_content" />
```
DetailActivity.java
```
//Hapus Deklrasi tv_readmore
TextView tv_readmore;

//Hapus Insialisasi tv_readmore di dalam onCreate
tv_readmore = findViewById(R.id.tv_readmore);

//Hapus onClickListener di dalam onCreate
        tv_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (article.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
```

# LICENSE

```
Copyright (C) 2021 AR Hakim AN NUR Addin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

