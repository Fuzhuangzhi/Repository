package bean;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 16:08
 * 用户信息类
 */
public class SCCUser {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 最后一次登录的时间
     */
    private String lastLoginDateTime;

    /**
     * 用户的唯一标识
     */
    private transient long uniqueld;

    public SCCUser(String userName,String lastLoginDate, String lastLoginDateTime) {
        this.userName = userName;
        if (StringUtils.isAnyBlank(lastLoginDate,lastLoginDateTime)) {
            this.lastLoginDateTime = null;
        }else{
            this.lastLoginDateTime = lastLoginDate + " " + lastLoginDateTime;
        }
    }

    public SCCUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(String lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public long getUniqueld() {
        return uniqueld;
    }

    public void setUniqueld(long uniqueld) {
        this.uniqueld = uniqueld;
    }
}
