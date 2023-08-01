package com.example.test.sub1.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {
    // error path를 꼭 "/error" 로 하자!
    private final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String redirectRoot(){
        log.info("에러 발생하여 리다이렉트");
        return "/index";
    }

    public String getErrorPath(){
        return null;
    }
}