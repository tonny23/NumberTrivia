package com.example.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.numbertrivia.api.TriviaTask;
import com.example.numbertrivia.data.Trivia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.rvNumbers)
    RecyclerView rvTrivia;

    @BindView(R.id.fabNew)
    FloatingActionButton fabNew;

    private TriviaAdapter mAdapter;
    private List<Trivia> mTrivias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new TriviaAdapter(mTrivias);
        rvTrivia.setLayoutManager(new LinearLayoutManager(this));
        rvTrivia.setAdapter(mAdapter);
        newItem();
        updateUI();
    }

    /**
     * Execute the trivia task to retrieve a new trivia
     */
    @OnClick(R.id.fabNew)
    public void newItem() {
        new TriviaTask(this).execute();
    }

    /**
     * Add a new trivia to the list and update the recyclerview
     * @param text trivia text
     * @param number number of the trivia
     */
    public void setTrivia(String text, int number) {
        Log.d(TAG, "setTrivia: " + text);
        mTrivias.add(new Trivia(text, number));
        updateUI();
    }

    /**
     * Update the recyclerview
     */
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new TriviaAdapter(mTrivias);
            rvTrivia.setLayoutManager(new LinearLayoutManager(this));
            rvTrivia.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
