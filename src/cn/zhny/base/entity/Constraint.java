package cn.zhny.base.entity;

/**
 * 枚举约束
 * @author zhny
 *
 */
public final class Constraint {
	
	/**
	 * 权限类型
	 * <ul>
	 * <li>MENU      菜单		  </li>
	 * <li>VIEW		   页面	      </li>
	 * <li>TOOL_BAR	    工具栏	      </li>
	 * <li>REQUEST   请求                      </li>
	 * </ul>
	 */
	public enum PermissionTypeEnum{
		MENU,
		VIEW,
		TOOL_BAR,
		REQUEST
	}
	
	/**
	 * 实体状态
	 * <ul>
	 * <li>Y      有效		  </li>
	 * <li>D		 已删除	      </li>
	 * </ul>
	 */
	public enum StatusEnum{
		Y,			
		D,
		F
	}
	
	/*
	 * 广告联盟-计费方式枚举
	 * */
	public enum ChargeTypeEnum{
		DIANJI,			
		TANCHUANG,
		XUANFU
	}
	/*
	 * 广告联盟-结算周期枚举
	 * */
	public enum SettlementCycleEnum{
		RI,			
		ZHOU,
		YUE,
		NIAN
	}
	
	/*
	 * 广告联盟-Plan-审核状态枚举
	 * */
	public enum AuditStatusEnum{
		DAISHEN,			
		TONGGUO,
		WEIGUO
	}
	
	/*
	 * 广告联盟-Plan-数据放回方式枚举
	 * */
	public enum DataBackTimeEnum{
		TIME,//实时
		M1,//1分钟
		M5,//5分钟
		M30,
		H1,//1小时			
		H2,
		D1//1日
	}
	/*
	 * 广告联盟-Plan-投放类型枚举
	 * */
	public enum PublishTypeEnum{
		ALL,			
		LIMITWEB,
		LIMITWEBTYPE
	}
	
	/*
	 * 广告联盟-Plan-网站类型枚举
	 * */
	public enum WebSiteTypeEnum{

		IT科技,娱乐影音,行业贸易,购物电商,人才房产,教育金融,艺术文化,导航搜索,综合门户,其它

	}
	/*
	 * 软件捆绑-soft-软件类型枚举
	 * */
	public enum SoftwareTypeEnum{
		新闻,
		门户,
		游戏,
		论坛,
		专业,
		其它类型,
		全部

	}
	
	/*
	 * 软件捆绑-soft-广告收费情况
	 * */
	public enum SoftwarePayTypeEnum{
		free,
		pay,
		cooperate

	}
	/**
	 * 审核流程
	 * <ul>
	 * <li>UNSTART 未开始 </li>
	 * <li>INITIAL 初始化 待审核 </li>
	 * <li>THROUGH 审核通过</li>
	 * <li>REFUSE 审核拒绝</li>
	 * </ul>
	 */
	public enum  Process {
		 UNSTART,
		 INITIAL,//初始化 待审核
		 THROUGH, //审核通过
		 REFUSE, //审核拒绝
	}
	
	/*
	 * 广告联盟-WebSite-网站审核状态枚举
	 * */
	public enum WebSiteStatusEnum{
		RUNNING,//实时
		STOP,
		D,//软删除标志
		DAISHEN,
		YISHEN

	}
	
	
	/*
	 * 广告联盟-WebSite-网站审核状态枚举
	 * */
	public enum AdStatusEnum{
		RUNNING,//实时
		STOP,
		D,//软删除标志
		DAISHEN,
		YISHEN

	}
	
	/*
	 * 广告联盟-Ad-广告类型枚举
	 * */
	public enum AdTypeEnum{
		TANCHUANG,//弹窗
		DIANJI//点击
		

	}
	
	
	/**
	 * 客户类型
	 * <ul>
	 * </ul>
	 */
	public enum CustomTypeEnum{
		GGZ,
		RJZ,
		RJZ_GGZ,
		SERVER,
		
		USER,				//过期
		ENTERPRISE,			//过期
		USER_ENTERPRISE,	//过期
		
	}
	
	/**
	 * 金融账户类型
	 * <ul>
	 * <li>BANK_ACCOUNT 银行账户 </li>
	 * </ul>
	 */
	public enum FinAccountTypeEnum{
		BANK_ACCOUNT
	}
	
	/**
	 * 团购产品展示形式
	 * <ul>
	 * <li>OPEN_WIN 弹窗 </li>
	 * </ul>
	 */
	public enum GrouponProductShowType{
		OPEN_WIN
	}
	
	/**
	 * 团购产品周期
	 * <ul>
	 * <li>WEEK 周 </li>
	 * </ul>
	 */
	public enum GrouponProductCircle{
		WEEK,
		DAY,
		MONTH,
		YEAR
		
	}
	
	/**
	 * 团购形式
	 * <ul>
	 * <li>FIVE 周五家轮播</li>
	 * </ul>
	 */
	public enum GrouponProductType{
		FIVE
	}
	
	/**
	 * 订单状态
	 * <ul>
	 * <li>WAIT_FOR_PAY 等待支付</li>
	 * <li>PAYED        已支付</li>
	 * <li>CANCELLED    已取消</li>
	 * <li>SUCCESS      订购成功</li>
	 * </ul>
	 */
	public enum OrderStatus{
		WAIT_FOR_PAY,
		PAYED,
		CANCELLED,
		SUCCESS
		
	}
	
	/**
	 * 支付方式
	 * <ul>
	 * <li>ALIPAY 支付宝</li>
	 * <li>FINACCOUNT 金融账户 </li>
	 * </ul>
	 */
	public enum PayMode{
		ALIPAY,
		FINACCOUNT
	}
	
	/**
	 * 网店营销服务类型
	 * <ul>
	 * <li>INNER 站内推广</li>
	 * <li>OUTER 站外引流</li>
	 * </ul>
	 */
	public enum OlStoreType{
		INNER,
		OUTER
	}
	
	/**
	 * 订单产品类型
	 * <ul>
	 * <li>OS 网店营销</li>
	 * <li>GP 团购</li>
	 * </ul>
	 */
	public enum OrderProductType{
		OS,
		GP,
		CZ
	}
	
	/**
	 * 账户交易类型  如：账户充值 
	 * <ul>
	 * <li>RECHARGE 账户充值</li>
	 * <li>ADCHARGING 广告扣费</li>
	 * <li>FORPAY 余额付款</li>
	 * </ul>
	 */
	public enum transactionType{
		RECHARGE,
		ADCHARGING,
		FORPAY
	}
	
 
	/**
	 * SliderPanel类型
	 * <ul>
	 * <li>LOCAL 本地上传</li>
	 * <li>OUTSIDE 外部链接</li>
	 * </ul>
	 */
	public enum LinkType{
		LOCAL,
		OUTSIDE
	}
	
}
