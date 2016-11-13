package pe.edu.utp.latesnew.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.latesnew.LatesNewsApp;
import pe.edu.utp.latesnew.R;
import pe.edu.utp.latesnew.adapters.ArticlesAdapter;
import pe.edu.utp.latesnew.models.Article;
import pe.edu.utp.latesnew.models.Source;
import pe.edu.utp.latesnew.services.NewsService;

public class ArticlesActivity extends AppCompatActivity {
    RecyclerView articlesRecyclerView;
    ArticlesAdapter articlesAdapter;
    GridLayoutManager articlesLayoutManger;
    List<Article> articles = new ArrayList<>();
    Source currentSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        articlesRecyclerView = (RecyclerView) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        articlesRecyclerView = (RecyclerView) findViewById(R.id.articleRecyclerView);
        articlesAdapter = new ArticlesAdapter();
        articlesAdapter.setArticles(articles);
        articlesLayoutManger = new GridLayoutManager(this,2);
        currentSource = LatesNewsApp.getInstace().getCurrentSource();
        articlesRecyclerView.setLayoutManager(articlesLayoutManger);
        articlesRecyclerView.setAdapter(articlesAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateArticles() {
        AndroidNetworking
                .get(NewsService.ARTICLES_URL)
                .setTag("articles")
                .setPriority(Priority.LOW)
                .addQueryParameter("source", currentSource.getId())
                .addQueryParameter("apiKey", getResources().getString(R.string.news_api_key))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("ok")){
                                articles = Article.buildFromJSONArray(response.getJSONArray("articles"),currentSource);
                                articlesAdapter.setArticles(articles);
                                articlesAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("LatesNews", anError.getErrorBody());
                    }
                });

    }

    @Override
    protected void onResume(){
        super.onResume();
        updateArticles();
    }

}
