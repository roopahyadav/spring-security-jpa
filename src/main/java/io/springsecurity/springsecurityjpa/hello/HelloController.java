package io.springsecurity.springsecurityjpa.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path = "/")
//    @RequestMapping("/")
    public String sayHi(){
        return "Welcome";
    }

   @GetMapping(path = "/user")
//    @RequestMapping("/user")
    public String user(){
        return "Welcome User";
    }

    @GetMapping(path = "/admin")
//    @RequestMapping("/admin")
    public String admin(){
        return "Welcome Admin";
    }


}
