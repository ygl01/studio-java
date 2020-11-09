package com.kuang.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author ygl
 * @description 自己的地区解析器
 * @date 2020/10/27 17:12
 */
public class MyLocaleResolver implements LocaleResolver {
    /**
     * @param
     * @return j解析请求
     * @author ygl
     * @date 2020-10-27 17:13
     * @since
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取请求中的参数链接
        String language = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault(); //如果没有就使用默认的
        //如果请求的链接携带了地区参数
        if (!StringUtils.isEmpty(language)) {
            //zh_CN
            String[] split = language.split("_");
            //国家  地区
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
