package com.example.test.sub1.Controller;

import com.example.test.sub1.Dto.StndWord;
import com.example.test.sub1.Service.StndWordService;
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
import com.example.test.sub1.Common.ApiResponseItem;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(schema = @Schema(implementation = StndWord.class))),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
})
public class StndWordController {

    private final StndWordService stndWordService;

    @Operation(summary = "표준단어조회", description = "전체 표준단어가 조회됩어니다.", tags = {"StndWord Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/getStndWordList")
    @ResponseBody
    public ApiResponseItem<List<StndWord>> getStndWordList(@RequestBody StndWord dto) {
        List<StndWord> list = stndWordService.getStndWordList(dto);

        return ApiResponseItem.createSuccess("200",list);
    }


    @Operation(summary = "표준단어등록", description = "표준단어이 등록.", tags = {"StndWord Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/addStndWord")
    @ResponseBody
    public ApiResponseItem addStndWord(@RequestBody StndWord dto) {
        List<StndWord> checkRes = stndWordService.getStndWordByEngNm(dto.getWordEngNm());

        if(checkRes.size()>0){
            log.error("이미 등록된 정보 존재");
            return ApiResponseItem.createError("500","이미 등록된 정보 존재");
        }
        StndWord result = stndWordService.addStndWord(dto);

        return result !=null ?  ApiResponseItem.createSuccessWithNoContent() : ApiResponseItem.createError("500","등록 에러 발생");
    }

    @Operation(summary = "표준단어삭제", description = "표준단어삭제.", tags = {"StndWord Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/delStndWord")
    @ResponseBody
    public int delStndWord(@RequestBody List<StndWord> list) {
        stndWordService.delStndWord(list);
        return 1;
    }

    @Operation(summary = "표준단어수정", description = "표준단어수정.", tags = {"StndWord Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/uptStndWord")
    @ResponseBody
    public int uptStndWord(@RequestBody StndWord dto) {
        stndWordService.uptStndWord(dto);
        return 1;
    }

    @Operation(summary = "표준단어1건 조회", description = "1건의 표준단어가 조회됩니다.", tags = {"StndWord Controller"}
            , parameters = {}
    )
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")   //권한 체크
    @PostMapping("/v1/getStndWord")
    @ResponseBody
    public StndWord getStndWord(@RequestBody StndWord dto) {
        return stndWordService.getStndWordById(dto);
    }

}
