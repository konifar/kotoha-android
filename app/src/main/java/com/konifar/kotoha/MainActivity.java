package com.konifar.kotoha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.konifar.kotoha.models.pojo.Phrase;
import com.konifar.kotoha.utils.AppUtils;
import com.konifar.kotoha.views.adapters.PhrasesArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.txt_empty)
    TextView txtEmpty;
    @Bind(R.id.loading)
    View loading;
    @Bind(R.id.txt_error)
    TextView txtError;

    private PhrasesArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initListView();

        loadData();
    }

    private void loadData() {
        // TODO Replace valid params
        Observable<List<Phrase>> observable = MainApplication.API.getPhrasesByText("愛");
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Phrase>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Completed.");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, t.getMessage());
                        txtError.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<Phrase> phrases) {
                        txtError.setVisibility(View.GONE);
                        txtEmpty.setVisibility(View.GONE);
                        loading.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        adapter.addAll(phrases);
                    }
                });
    }

    private void initListView() {
        adapter = new PhrasesArrayAdapter(this);
        listView.setAdapter(adapter);
    }

    @SuppressWarnings("unused")
    @OnItemClick(R.id.list_view)
    void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        //
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.action_link:
                AppUtils.showWebPage(Constants.REPOGITORY_URL, this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
