package com.powerwin.entity;


import lombok.Data;

@Data
public class Ad {

	private String adid;//广告id
	private String cid;//广告主id
	private String type;//广告类型：1积分墙 2免费墙 3插屏 4全屏 5广告条 7视频
	private String adname;//广告名称
	private String data_from;//广告来源：1普通广告，2渠道广告 ,3mobimax广告
	private String data_type;//(暂未使用)数据类型，1积分墙 2免费墙 3插屏 4全屏 5广告条 7视频
	private String pkg;//包名
	private String icon;//icon
	private String url;//跳转目标地址
	private String desc;//广告描述
	private String psize;//包大小
	private String rate;//分成比率(100以内整型)
	private String state;//状态 1新广告,2审核通过,，3拒绝，4启动 5 停止 6软删除，7调试，8暂停
	private String remark;//广告投放说明
	private String create_time;//创建时间
	private String check_time;//审核时间
	private String admin_uid;//审核人id
	private String admin_name;//审核人姓名
	private String update_time;//更新时间
	private String price_callback_income;//接入单价callback
	private String price_callback_cost;//投放单价callback，值为price_income_callback的0.8
	private String price_click_income;//接入单价
	private String price_click_cost;//投放单价
	private String price_job_income;//接入单价
	private String price_job_cost;//(暂未使用)投放单价job，值为cost_callback的0.5
	private String ctype;//分类
	private String cstype;//子分类
	private String actype;//分类(后台)
	private String acstype;//子分类(后台)
	private String clevel;//评级
	private String options;//广告物料JSON信息(插屏:image和click_url)；(积分墙:icon,title,text1,text2,download,store,callbackurl,callbacks,ids,psize)
	private String devices;//(暂未使用)投放设备：iphone4,iphone5,iphone6,ipad,ipod
	private String sort;//(暂未使用)排位号
	private String os;//广告平台 1android 2ios
	private String osver;//(暂未使用)投放系统版本 ios5,ios6,ios7
	private String appstoreid;//暂未使用)appstoreid 苹果平台编号
	private String bundleid;//(暂未使用)bundleid 广告包名
	private String refuse_reason;//拒绝原因
	private String seller;//(暂未使用)销售id
	private String num;//(暂未使用)IP 数量
	private String Stringerval;//(暂未使用)时间间隔
	private String boot_time_num;//(暂未使用)相同时间启动数量
	private String num_ad;//(暂未使用)IP 数量（广告主）
	private String Stringerval_ad;//(暂未使用)时间间隔（广告主）
	private String boot_time_num_ad;//(暂未使用)相同时间启动数量（广告主）
	private String active_type;//(暂未使用)激活方式：1OPENUDID,2IDFA,3MAC
	private String keywords;//(暂未使用)关键词，逗号分隔
	private String is_aso;//(暂未使用)跳转方式：0正常，1aso自动搜索，2验证码，3手动搜索，4短链
	private String aso_pos;//(暂未使用)aso展示位置设置
	private String process_name;//进程名称
	private String jobs;//(暂未使用)激活任务
	private String is_hs_flag;//(暂未使用)欢试投放标记（1独家，2首发，3：100%返现）
	private String is_hs_report;//(暂未使用)运行列表是否加入报表（0：默认不计，1计入报表）
	private String is_monitor;//(暂未使用)AppStore监测 0否  1是
	private String is_hand_stop;//(暂未使用)是否手动停止：0 默认，1手动
	private String space_type;//(暂未使用)投放类型，积分墙1 免费墙2 插屏3 全屏4 广告条5（暂停使用 = 广告类型）
	private String is_talkingdata;//(暂未使用)是否上报给talkingdata，1上报，2不上报
	private String ads_alias;//(暂未使用)广告别名
	private String billing;//(暂未使用)计价方式(CLICK CALLBACK JOB)：1click；2callback；3job
	private String is_appsflyer;//是否上报给appsflyer，1上报，2不上报
	private String rpt_type;//上报类型，0不上报，1appsflyer，2adjust
	private String adid_come;//广告id（广告主自用）
	private String tracking_url;//跟踪url
	private String big_pic;
	private String payoutType;
    private String cappingType;// 限制类型;
    private Integer dailyPayoutCap; // 日预算限制，单位: 美元
    private Integer dailyConversionCap; // 日转化限制
    private Integer monthPayoutCap; // 月预算限制，单位: 美元
    private Integer monthConversionCap; // 月转化限制
    private Integer payoutCap; // 总预算限制，单位: 美元
    private Integer conversionCap; // 总转化限制
    private String currency;// payout金额单位;
	private Boolean is_disable;//是否禁用
	private String originalStatus;//广告原生状态：上游设置的广告状态

}
