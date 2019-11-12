package com.aoxing.it.filter;

import com.aoxing.it.util.CommonStatic;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

/**
 * @author hejq9
 * @date 2019-10-22
 */
@Component
@ConfigurationProperties(prefix = "filter")
@WebFilter(urlPatterns = "/**", filterName = "loginFilter")
@Data
public class LoginFilter implements Filter {

    /**
     * 白名单
     */
    private String[] whiteUrl;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String origin  = req.getHeader(HttpHeaders.ORIGIN);
        res.setHeader("Access-Control-Allow-Origin", origin);
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Access-Token, token, username");
        res.setHeader("Access-Control-Max-Age", "3600");

        //跨域会先发起OPTIONS请求，放行
        if ("options".equals(req.getMethod()) || "OPTIONS".equals(req.getMethod())) {
            chain.doFilter(request, res);
            return;
        }

        //白名单，放行
        String url = req.getRequestURI();
        if (Arrays.asList(whiteUrl).contains(url.substring(url.indexOf(contextPath) + contextPath.length()))) {
            chain.doFilter(request, res);
            return;
        }

        //验证username和token是否对应
        String username = req.getHeader("username");
        String token = req.getHeader("token");
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(token)) {
            String tokenRight = redisTemplate.boundValueOps(CommonStatic.TOKEN_PREFIX + username).get();
            if (token.equals(tokenRight)) {
                //token延长有效期
                redisTemplate.boundValueOps(CommonStatic.TOKEN_PREFIX + username).set(token, Duration.ofSeconds(CommonStatic.TOKEN_DURATION));
                chain.doFilter(request, res);
                return;
            }
        }

        res.setStatus(401);
    }
}
