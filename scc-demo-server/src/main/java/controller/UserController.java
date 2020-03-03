package controller;

import bean.ResquestResult;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @Date 2020/3/2 0002 11:44
 */
@RestController
public class UserController {

    /**
     * 登录失败
     */
    private static final String LOGIN_STATUS_FAIL = "0";

    /**
     * 登录成功
     */
    private static final String LOGIN_STATUS_SUCCESS = "1";

    /**
     * 缓存过期
     */
    private static final String LOGIN_STATUS_EXPIRE = "2";

    //1.loginfail 登录失败
    @PostMapping("/loginfail")
    public ResquestResult loginFail(){
        return new ResquestResult(LOGIN_STATUS_FAIL,"用户名/密码错误",null);
    }

    //2.loginsuccess 登录成功
    @PostMapping("/loginsuccess/{token}")
    public ResquestResult loginSuccess(@PathVariable String token){
        return new ResquestResult(LOGIN_STATUS_SUCCESS,"登录成功",
                ImmutableMap.of("token", token));
    }

    //3.loginexpire 过期重新登录
    @PostMapping("/loginexpire/{token}")
    public ResquestResult loginExpire(@PathVariable String token){
        return new ResquestResult(LOGIN_STATUS_EXPIRE,"重新登录",
                null);
    }
}
