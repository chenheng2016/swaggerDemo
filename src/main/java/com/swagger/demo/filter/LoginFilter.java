package com.swagger.demo.filter;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.swagger.demo.config.Swagger2Config;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenheng
 * @time 2020/4/3 23:10
 */
//@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getRequestURI().contains("webjars") || request.getRequestURI().contains("swagger")
                || request.getRequestURI().endsWith("/configuration/security")
                || request.getRequestURI().endsWith("v2/api-docs")
                || request.getRequestURI().endsWith("/configuration/ui")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = request.getHeader(Swagger2Config.TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(Swagger2Config.TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
                ServletOutputStream outputStream = response.getOutputStream();
                Map<String, Object> map = new HashMap<>();
                map.put("code", HttpStatus.UNAUTHORIZED.value());
                map.put("msg", "未登录");
                JsonMapper jsonMapper = new JsonMapper();
                outputStream.write(jsonMapper.writeValueAsBytes(map));
                outputStream.flush();
                outputStream.close();
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
