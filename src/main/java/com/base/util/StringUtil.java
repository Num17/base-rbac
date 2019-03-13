package com.base.util;

public class StringUtil {

    public static final String EMPTY = "";
    public static final String SPACE = " ";

    public static String removeHeader(String str, String header) {
        if (isEmpty(str) || isEmpty(header)) {
            return EMPTY;
        }

        if (!str.startsWith(header)) {
            return str;
        }

        return str.substring(header.length());
    }

    /**
     * 去除字符串内所有空格
     */
    public static String trimAll(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }

        return str.replaceAll(SPACE, EMPTY);

    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (null == str || str.length() == 0) {
            return true;
        }

        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (' ' != c) {
                return false;
            }

        }

        return true;

    }

}
