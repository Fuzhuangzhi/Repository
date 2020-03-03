package filter;

import bean.SCCUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 15:59
 */
public class LoginFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private UserService userService;

    public LoginFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("注册登录过滤器");
    }

    //不需要身份验证的URI(TODO:从配置文件读取)
    private static String[] EXINCLUDE_CHECK_URI = {"/queryparaminfo"};

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        //获取请求的URI
        String path = httpRequest.getRequestURI();
        logger.info("path={}",path);
        if (path.indexOf("/login") < 0) {
            boolean isExclude = false;
            for (String excludeURI : EXINCLUDE_CHECK_URI) {
                if (path.indexOf(excludeURI) >= 0) {
                    isExclude = true;
                    break;
                }
            }
            if (isExclude) {
                //不需要身份验证，直接放行
                filterChain.doFilter(request, response);
            }else if (userService.userExistInCache(request.getParameter("token"))){
                //需要验证
                //验证通过，直接放行
                filterChain.doFilter(request, response);
            }else {
                //验证不通过，跳转到登录页面
                httpRequest.getRequestDispatcher("/loginexpire").forward(request, response);
            }
        }else{
            //登录请求
            SCCUser sccUser = userService.login(request.getParameter("username"),
                    request.getParameter("password"));
            //2.未成功登录，重定向到登录页
            if (sccUser == null) {
                httpRequest.getRequestDispatcher("/loginfail").forward(request, response);
            }else {
                //正确登录
                httpRequest.getRequestDispatcher("/loginsuccess").forward(request, response);
            }

        }
    }

    @Override
    public void destroy() {
        logger.info("注销登录过滤器");
    }
}
