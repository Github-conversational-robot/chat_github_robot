package com.robot.security.rest;
import com.robot.security.service.dto.UserChatMesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Api(tags = "用户问答信息")
public class UserChatController {
    @ApiOperation("对用户问题进行回答")
    @PostMapping("/chat")
    public ResponseEntity<Object> chatWithRobot(@RequestBody UserChatMesDto userChatMes) {
        System.out.println(userChatMes.getFilePath());
        System.out.println(userChatMes.getInputMes());
        return ResponseEntity.status(HttpStatus.CREATED).body("hello boy");
    }
}
