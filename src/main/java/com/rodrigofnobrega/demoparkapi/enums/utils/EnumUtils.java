package com.rodrigofnobrega.demoparkapi.enums.utils;

import com.rodrigofnobrega.demoparkapi.enums.HttpMessagesEnum;

public class EnumUtils {

    public static String enumToString(HttpMessagesEnum httpMessagesEnum) {
        char firstLetter = httpMessagesEnum.name().charAt(0);


        return firstLetter +  httpMessagesEnum.name().toLowerCase().replace("_",  " ").substring(1);
    }
}
