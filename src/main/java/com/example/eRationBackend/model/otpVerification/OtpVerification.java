package com.example.eRationBackend.model.otpVerification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class OtpVerification {
    String otp;

    public OtpVerification(String nextInt) {
        this.otp=nextInt;
    }
}
