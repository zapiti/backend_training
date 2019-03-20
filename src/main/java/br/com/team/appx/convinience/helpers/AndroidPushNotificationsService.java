package br.com.team.appx.convinience.helpers;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAAvtW-Lws:APA91bGph2ApbgpB8eRlmXH1_dtHStZbhqsmX0xm_y4xJMMKbx326D-fVkUZ--5j_MnJY3u21d7Tgb2G3oe146uIwzxj4hToCbX6Z_-lQHtU7SuWHbahOXR9hsCa20-BI7Q7SbHLSRSm";

    @Async
    public CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        FirebaseResponse firebaseResponse = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", entity, FirebaseResponse.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

}
