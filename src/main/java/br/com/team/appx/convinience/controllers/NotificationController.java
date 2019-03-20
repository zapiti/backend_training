package br.com.team.appx.convinience.controllers;


import br.com.team.appx.convinience.dto.UserDto;
import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.helpers.AndroidPushNotificationsService;
import br.com.team.appx.convinience.helpers.FirebaseResponse;
import br.com.team.appx.convinience.keys.NotificationKeys;
import br.com.team.appx.convinience.model.Notification;
import br.com.team.appx.convinience.service.AuthenticateService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/send")
public class NotificationController {


    @Autowired
    private
    AndroidPushNotificationsService androidPushNotificationsService;


    public @PostMapping
    ResponseEntity<Object> startNotification(@RequestBody Notification notification) throws IOException {
        return send(notification);
    }

    public ResponseEntity<Object> send(Notification values) {
        if (values.body == null && values.title == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Corpo ou titulo nulos");
        }
        return sendNotification(setNotificationParams(values));
    }

    private JSONObject setNotificationParams(Notification values) {

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

        return body;
    }

    private ResponseEntity<Object> sendNotification(JSONObject body) {
        HttpEntity<String> request = new HttpEntity<>(body.toString());

        if (androidPushNotificationsService == null) {
            androidPushNotificationsService = new AndroidPushNotificationsService();
        }

        CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            FirebaseResponse firebaseResponse = pushNotification.get();
            if (firebaseResponse.getSuccess() == 1) {
                return ResponseEntity.status(HttpStatus.OK).body("Enviado com sucesso!!!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao enviar");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Objeto nao carregado");
    }


}
