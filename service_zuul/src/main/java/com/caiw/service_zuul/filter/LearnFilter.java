package com.caiw.service_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName LearnFilter
 * @Author caiwe
 * @CreateTime 2018/9/7  17:01
 **/
@Component
public class LearnFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(LearnFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Enumeration<String> parameterNames = request.getParameterNames();
        System.out.println("request.getAuthType() = " + request.getAuthType());
        Enumeration<String> headerNames = request.getHeaderNames();

        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            log.info(s +"\t>>>\t" +request.getParameter(s));
        }
        log.info("---------------------------------------------------------------------");
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            log.info(s +"\t>>>\t" +request.getParameter(s));
        }
        Object accessToken = request.getParameter("token");
        /*if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }*/
        log.info("ok");
        return null;
    }
}
