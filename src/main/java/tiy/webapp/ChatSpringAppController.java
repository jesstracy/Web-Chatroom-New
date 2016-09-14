package tiy.webapp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by jessicatracy on 9/14/16.
 */
public class ChatSpringAppController {
    //    Client myClient = new Client();


//    @RequestMapping(path = "/chat", method = RequestMethod.GET)
//    public String chat() {
////        myClient.sendMessage(message);
//
//        return "input";
//    }


    @RequestMapping(path = "/chatNew", method = RequestMethod.GET)
    public String inputNew(Model model, HttpSession session, String message) {
        if (message != null) {
            //When I'm just sysouting the server response, I don't need to set the message on the session - > just need it to send to server and can go away each time!
            //Also don't need to add to model because the view does not need to see it at all.

//            myWebChatClient = new WebChatClient();
//            String serverResponse = myWebChatClient.sendMessage(message);
//            System.out.println(serverResponse);

            //BUT, I will have to add it to the model when I'm printing it to the browser screen!
//            model.addAttribute("serverResponse", serverResponse);

        }

        return "input";
    }
}
