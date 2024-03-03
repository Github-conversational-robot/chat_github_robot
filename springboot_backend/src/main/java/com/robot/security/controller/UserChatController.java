package com.robot.security.controller;
import com.robot.security.service.ChatService;
import com.robot.security.service.dto.UserChatMesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Api(tags = "用户问答信息")
public class UserChatController {
    @Autowired
    private final ChatService chatService;
    @ApiOperation("对用户问题进行回答")
    @PostMapping("/chat")
    public ResponseEntity<Object> chatWithRobot(@RequestBody UserChatMesDto userChatMes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                chatService.chatOneTime(userChatMes.getInputMes(), userChatMes.getFilePath()));
    }
}
