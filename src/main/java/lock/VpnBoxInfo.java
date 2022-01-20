package lock;


import java.io.Serializable;

/**
*@author weiyifei
*@description vpn拓扑接口盒子
*@date 2022/1/4
*/
public class VpnBoxInfo implements Serializable {

    private String circuitNo;

    private String regionId;

    private String boxSerNum;

    private String boxName;

    private String deviceFactory;

    private String systemIp;

    private Double vpnBandWidth;

    public VpnBoxInfo(String circuitNo, String regionId, String boxSerNum, String boxName, String deviceFactory, String systemIp, Double vpnBandWidth) {
        this.circuitNo = circuitNo;
        this.regionId = regionId;
        this.boxSerNum = boxSerNum;
        this.boxName = boxName;
        this.deviceFactory = deviceFactory;
        this.systemIp = systemIp;
        this.vpnBandWidth = vpnBandWidth;
    }

    public VpnBoxInfo() {
    }

    public String getCircuitNo() {
        return circuitNo;
    }

    public void setCircuitNo(String circuitNo) {
        this.circuitNo = circuitNo;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getBoxSerNum() {
        return boxSerNum;
    }

    public void setBoxSerNum(String boxSerNum) {
        this.boxSerNum = boxSerNum;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getDeviceFactory() {
        return deviceFactory;
    }

    public void setDeviceFactory(String deviceFactory) {
        this.deviceFactory = deviceFactory;
    }

    public String getSystemIp() {
        return systemIp;
    }

    public void setSystemIp(String systemIp) {
        this.systemIp = systemIp;
    }

    public Double getVpnBandWidth() {
        return vpnBandWidth;
    }

    public void setVpnBandWidth(Double vpnBandWidth) {
        this.vpnBandWidth = vpnBandWidth;
    }

    @Override
    public String toString() {
        return "VpnBoxInfo{" +
                "circuitNo='" + circuitNo + '\'' +
                ", regionId='" + regionId + '\'' +
                ", boxSerNum='" + boxSerNum + '\'' +
                ", boxName='" + boxName + '\'' +
                ", deviceFactory='" + deviceFactory + '\'' +
                ", systemIp='" + systemIp + '\'' +
                ", vpnBandWidth=" + vpnBandWidth +
                '}';
    }
}
