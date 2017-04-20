package com.itteam.yutang.bean;

import java.io.Serializable;

public class EquipmentWarningBean implements Serializable{
	private static final long serialVersionUID = 1L;
	public String pondId;// - 鱼塘Id
	public String alarmId;// - 错误Id
	public String pondName;// "高桥村1号塘",
	public String deviceId;// - 设备Id
	public String group_id;// - 分组Id
	public String type;// - 警告类型
	public String msg;// - 警告内容
	public String createTime;// - 警告生成时间
	public String ack_time;// - 确认时间(朱佳杰这时间默认为null啊)
}
