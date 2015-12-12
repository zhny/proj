package cn.zhny.api.environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.base.utils.DateUtil2;
import cn.zhny.base.utils.JsonUtil;
import cn.zhny.environment.entity.Record;

@Controller
@RequestMapping("api/em")
public class EnvironmentApi  extends AbstractAction{
	
	@ResponseBody
	@RequestMapping("cur")
	public String getData(String callback){
		//初始化取多条数据
		List<Record> records=Record.find.order("id desc").findPagedList(0, 1).getList();
		if(records==null||records.size()==0)return null;
		Record record=records.get(0);
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("rTimes", DateUtil2.format_yyyy_MM_dd_HH_mm_ss(record.getrTime()));
		result.put("d1", record.getD1());
		result.put("d2", record.getD2());
		result.put("d3", record.getD3());
		result.put("d4", record.getD4());
		result.put("d5", record.getD5());
		result.put("d6", record.getD6());
		result.put("d7", record.getD7());
		result.put("d8", record.getD8());
		result.put("d12", record.getD12());
		result.put("d13", record.getD13());
		result.put("d14", record.getD14());
		result.put("lastId", record.getID());
		
		String jsonResult=JsonUtil.toJson(result);
		if(callback!=null&&!"".equals(callback)){
			return callback+"("+jsonResult+")";
		}else{
			return jsonResult;
		}
	}
}


