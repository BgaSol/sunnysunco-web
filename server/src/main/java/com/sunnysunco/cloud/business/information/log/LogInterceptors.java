package com.sunnysunco.cloud.business.information.log;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class LogInterceptors implements HandlerInterceptor {
    private final LogService logService;

    /**
     * 获取客户端ip地址
     */
    public static String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        // 跨域请求，OPTIONS请求，直接返回
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        LogEntity logEntity = new LogEntity();
        logEntity.setUrl(request.getRequestURI());
        logEntity.setMethod(request.getMethod());
        logEntity.setReferer(request.getHeader("Referer"));
        logEntity.setIp(getClientIP(request));

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        logEntity.setBrowser(userAgent.getBrowser().getName());
        logEntity.setOs(userAgent.getOperatingSystem().getName());

        // 获取客户端信息
        try {
            String loginIdAsString = StpUtil.getLoginIdAsString();
            logEntity.setUserId(loginIdAsString);
        } catch (NotLoginException ignored) {
        }
        logService.save(logEntity);
        // 返回true，继续执行 如果返回false则中断
        return true;
    }
}