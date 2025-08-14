package com.durgasoft.Service;

import com.durgasoft.Entity.OtpData;
import com.durgasoft.Entity.Signup;
import com.durgasoft.Repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OTPService {
    @Autowired
    private JWTService jwtService;


    private SignupRepository signupRepository;

    public OTPService(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    private final Map<String, OtpData> otpStore = new ConcurrentHashMap<>();

    public String generateOtp(String mobile) {
        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(999999));
        long expiry = System.currentTimeMillis() + (5 * 60 * 1000);
        Optional<Signup> mobileData = signupRepository.findByMobile(mobile);
        if (mobileData.isEmpty()) {
            throw new IllegalArgumentException("Cannot find mobile number");
        }
        Signup fetchMobileData = mobileData.get();
        OtpData otpData = new OtpData(mobileData.get().getMobile(), otp, expiry);
        otpStore.put(fetchMobileData.getMobile(), otpData);
        return otp;


    }


    public String verifyOtp(String UserOtp, String username) {
        if (UserOtp.isEmpty()) {
            throw new IllegalArgumentException("enter otp");
        }
        OtpData sysOtp = otpStore.get(username);
        System.out.println(sysOtp);
        if (sysOtp == null) {
            throw new IllegalArgumentException("no otp found for this user");
        }

        if (!UserOtp.equals(sysOtp.getOtp())) {
            throw new IllegalArgumentException("inavlid otp");
        }
        otpStore.remove(username);
        return "token is verified";

    }
}