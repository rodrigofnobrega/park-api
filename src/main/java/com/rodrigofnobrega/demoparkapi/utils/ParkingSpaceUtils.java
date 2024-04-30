package com.rodrigofnobrega.demoparkapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingSpaceUtils {
    public static String genereateReceipt() {
        LocalDateTime date = LocalDateTime.now();
        String receipt = date.toString().substring(0,19);
        return receipt.replace("-", "")
                .replace(":", "")
                .replace("T", "-");
    }
}
