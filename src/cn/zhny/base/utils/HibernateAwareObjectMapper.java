package cn.zhny.base.utils;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper  extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public HibernateAwareObjectMapper() {
		
		this.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        registerModule(new Hibernate4Module());
    }
}
