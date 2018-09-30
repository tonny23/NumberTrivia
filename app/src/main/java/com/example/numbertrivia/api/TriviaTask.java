package com.example.numbertrivia.api;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.numbertrivia.MainActivity;
import com.example.numbertrivia.data.Trivia;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class TriviaTask extends AsyncTask<Void, Void, Trivia> {
    private WeakReference<MainActivity> mMainActivityWeakReference;

    public TriviaTask(MainActivity mainActivity) {
        // Put the reference to the main reference in a weakReference, otherwise possible memory leaks
        mMainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Override
    protected void onPreExecute() {
        // Get the mainActivity from the weakReference
        MainActivity mainActivity = mMainActivityWeakReference.get();
        // Set progressbar to visible
        if (mainActivity.getCurrentFocus() != null) {
            Toast.makeText(mainActivity.getApplicationContext(), "Loading trivia",
                    Toast.LENGTH_SHORT)
                    .show(); // Inform the user the task is starting
        }
    }

    @Override
    protected Trivia doInBackground(Void... voids) {
        // Get the interface where the http requests are in.
        TriviaApiService service = ApiClient.getClient().create(TriviaApiService.class);
        Trivia trivia = null;
        try {
            trivia = getTrivia(service);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trivia;
    }

    private Trivia getTrivia(TriviaApiService triviaApiService) throws IOException {
        // We call execute on the getRandomTriviaItem, this means this function is called synchronous.
        // Also the body is taken in the body is the trivial item we just got from the numbers api.
        return triviaApiService.getTrivia().execute().body();
    }

    @Override
    protected void onPostExecute(Trivia trivia) {
        // Called when doInBackground finishes
        MainActivity mainActivity = mMainActivityWeakReference.get();
        if (trivia == null) {
            Toast.makeText(mainActivity.getApplicationContext(), "Downloading the quote failed", Toast.LENGTH_SHORT).show();
            mainActivity.setTrivia("No quote is found, try to reopen the application", 0);
            //Further execution of the method is not needed
            return;
        }
        mainActivity.setTrivia(trivia.getText(), trivia.getNumber());
    }
}
