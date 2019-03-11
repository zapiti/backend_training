package br.com.team.appx.convinience.controllers;


import br.com.team.appx.convinience.helpers.AndroidPushNotificationsService;
import br.com.team.appx.convinience.helpers.FirebaseResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> send() {


        JSONObject body = new JSONObject();
        // JsonArray registration_ids = new JsonArray();
        // body.put("registration_ids", registration_ids);
        body.put("to", "cTSL2z6OH40:APA91bHKzNoudrR2KO1RCp4SJPBVPdChrtkxKTNDuwb3UFyEJqtSgsUWDpxPq4EiZr_q2cCndR_q7F-7HgtvYY9GIKjk-65vKityzmOhed83XMa5tsT5b-ZCjjCuXEd-bGX53pEd8Lap");
        body.put("priority", "high");
        // body.put("dry_run", true);


        JSONObject notification = new JSONObject();
        notification.put("title", "JSA Notification");
        notification.put("body", "Happy Message!");

        JSONObject data = new JSONObject();
        data.put("title", "JSA Data 1");
        data.put("body", "JSA Data 2");

        body.put("notification", notification);
        body.put("data", data);


        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            FirebaseResponse firebaseResponse = pushNotification.get();
            if (firebaseResponse.getSuccess() == 1) {
                log.info("push notification sent ok!");
            } else {
                log.error("error sending push notifications: " + firebaseResponse.toString());
            }
            return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("the push notification cannot be send.", HttpStatus.BAD_REQUEST);
    }
}
