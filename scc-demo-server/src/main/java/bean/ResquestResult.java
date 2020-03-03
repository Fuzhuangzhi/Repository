package bean;

/**
 * @author Administrator
 * @Date 2020/3/1 0001 15:55
 * 返回数据格式
 */
public class ResquestResult {

    /**
     * 返回状态
     */
    protected String status;

    /**
     * 错误信息
     */
    protected String errorMsg;

    /**
     * 实际返回数据
     */
    protected Object data;

    public ResquestResult() {
    }

    public ResquestResult(String status, String errorMsg, Object data) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
