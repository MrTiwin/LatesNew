package pe.edu.utp.latesnew.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.latesnew.R;
import pe.edu.utp.latesnew.adapters.SourcesAdapter;
import pe.edu.utp.latesnew.models.Source;
import pe.edu.utp.latesnew.services.NewsService;

public class MainActivity extends AppCompatActivity {
    RecyclerView sourcesRecyclerView;
    GridLayoutManager sourcesLayoutManager;
    SourcesAdapter sourcesAdapter;
    List<Source> sources;
    int spanCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sourcesRecyclerView = (RecyclerView) findViewById(R.id.sourcesRecyclerView);
        spanCount = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)?3:2;
        sourcesLayoutManager = new GridLayoutManager(this,spanCount);
        sourcesAdapter = new SourcesAdapter();
        sources = new ArrayList<>();
        sourcesAdapter.setSources(sources);
        sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);
        sourcesRecyclerView.setAdapter(sourcesAdapter);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateSources(){
        AndroidNetworking
                .get(NewsService.SOURCE_URL)
                .setTag("News")
                .setPriority(Priority.LOW)
                .addPathParameter("language","en")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("ok")){
                                sources = Source.buildFromJSONArray(response.getJSONArray("sources"));
                                sourcesAdapter.setSources(sources);
                                sourcesAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateSources();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        spanCount = (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)?3:2;
        sourcesLayoutManager.setSpanCount(spanCount);
        sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);
        super.onConfigurationChanged(newConfig);
    }
}
