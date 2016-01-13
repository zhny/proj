package cn.zhny.admin.environment;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.base.entity.SysConf;
import cn.zhny.base.utils.DateUtil;
import cn.zhny.base.utils.DateUtil2;
import cn.zhny.base.utils.JsonUtil;
import cn.zhny.base.utils.PoiHelper;
import cn.zhny.base.utils.Reflections;
import cn.zhny.base.utils.StringUtil;
import cn.zhny.base.vo.BSPage;
import cn.zhny.base.vo.ExportXlsVo;
import cn.zhny.environment.entity.Record;
import cn.zhny.environment.entity.Stations;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;

@Controller
@RequestMapping("admin/environment")
@Transactional(rollbackFor=Exception.class)
public class EnvironmentManagerAction extends AbstractAction{
	
	@RequestMapping("")
	public String index(String col,String colName){
		return StringUtil.isEmpty(col)?"admin/environment/page":"admin/environment/col_page";
	}
	
	@ResponseBody
	@RequestMapping("page")
	public BSPage page(Integer current,Integer rowCount,String col){
		SysConf sysConf=SysConf.find.setUseQueryCache(true).where().eq("code", "ENV_PICK_TIME").findUnique();
		String pickTime=sysConf.getValue();
		ExpressionList<Record> expr=null;
		if(StringUtil.isEmpty(col)){
			expr=Record.find.fetch("station").where().eq("MOD(t0.id,"+pickTime+")", 0);
		}else{
			expr=Record.find.select("ID,rTime,"+col).fetch("station").where().eq("MOD(t0.id,"+pickTime+")", 0);
		}
		if(checkParam("rTimeStart")){
			expr.ge("rTime", getParam("rTimeStart"));
		}
		if(checkParam("rTimeEnd")){
			expr.le("rTime", getParam("rTimeEnd"));
		}
		PagedList<Record> page=expr.orderBy("ID desc").findPagedList(current-1, rowCount);
		return new BSPage(page,rowCount);
	}
	
	@ResponseBody
	@RequestMapping("getData")
	public Map<String,Object> getData(Long lastId,Integer len,Long stationId){
		SysConf sysConf=SysConf.find.setUseQueryCache(true).where().eq("code", "ENV_PICK_TIME").findUnique();
		String pickTime=sysConf.getValue();
		
		List<Record> records=null;
		if(lastId==null&&len==null)return null;
		if(len!=null){
			records=Record.find.where().eq("MOD(t0.id,"+pickTime+")", 0).orderBy("id desc").findPagedList(0, len).getList();
		}
		if(lastId!=null){
			records=Record.find.where().eq("id", lastId+1).findList();
		}
		if(records==null||records.size()==0)return null;
		List<String> rTimes=new ArrayList<String>();
		List<BigDecimal> d1s=new ArrayList<BigDecimal>();
		List<BigDecimal> d2s=new ArrayList<BigDecimal>();
		List<BigDecimal> d3s=new ArrayList<BigDecimal>();
		List<BigDecimal> d4s=new ArrayList<BigDecimal>();
		List<BigDecimal> d5s=new ArrayList<BigDecimal>();
		List<BigDecimal> d6s=new ArrayList<BigDecimal>();
		List<BigDecimal> d7s=new ArrayList<BigDecimal>();
		List<BigDecimal> d8s=new ArrayList<BigDecimal>();
		List<BigDecimal> d12s=new ArrayList<BigDecimal>();
		List<BigDecimal> d13s=new ArrayList<BigDecimal>();
		List<BigDecimal> d14s=new ArrayList<BigDecimal>();
		for(int i=records.size()-1;i>=0;i--){
			Record record=records.get(i);
			rTimes.add(DateUtil2.format_HH_mm_ss(record.getrTime()));
			d1s.add(record.getD1());
			d2s.add(record.getD2());
			d3s.add(record.getD3());
			d4s.add(record.getD4());
			d5s.add(record.getD5());
			d6s.add(record.getD6());
			d7s.add(record.getD7());
			d8s.add(record.getD8());
			d12s.add(record.getD12());
			d13s.add(record.getD13());
			d14s.add(record.getD14());
			if(i==0){
				lastId=record.getID();
			}
		}
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("rTimes", rTimes);
		result.put("d1s", d1s);
		result.put("d2s", d2s);
		result.put("d3s", d3s);
		result.put("d4s", d4s);
		result.put("d5s", d5s);
		result.put("d6s", d6s);
		result.put("d7s", d7s);
		result.put("d8s", d8s);
		result.put("d12s", d12s);
		result.put("d13s", d13s);
		result.put("d14s", d14s);
		result.put("lastId", lastId);
		return result;
	}
	
	@RequestMapping("view/{dx}")
	public String viewD1(@PathVariable String dx,Model model){
		model.addAttribute("dx", dx);
		model.addAttribute("query", request.getQueryString());
		model.addAttribute("rTimeStart",getParam("rTimeStart"));
		model.addAttribute("rTimeEnd",getParam("rTimeEnd"));
		return "admin/environment/dx";
	}
	
