package cn.edu.zuel.demo4.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 非法访问拦截(白名单形式)
// 1.登录 注册 页面放行(指定页面放行)
// 2.放行静态资源(js、image、css等)
// 3.指定操作放行(登录、注册)
// 4.登录状态放行(判断session中的用户信息是否为空)
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 基于http请求
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //获取请求的页面
        String url = request.getRequestURI();
        // 指定页面放行 其他的回到index页面
        if (url.contains("login.jsp")||url.contains("index.jsp")||url.contains("register.jsp")
                ||url.contains("LoginServlet")||url.contains("RegisterServlet")){
            filterChain.doFilter(request,response);
            return;
        }
        //放行静态资源
        if (url.contains("/js")||url.contains("/images")||url.contains("/css")){
            filterChain.doFilter(request,response);
            return;
        }
        //登录状态放行(判断session中的用户信息是否为空)
        //判断session是否空
        if (request.getSession().getAttribute("logintype")!=null){
            filterChain.doFilter(request,response);
            return;
        }
        //filterChain.doFilter(servletRequest,servletResponse);
        response.sendRedirect("index.jsp");
    }

    @Override
    public void destroy() {

    }
}
