package com.ztsc.house.bean.login;

/**
 * Created by xuyang on 2017/3/13.
 * 令牌登录请求bean
 */

public class TokenLoginRequestBody {
//    令牌
    private String token;
//    设备唯一码
    private String machineId;
//    设备名称
    private String machineName;
//    平台类型
    private int clientType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }
}
