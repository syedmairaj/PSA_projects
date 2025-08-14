package com.durgasoft.Controller;

import com.durgasoft.Entity.JwtToken;
import com.durgasoft.Entity.OtpData;
import com.durgasoft.Entity.Signup;
import com.durgasoft.Exception.InvalidCredentialsException;
import com.durgasoft.Service.AuthService;
//import com.durgasoft.Service.JWTService;
import com.durgasoft.Service.JWTService;
import com.durgasoft.Service.OTPService;
import com.durgasoft.payload.OTPDto;
import com.durgasoft.payload.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/app")
public class AuthController {


    private AuthService authService;
    private OTPService otpService;
@Autowired
    JWTService jwtService;
    //private JWTService jwtService;
    @Autowired
    public AuthController(AuthService authService, OTPService otpService) {
        this.authService = authService;
        this.otpService = otpService;
        //  this.jwtService = jwtService;
    }

    //Role USer signup
    @PostMapping("/signup")

    public ResponseEntity<?> signUp(@RequestBody SignUpDto signdto) {
        return new ResponseEntity<>(authService.signup(signdto), HttpStatus.OK);
    }
//
//@DeleteMapping("{/id}")
//public ResponseEntity<String> delRecord(@PathVariable Long id){
//    return authService.delRecord(id);
//
//}

    //new Login and Generate OTP

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody SignUpDto signUpDto) {

        OTPDto  otp = authService.login(signUpDto);

        return new ResponseEntity<>(otp, HttpStatus.OK);
    }


//validate Otp
        @PostMapping("/verify-otp")
        public ResponseEntity<?> verifyOTP (@RequestParam String UserOtp, String username){
            String verify = otpService.verifyOtp(UserOtp,username);
            System.out.println("otp verified" +UserOtp);
            JwtToken jwtToken = new JwtToken();
//            if (!verify) {
//                throw new IllegalArgumentException("invalid otp");
//            }

            jwtToken.setType("JWT");
            jwtToken.setToken(verify);

            return new ResponseEntity<>(jwtToken, HttpStatus.ACCEPTED);
        }


//    @GetMapping("/login")
//    public ResponseEntity<?> login(@RequestBody SignUpDto signDto) {
//    String token = authService.login(signDto);
//    if(token!=null){
//        return new ResponseEntity<>(token,HttpStatus.OK);
//    }
//     return new ResponseEntity<>("Invalid",HttpStatus.INTERNAL_SERVER_ERROR);
//    //return new ResponseEntity<>(authService.login(signDto), HttpStatus.CREATED);
//
//        }




}