package br.com.team.appx.convinience.controllers;

import br.com.team.appx.convinience.helpers.AndroidPushNotificationsService;
import br.com.team.appx.convinience.helpers.FirebaseResponse;
import br.com.team.appx.convinience.keys.NotificationKeys;
import br.com.team.appx.convinience.model.FCNotification;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SuppressWarnings("unchecked")
@RestController
public class NotificationController {

    @Autowired
    private
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public HttpStatus send(@RequestBody FCNotification values) throws IOException {

        if (values.body == null && values.title == null) {
            return HttpStatus.BAD_REQUEST;
        }

        JSONObject body = new JSONObject();
        body.put("to", NotificationKeys.tokenUserTest);
        body.put("priority", NotificationKeys.priority);


        HashMap<String, String> notification = new HashMap<>();
        notification.put("title", values.title);
        notification.put("body", values.body);

        HashMap<String, String> data = new HashMap<>();
        data.put("title", values.title);
        data.put("body", values.body);

        body.put("notification", notification);
        body.put("data", data);


        HttpEntity<String> request = new HttpEntity<>(body.toString());

        if (androidPushNotificationsService == null) {
            androidPushNotificationsService = new AndroidPushNotificationsService();
        }

        CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            FirebaseResponse firebaseResponse = pushNotification.get();
            if (firebaseResponse.getSuccess() == 1) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.BAD_REQUEST;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        return HttpStatus.BAD_REQUEST;
    }
}
