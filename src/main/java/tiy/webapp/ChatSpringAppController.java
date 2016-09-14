package tiy.webapp;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by jessicatracy on 9/14/16.
 */
@Controller
public class ChatSpringAppController {
    Client myClient = new Client();
    String username = "";

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session, String username) {
        return "login";
    }

    @RequestMapping(path = "/get_username", method = RequestMethod.POST)
    public String get_username(HttpSession session, String username) {
//        this.username = username;
        session.setAttribute("username", username);
//        System.out.println(this.username);
        return "redirect:/chat";
    }


    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public String input(HttpSession session, Model model, String message) {
        if (message != null) {
        String serverResponse = myClient.sendMessage((String)session.getAttribute("username"), message);
//            System.out.println("Username is: " + session.getAttribute("username"));
//            String serverResponse = "test 1\ntest 2\ntest 3\ntest4";
            String[] serverMessages = serverResponse.split("\n");
            model.addAttribute("serverMessages", serverMessages);
        }

        return "input";
    }



}
