package br.com.team.appx.convinience.controllers.test;

import br.com.team.appx.convinience.controllers.NotificationController;
import br.com.team.appx.convinience.model.FCNotification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestNotificationController {
    @RequestMapping(path = "/testnofication", method = RequestMethod.GET)
    public @ResponseBody
    HttpStatus testNotification() throws IOException {
        FCNotification notification = new FCNotification();

        notification.title = "Seu produto acabou";

        notification.body = "O produto XPTO acabou no estoque";


        return new NotificationController().send(notification);
    }
}
