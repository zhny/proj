package cn.zhny.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.base.entity.SysConf;
import cn.zhny.base.utils.JsonUtil;
import cn.zhny.base.vo.Jo;

@Controller
@RequestMapping("/admin/sysconf")
@Transactional(rollbackFor=Exception.class)
public class SysConfAction extends AbstractAction{
	
	
	@RequestMapping("")
	public String _(Model model){
		List<SysConf> confs=SysConf.find.all();
		model.addAttribute("confs", confs);
		return "admin/sysconf";
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public Jo update(@RequestBody String data){
		System.err.println(data);
		List<SysConf> confs=JsonUtil.gson.fromJson(data, new TypeToken<List<SysConf>>() {}.getType());
		if(!CollectionUtils.isEmpty(confs)){
			for(SysConf conf:confs){
				SysConf pConf=SysConf.find.byId(conf.getId());
				pConf.setValue(conf.getValue());
				pConf.update();
			}
		}
		return new Jo();
	}
}
