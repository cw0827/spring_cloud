//package com.oauth.oauthservice.config;
//
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//
//import org.springframework.core.annotation.Order;
//
//import java.io.IOException;
//
//
///**
// * @author caiwei
// * @version V1.0
// * @ClassName: test
// * @Function:
// * @Date: 2019/11/15 15:38
// */
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
//public class FateCorsFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT, OPTIONS");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        System.out.println("111111111111111111111111------------------------------------------------------");
//        //下面这段是关键，不加的话，返回时Ajax会提示ResponseStatus异常
//        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
//            response.setStatus(HttpServletResponse.SC_OK);
//        }else{
//            chain.doFilter(req, res);
//        }
//    }
//
//    @Override
//    public void destroy() {}
//}
