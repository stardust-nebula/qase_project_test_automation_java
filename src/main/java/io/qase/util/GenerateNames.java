package io.qase.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateNames {

    public static String generateTestProjectNameWithCurrentDate(int prefixProjectNameLength) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StringConstant.DATE_FORMAT_PATTERN_FOR_NAMES);
        Date date = new Date(System.currentTimeMillis());
        String dateForName = simpleDateFormat.format(date);
        String prefixProjectName = generateRandomString(prefixProjectNameLength);
        return prefixProjectName + " current date" + dateForName;
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    private GenerateNames() {
    }
}
