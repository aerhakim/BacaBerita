package io.github.aerhakim.bacaberita.utils.api;

import android.content.Context;
import android.widget.Toast;

import io.github.aerhakim.bacaberita.R;
import io.github.aerhakim.bacaberita.models.Article;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Config {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHealines(DataListener listener, String category, String query){
        CallApi callApi = retrofit.create(CallApi.class);
        Call<Article> call = callApi.callheadlines("id", category, query, context.getString(R.string.api_key));
       try {
            call.enqueue(new Callback<Article>() {
                @Override
                public void onResponse(Call<Article> call, Response<Article> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }

                listener.onFetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {
                listener.onError("Request Failed!");
                }
            });
        } catch (Exception e){
           e.printStackTrace();
       }
    }

    public Config(Context context) {
        this.context = context;
    }

    public interface CallApi {
        @GET("top-headlines")
        Call<Article> callheadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key
        );
    }
}
