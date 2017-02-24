package com.udacity.gradle.builditbigger;


import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import br.com.etm.gce.backend.myJokeApi.MyJokeApi;


/**
 * Created by EDUARDO_MARGOTO on 23/02/2017.
 */

public class EndpointAsyncTask extends AsyncTask<Object, Object, String> {

    final static String URL_ROOT = "http://10.0.2.2:8080/_ah/api/";

    MyJokeApi myApiService;
    private EndpointCompleteListener endpointCompleteListener;

    @Override
    protected String doInBackground(Object... params) {

        if(myApiService == null) {  // Only do this once
            MyJokeApi.Builder builder = new MyJokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(URL_ROOT)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            myApiService = builder.build();
        }
        try {
            return myApiService.randomJoke().execute().getJokeData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (endpointCompleteListener != null) {
            endpointCompleteListener.onComplete(s);
        }

    }

    public EndpointAsyncTask  setEndpointCompleteListener(EndpointCompleteListener endpointCompleteListener) {
        this.endpointCompleteListener = endpointCompleteListener;
        return this;
    }

    public interface EndpointCompleteListener {

        void onComplete(String joke);
    }
}
