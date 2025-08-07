package com.durgasoft.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    @Value("${twilio.fromPhone}")
    private String fromPhone;

    public String sendSms(String to, String message) {
        Message sms = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromPhone),
                message
        ).create();
        return sms.getSid(); // optional: return status or sid
    }

}
