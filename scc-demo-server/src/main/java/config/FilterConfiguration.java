package config;

import filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.UserService;

/**
 * @author Administrator
 * @Date 2020/3/2 0002 11:32
 * 过滤器容器
 */
@Configuration
public class FilterConfiguration {

    @Autowired
    UserService userService;

    @Bean
    public FilterRegistrationBean loginFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(
                new LoginFilter(userService)
        );
        //所有的请求都必须经过过滤器
        registrationBean.addUrlPatterns("/*");
        //设置过滤器的名字
        registrationBean.setName("loginFilter");
        //设置过滤器的启动顺序 数字越小，过滤器越靠前
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
