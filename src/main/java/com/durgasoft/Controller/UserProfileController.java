package com.durgasoft.Controller;

import com.durgasoft.Entity.Signup;
import com.durgasoft.Service.UserProfileService;
import com.durgasoft.payload.UserProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vi/api/user")
public class UserProfileController {


    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService){
        this.userProfileService=userProfileService;
    }


    @GetMapping("/profile")

    public ResponseEntity<UserProfileDto> userProfile(@AuthenticationPrincipal Signup signup){
        return userProfileService.userProfile(signup);
    }
}
