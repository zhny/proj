package cn.zhny.base.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;


public class DateFilter implements WebBindingInitializer {
	  
    public void initBinder(WebDataBinder binder, WebRequest request) {  
    	SimpleDateFormat my = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	my.setLenient(false);  
        binder.registerCustomEditor(Date.class,   
            new CustomDateEditor(my, true));  
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));  
    }  
}
