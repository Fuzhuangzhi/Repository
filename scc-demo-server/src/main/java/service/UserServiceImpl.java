package service;

import bean.SCCUser;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import util.RedisStringUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 16:29
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private SqlSessionTemplate sst;

    @Autowired
    private RedisStringUtils redisStringUtils;

    @Autowired
    Gson gson;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public SCCUser login(String username, String password) {
        //1.数据库查询
        Map<String,String> res = sst.selectOne("userMapper.queryUserByUserName",
                ImmutableMap.of("UserName", username,
                        "Password", password));
        if (CollectionUtils.isEmpty(res)) {
            return null;
        }
        SCCUser user = convertDB2Bean(res);
        //2.是否查到，如果查到。。。
        //更新数据库信息
        updateDbUser(user);
        //更新数据库缓存
        updateCacheUser(user);
        return user;
    }

    @Override
    public boolean userExistInCache(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        String value = redisStringUtils.getValue(RedisStringUtils.USER_CACHE_PREFIX + token);
        if (!StringUtils.isEmpty(value)) {
            //重新激活token
            redisStringUtils.setKey(RedisStringUtils.USER_CACHE_PREFIX + token,
                    value,
                    EXPIRE_SECOND);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 更新缓存信息
     * @param user
     */
    private final int EXPIRE_SECOND = 60*60*24;
    private void updateCacheUser(SCCUser user) {
        user.setUniqueld(Long.valueOf(UUID.randomUUID().toString()));
        //存入redis
        redisStringUtils.setKey(RedisStringUtils.USER_CACHE_PREFIX + user.getUniqueld(),
                gson.toJson(user),EXPIRE_SECOND);
    }

    /**
     * 跟新上次登录时间
     * @param user
     */
    private void updateDbUser(SCCUser user) {
        //更新javaBean的最新登录时间
        user.setLastLoginDateTime(DateFormatUtils.format(new Date(), "yyyyMMdd HH:mm:ss"));

        //保存到数据库
        String[] dateTime = user.getLastLoginDateTime().split(" ");

        sst.update("userMapper.updateUserLoginTime",ImmutableMap.of("UserName",user.getUserName(),
                "LastLoginDate",dateTime[0],
                "LastLoginTime",dateTime[1]
                ));
    }

    private SCCUser convertDB2Bean(Map<String, String> res) {
        return new SCCUser(res.get("username"),res.get("lastlogindate"),res.get("lastlogintime"));
    }
}
