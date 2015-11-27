package cn.zhny.base.utils;

import org.hibernate.internal.util.StringHelper;

public class ImprovedNamingStrategyFix extends org.hibernate.cfg.ImprovedNamingStrategy{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String propertyToColumnName(String propertyName) {
		return addUnderscores( StringHelper.unqualify(propertyName) );
	}
	
	@Override
	public String columnName(String columnName) {
		return columnName;
	}
}
