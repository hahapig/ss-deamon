package com.taihe.schedule.util;


public abstract class Constants {
	public static final int BUZITYPE_ACTIVITY_CLEAN = 2;
	public static final int BUZITYPE_ORDER_EXPIRED = 1;
	public static final int BUZITYPE_ACT_REPORT = 3;
	
	//预售结束后的销售模式
	public static final int OLD_SELL = 0;//兼容老的活动
	public static final int STOP_SELL = 1;//停止售票
	public static final int COST_SELL = 2;//原价售票
	
	public static final int CHECK = 1;//审核中
    public static final int CHECK_FAILED = 3;//审核未通过
    public static final int COLLECT_MONEY = 2;//筹资中[黄、蓝、绿、橙]
    public static final int COLLECT_MONEY_FINISHED = 4;//已达成
    public static final int SHOW = 5;//演出中
    public static final int SHOW_OVER = 6;//演出结束 (到这一步的都是成功的项目)
    public static final int CANCELED = 7;//已取消
    public static final int FAILED = 8;//项目筹资失败
    public static final int WAIT_PAY = 9;//待付款
    public static final int SELL_OVER = 10;//售票结束
    public static final int SELL_COST = 11;//原价出售
    
    public static final int FUND_TYPE_PLATFORM = 1;//平台基金
    public static final int FUND_TYPE_OTHER = 2;//其他基金
    public static final int FUND_COMPUTE_STYLE_RATE = 1;//百分比结算制
    public static final int FUND_COMPUTE_STYE_REMAINING = 2; //剩余份额结算制
    
    public static final int FUND_AUDIT_STATE_ING = 1;//申请中
    public static final int FUND_AUDIT_STATE_SUC = 2;//成功
    public static final int FUND_AUDIT_STATE_FAIL = 3;//失败
    
    //-----------------------日历相关-------------------
    public static final int CALENDER_INTERVIEW = 1;//采访
    public static final int CALENDER_REHEARSE = 2;//排练
    public static final int CALENDER_CONFERENCE = 3;//发布会
    public static final int CALENDER_OTHER = 4;//其他
    public static final int CALENDER_RESERVATION = 5;//可预约
    public static final int CALENDER_SHOW = 6;//演出

    public static final int ALL = 0;//全部
    //---------------表演类型--------------------
    public static final int SHOW_TYPE_ACCOMPANIMENT = 1;//伴奏带
    //---------------音乐风格---------
    public static final int BALLAD = 1;//民谣
    public static final int ROCK = 2;//摇滚
    public static final int POP = 3;//流行
    public static final int ELECTRONICS = 4;//电子
    public static final int LIGHT = 5;//轻音乐
    public static final int RAP = 6;//说唱
    public static final int CLASSICAL = 7;//古典
    public static final int WORLD = 8;//世界音乐
    //-----------------认证状态----------------
    public static final int USER_CHECK_ING = 1;//认证中
    public static final int USER_CHECK_SUCCESS = 2;//认证通过
    public static final int USER_CHECK_FAILED = 3;//认证失败
    
    //内部的一些地址
    public static final String PROPERTIESURL="/var/showstart/config/showstart.properties";
    public static final String TICKET_CHECH_URL="http://"+StreamUtil.getUrls("url.internal.sc")+"/service/modifyTicketChecked";
}
