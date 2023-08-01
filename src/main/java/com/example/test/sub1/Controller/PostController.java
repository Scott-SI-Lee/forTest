package com.example.test.sub1.Controller;

import com.example.test.sub1.Dto.LoginUser;
import com.example.test.sub1.Dto.Post;
import com.example.test.sub1.Repository.PostRepository;
import com.example.test.sub1.Repository.UserRepository;
import com.example.test.sub1.Service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = Post.class))),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
})
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시물 조회", description = "전체 게시물이 조회됩니다.", tags = {"Post Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/getPostList")
    @ResponseBody
    public List<Post> getPostList(@RequestBody Post dto) {
        return postService.getPostList(dto);
    }


    @Operation(summary = "게시물 등록", description = "게시물이 등록.", tags = {"Post Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/addPost")
    @ResponseBody
    public Post addPost(@RequestBody Post dto) {
        return postService.addPost(dto);
    }

    @Operation(summary = "게시물 삭제", description = "게시물 삭제.", tags = {"Post Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/delPost")
    @ResponseBody
    public int delPost(@RequestBody Post dto) {
        postService.delPost(dto);
        return 1;
    }

    @Operation(summary = "게시물 수정", description = "게시물 수정.", tags = {"Post Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/uptPost")
    @ResponseBody
    public int uptPost(@RequestBody Post dto) {
        postService.uptPost(dto);
        return 1;
    }

    @Operation(summary = "게시물 1건 조회", description = "1건의 게시물이 조회됩니다.", tags = {"Post Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/getPost")
    @ResponseBody
    public Post getPost(@RequestBody Post dto) {
        return postService.getPost(dto);
    }
}
