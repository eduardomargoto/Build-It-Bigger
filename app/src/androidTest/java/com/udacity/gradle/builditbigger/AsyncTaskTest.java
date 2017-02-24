package com.udacity.gradle.builditbigger;

import android.support.annotation.UiThread;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by EDUARDO_MARGOTO on 24/02/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AsyncTaskTest {

    @Test()
    @UiThread
    public void testEndpointAsyncTask() throws Exception {
        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask();
        assertNotNull("Null response relieved, make sure local server is running",
                endpointAsyncTask.execute().get());
    }

}
