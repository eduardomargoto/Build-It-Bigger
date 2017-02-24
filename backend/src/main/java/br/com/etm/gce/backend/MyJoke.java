package br.com.etm.gce.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyJoke {

    private String jokeData;

    public String getJokeData() {
        return jokeData;
    }

    public void setJokeData(String data) {
        jokeData = data;
    }
}