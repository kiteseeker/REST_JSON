package by.kulikovski.rest.rest_controller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {

    // app code for the "/hello" endpoint

    @GetMapping("/hello")
    public String sayHello() {
        return "Greetings, dear friend";
    }
}
