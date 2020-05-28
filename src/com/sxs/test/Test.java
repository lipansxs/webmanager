package com.sxs.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("/([^/]*?)$").matcher("/usermanage");
        m.reset("/usermanage/sel");
        if (m.find()) {
            System.out.println(m.group(1));
        }
    }
}
