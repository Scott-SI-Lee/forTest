package com.example.test.sub1.Controller;


import com.example.test.sub1.Dto.LoginUser;
import com.example.test.sub1.Repository.UserRepository;
import com.example.test.sub1.Service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequiredArgsConstructor
//@PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
public class IndexController {

    private final LoginService loginService;

    //@PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @RequestMapping("/")
    public String Hello(){

        return"index";
    }

    @RequestMapping("/loginForm")
    public String loginForm(){
        log.info("hi");
        return"index";
    }


    @RequestMapping("/loginProceed")
    @ResponseBody
    public LoginUser goLogin(@RequestBody LoginUser dto){
        log.info("로그인시도"+dto.toString());
        return loginService.logInProceed(dto);
    }
}
