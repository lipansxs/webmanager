package com.sxs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUri {

    // 解析uri的操作
    private static Matcher uri_matcher = Pattern.compile("/([^/]*?)$").matcher("");

    /**
     * 获取操作接口字符串
     */
    public static String parseOption(String uri){

        String result = null;

        // 重新设置匹配序列
        uri_matcher.reset(uri);

        if (uri_matcher.find()) {
            result = uri_matcher.group(1);
        }

        return result;
    }

}