	@ResponseBody
	@RequestMapping("getData/{dx}")
	public void getData(@PathVariable String dx,Boolean isXy,HttpServletResponse response){
		SysConf sysConf=SysConf.find.setUseQueryCache(true).where().eq("code", "ENV_PICK_TIME").findUnique();
		String pickTime=sysConf.getValue();
		
		List<Record> records=null;
		ExpressionList<Record> expr=Record.find.where();
		if(checkParam("rTimeStart")){
			expr.ge("rTime", getParam("rTimeStart"));
		}
		if(checkParam("rTimeEnd")){
			expr.le("rTime", getParam("rTimeEnd"));
		}
		
		expr.eq("MOD(t0.id,"+pickTime+")", 0);
		records=expr.orderBy("id desc").findList();
		if(records==null||records.size()==0)return;
		if(isXy!=null&&isXy){
			List<Object[]> result=new ArrayList<Object[]>();
			for(int i=records.size()-1;i>=0;i--){
				Record record=records.get(i);
				result.add(new Object[]{record.getrTime().getTime(),(BigDecimal)Reflections.invokeGetter(record, dx)});
			}
			JsonUtil.toJson(result, response);
			return;
		}else{
			List<String> rTimes=new ArrayList<String>();
			List<BigDecimal> dxs=new ArrayList<BigDecimal>();
			for(int i=records.size()-1;i>=0;i--){
				Record record=records.get(i);
				rTimes.add(DateUtil2.format_yyyy_MM_dd_HH_mm_ss(record.getrTime()));
				dxs.add((BigDecimal)Reflections.invokeGetter(record, dx));
			}
			Map<String,Object> result=new HashMap<String,Object>();
			result.put("rTimes", rTimes);
			result.put("dxs", dxs);
			JsonUtil.toJson(result, response);
			return;
		}
	}
	
	
	@ResponseBody
	@RequestMapping("genData")
	public void genData(Integer size){
		for(int i=0;i<size;i++){
			Record record=new Record();
			record.setrTime(DateUtil.addMinutes(new Date(), i));
			record.setD1(new BigDecimal(new Random().nextInt(50)));
			record.setD2(record.getD1().multiply(new BigDecimal(1.8)));
			record.setD3(record.getD1().multiply(new BigDecimal(7)));
			record.setD4(record.getD1().divide(new BigDecimal(2.5),5,BigDecimal.ROUND_HALF_DOWN));
			record.setD5(record.getD1().multiply(new BigDecimal(50)));
			record.setD6(record.getD1().multiply(new BigDecimal(60)));
			record.setD7(record.getD1().multiply(new BigDecimal(5)));
			record.setD8(new BigDecimal(new Random().nextInt(100)));
			record.setD12(new BigDecimal(new Random().nextInt(12)));
			record.setD13(new BigDecimal(new Random().nextInt(500)));
			record.setD14(new BigDecimal(new Random().nextInt(14)));
			record.setStation(Stations.find.ref(1L));
			record.save();
		}
	}
	
	@RequestMapping("export")
	public void export(String dx,HttpServletResponse resp) throws IOException{
		SysConf sysConf=SysConf.find.setUseQueryCache(true).where().eq("code", "ENV_PICK_TIME").findUnique();
		String pickTime=sysConf.getValue();
		
		ExpressionList<Record> expr=Record.find.where();
		if(checkParam("rTimeStart")){
			expr.ge("rTime", getParam("rTimeStart"));
		}
		if(checkParam("rTimeEnd")){
			expr.le("rTime", getParam("rTimeEnd"));
		}
		
		expr.eq("MOD(t0.id,"+pickTime+")", 0);
		
		resp.setContentType("application/msexcel;charset=UTF-8");  
		resp.addHeader("Content-Disposition", "attachment;filename=\""+ new String(("export_data" +DateUtil2.format_yyyy_MM_dd(new Date())+ ".xls").getBytes("GBK"),"ISO8859_1") + "\"");  
		
		List<Record> records=expr.orderBy("id desc").findList();
		if(!CollectionUtils.isEmpty(records)){
			ExportXlsVo vo=new ExportXlsVo();
			
			vo.setSheetName("气象导出数据");
			List<String> titles=new ArrayList<String>();
			titles.add("采集时间");
			titles.add("温度 ℃");
			titles.add("湿度 %");
			titles.add("风向 °");
			titles.add("风速 m/s");
			titles.add("气压 bpa");
			titles.add("总辐射 ");
			titles.add("雨量 ml");
			titles.add("土壤温度 ℃");
			titles.add("日照时数 h");
			titles.add("PM2.5");
			titles.add("水PH值");
			vo.setTitles(titles);
			List<List<String>> rows=new ArrayList<List<String>>();
			
			for(Record record:records){
				rows.add(
					Arrays.asList(
							DateUtil2.format_yyyy_MM_dd_HH_mm_ss(record.getrTime()),
							record.getD1().toString(),
							record.getD2().toString(),
							record.getD3().toString(),
							record.getD4().toString(),
							record.getD5().toString(),
							record.getD6().toString(),
							record.getD7().toString(),
							record.getD8().toString(),
							record.getD12().toString(),
							record.getD13().toString(),
							record.getD14().toString()
							)
				);
			}
			vo.setRows(rows);
			PoiHelper.createXls(resp.getOutputStream(), vo);
		}
		
	}
	
}
