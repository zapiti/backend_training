package br.com.team.appx.convinience.controllers.test;

import br.com.team.appx.convinience.controllers.NotificationController;
import br.com.team.appx.convinience.model.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestNotificationController {
    @RequestMapping(path = "/testnofication", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> testNotification() throws IOException {
        Notification notification = new Notification();

        notification.title = "Seu produto acabou";

        notification.body = "O produto XPTO acabou no estoque";


        return new NotificationController().send(notification);
    }
}
