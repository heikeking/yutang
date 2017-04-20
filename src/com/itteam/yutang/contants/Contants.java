package com.itteam.yutang.contants;

public class Contants {
	/**
	 * 登陆界面
	 */
	public static final int LOGINSUCCEED = 0;
	public static final int REGISTERSUCCEED = 1;
	public static final int LOGINFAIL = 2;
	public static final int REGISTERFAIL = 3;
	
	public static  final int SUCCESS=1;
	public static  final int FAIL=0;
	/**
	 * 域名地址
	 */
	public static final String baseUrl = "http://fish.zjit.org/fishPond/";
	
	/*登陆地址*/
	public static final String loginUrl =baseUrl +"user/userLogin.php";
	//设备信息
	public static final String equipmentBrief =baseUrl +"equipment/equipmentBrief.php";
	//塘口信息
	public static final String pondList =baseUrl +"pond/pondList.php";
	//写入控制器各种控制单元状态
	public static final String changeUnitType =baseUrl +"command/changeUnitType.php";
	//写入控制器阀值
	public static final String writeCommandThreshold =baseUrl +"command/writeCommandThreshold.php";
	
	public static final String calibrationEquipment =baseUrl +"command/calibrationEquipment.php";
	
	public static final String calibrationTime =baseUrl +"command/calibrationTime.php";
	
	public static final String pondConfig =baseUrl +"pond/pondConfig.php";
	
	//添加塘口
	public static final String pondAdd =baseUrl +"pond/pondAdd.php";
	
	public static final String pondSetUpList =baseUrl +"pond/pondSetUpList.php";
	
	public static final String pondInfo =baseUrl +"pond/pondInfo.php";
	
	public static final String readControllerConfig =baseUrl +"command/readControllerConfig.php";
	
	public static final String equipmentAdd =baseUrl +"equipment/equipmentAdd.php";
	
	public static final String userUpdatePassword =baseUrl +"user/userUpdatePassword.php";
	
	public static final String userUpdateInfo =baseUrl +"user/userUpdateInfo.php";
	
	public static final String pondUpdate =baseUrl +"pond/pondUpdate.php";
	
	public static final String equipmentWarningList =baseUrl +"equipment/equipmentWarningList.php";
	
	public static final String userRegister =baseUrl +"user/userRegister.php";
	public static final String equipmentErrorList =baseUrl +"equipment/equipmentErrorList.php";
	public static final String equipmentErrorConfirm =baseUrl +"equipment/equipmentErrorConfirm.php";
	
	public static final String pondDelete =baseUrl +"pond/pondDelete.php";
	public static final String equipmentHitch =baseUrl +"equipment/equipmentHitch.php";
}
