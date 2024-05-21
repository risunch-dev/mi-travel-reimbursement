package com.xiaomi.info.controller;

import com.xiaomi.info.response.Response;
import com.xiaomi.info.service.UserService;
import com.xiaomi.info.user.request.UserDetailRequest;
import com.xiaomi.info.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.xiaomi.info.controller
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/17 20:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public Response<Boolean> getAll() {
        return Response.success();
    }

    @PostMapping("/detail")
    public Response<UserResponse> userDetail(@RequestBody UserDetailRequest request) {
        return Response.success(userService.userDetail(request.getUserId()));
    }



}
