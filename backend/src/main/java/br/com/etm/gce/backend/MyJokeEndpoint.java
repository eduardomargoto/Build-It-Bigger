/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package br.com.etm.gce.backend;

import com.example.JokeRepo;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.Random;


/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myJokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.gce.etm.com.br",
                ownerName = "backend.gce.etm.com.br"
        )
)
public class MyJokeEndpoint {

    @ApiMethod(name = "randomJoke",
            path = "randomJoke",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public MyJoke getRandomJoke() {

        Random random = new Random();
        int position = random.nextInt(JokeRepo.jokers.length);

        MyJoke data = new MyJoke();
        data.setJokeData(JokeRepo.jokers[position]);

        return data;
    }

}
