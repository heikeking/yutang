package com.itteam.yutang.bean;

public class DeviceListBean {
	public String deviceId;// - 设备Id

	public String group_id;// - 分组Id

	public String tmp;// - 温度

	public String ph;// - PH值

	public String o2;// - 溶氧浓度

	public String feedStatus;// - 抽水泵状况(00=ON,01=OFF,02=AUTO ON,03=AUTO OFF)

	public String o2Status;// - 抽水泵状况(00=ON,01=OFF,02=AUTO ON,03=AUTO OFF)

	public String recvTime;// - 最后更新时间

	public String status;// - 设备在线(1在线, 2离线)
	public String alarmCount;
	public String imei;
}
