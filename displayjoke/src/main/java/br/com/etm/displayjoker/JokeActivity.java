package br.com.etm.displayjoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by EDUARDO_MARGOTO on 23/02/2017.
 */

public class JokeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity);

        String joke = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        ((TextView) findViewById(R.id.joke_text_view)).setText(joke);

    }
}
