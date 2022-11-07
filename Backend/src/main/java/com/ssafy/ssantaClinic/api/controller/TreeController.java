package com.ssafy.ssantaClinic.api.controller;

import com.ssafy.ssantaClinic.api.response.CalendarResponse;
import com.ssafy.ssantaClinic.api.service.S3Service;
import com.ssafy.ssantaClinic.common.auth.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @FileName : TreeController
 * @Class 설명 : 트리 관련 요청을 처리하는 Controller
 */
@Api(value = "트리 관련 API", tags = {"TreeController"})
@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/tree")
@RequiredArgsConstructor
public class TreeController {
    private final S3Service s3Service;

    @ApiOperation(value = "트리 3D 파일 받아오기", notes = "클라이언트로부터 3D을 받아온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PostMapping(
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<?> getGlbFile(@RequestPart(required = false) MultipartFile glbfile) throws IOException {
        /**
         * @Method Name : getGlbFile
         * @Method 설명 : 3D 트리 파일 보내기
         */
        // S3 업로드
        String url = "";
        if(url != null){
            url = s3Service.uploadGlb(glbfile);
        }
        return ResponseEntity.ok().build();
    }
}