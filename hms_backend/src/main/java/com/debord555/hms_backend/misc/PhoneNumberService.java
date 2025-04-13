package com.debord555.hms_backend.misc;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberService {

    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public boolean isValidPhoneNumber(String phoneNumberE164Format) {
        try {
            Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumberE164Format, null);
            return phoneUtil.isValidNumber(phoneNumberProto);
        } catch (Exception e) {
            return false; // Or throw a custom exception
        }
    }
}