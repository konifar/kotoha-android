package com.konifar.kotoha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.konifar.kotoha.models.PhraseModel;
import com.konifar.kotoha.models.pojo.Phrase;
import com.konifar.kotoha.utils.AppUtils;
import com.konifar.kotoha.views.adapters.PhrasesArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edit_search)
    EditText editSearch;
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.txt_empty)
    TextView txtEmpty;
    @Bind(R.id.loading)
    View loading;
    @Bind(R.id.txt_error)
    TextView txtError;

    private PhrasesArrayAdapter adapter;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initListView();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        subscription = Subscriptions.empty();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e(TAG, "beforeTextChanged: " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e(TAG, "onTextChanged: " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                loadData(s.toString());
            }
        });
    }

    private void loadData(String searchText) {
        adapter.clear();
        loading.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

        subscription = new PhraseModel().getListByText(searchText, new Observer<List<Phrase>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Completed.");
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, t.getMessage());
                loading.setVisibility(View.GONE);
                txtEmpty.setVisibility(View.GONE);
                txtError.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
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
