package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.etm.displayjoker.JokeActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by EDUARDO_MARGOTO on 24/02/2017.
 */

public class MainActivityFragment extends Fragment implements EndpointAsyncTask.EndpointCompleteListener {

    @BindView(R.id.progressBar)
    ProgressBar loading_progress;
    @BindView(R.id.button_joke)
    Button button_joke;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @OnClick(R.id.button_joke)
    public void callJoke(View view) {
        loading_progress.setVisibility(View.VISIBLE);
        button_joke.setVisibility(View.GONE);
        new EndpointAsyncTask()
                .setEndpointCompleteListener(this)
                .execute();
    }


    @Override
    public void onComplete(String joke) {

        if (joke != null && !joke.isEmpty()) {
            Intent intent = new Intent(getActivity(), JokeActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, joke);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Joke not found.", Toast.LENGTH_SHORT).show();
        }

        button_joke.setVisibility(View.VISIBLE);
        loading_progress.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
