package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.example.jokedisplayer.DisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

class EndPointsAsyncTask extends AsyncTask<Pair<Context, MyIdlingResource>, Void, String> {

    private static final String KEY_JOKE = "joke";
    private static MyApi myApi = null;
    private Context context;
    private MyIdlingResource idlingResource;


    @Override
    protected String doInBackground(Pair<Context, MyIdlingResource>... params) {

        context = params[0].first;
        idlingResource = params[0].second;

        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }
        if (myApi == null) {
            MyApi.Builder myApiBuilder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApi = myApiBuilder.build();
        }

        try {
            return myApi.getRandomJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent startDisplayActivity = new Intent(context, DisplayActivity.class);
        startDisplayActivity.putExtra(KEY_JOKE, result);
        context.startActivity(startDisplayActivity);
        if (idlingResource != null) {
            idlingResource.setIdleState(true);
        }
    }


}
