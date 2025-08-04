package com.durgasoft.Service;

import com.durgasoft.Entity.Signup;
import com.durgasoft.Repository.SignupRepository;
import com.durgasoft.payload.UserProfileDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    private SignupRepository signupRepository;

    public UserProfileService(SignupRepository signupRepository){
        this.signupRepository=signupRepository;
    }
    public ResponseEntity<UserProfileDto> userProfile(Signup signup) {

       UserProfileDto dto = new UserProfileDto();

       dto.setUsername(signup.getUsername());
       dto.setEmail(signup.getEmail());
       dto.setMobile(signup.getMobile());
       dto.setRole(signup.getRole());
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}
