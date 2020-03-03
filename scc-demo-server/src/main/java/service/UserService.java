package service;

import bean.SCCUser;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 16:06
 * 用户业务逻辑接口
 */
public interface UserService {
    SCCUser login(String username, String password);

    /**
     * 判断token是否在缓存中存在
     * @param token
     * @return
     */
    boolean userExistInCache(String token);

}
