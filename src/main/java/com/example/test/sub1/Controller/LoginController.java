package com.example.test.sub1.Controller;

import com.example.test.sub1.Dto.LoginUser;
import com.example.test.sub1.Repository.UserRepository;
import com.example.test.sub1.Service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = LoginUser.class))),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
})
public class LoginController {


     private final UserRepository userRepository;







    @Operation(summary = "회원정보 조회", description = "회원정보가 조회됩니다.", tags = { "Login Controller" }
    ,parameters = {}
    )
    @PostMapping("/v1/getUser")
    @ResponseBody
    public LoginUser getUser(@RequestBody LoginUser dto) {
        return userRepository.getReferenceById(dto.getUserId());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/getUserList")
    @ResponseBody
    public List<LoginUser> getUserList(@RequestBody LoginUser dto) {
        return userRepository.findAll();
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/regUser")
    @ResponseBody
    public LoginUser regUser(@RequestBody LoginUser dto) {
        return userRepository.save(dto);
    }

}
