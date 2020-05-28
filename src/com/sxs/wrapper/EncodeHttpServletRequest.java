package com.sxs.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

public class EncodeHttpServletRequest extends HttpServletRequestWrapper {
    public EncodeHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String result = null;
        try {

            result = this.getRequest().getParameter(name);
            if (null != result) {
                result = new String(result.getBytes("utf-8"), "utf-8");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
