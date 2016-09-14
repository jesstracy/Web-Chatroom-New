package tiy.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by jessicatracy on 9/14/16.
 */
@Controller
public class ChatSpringAppController {
//        Client myClient = new Client();

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public String input(String message) {
        if (message != null) {
//        myClient.sendMessage(message); <-- returns string, entire history, newest message on top
        }

        return "input";
    }



}
