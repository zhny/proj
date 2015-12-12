package cn.zhny.admin.ivs;

import cn.zhny.base.action.AbstractAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 15-12-12.
 */
@Controller
@RequestMapping("admin/environment")
public class IvsManagerAction extends AbstractAction {

    @RequestMapping("preview")
    public String preview(){
        return "admin/ivs/preview";
    }

    @RequestMapping("playback")
    public String playback(){
        return "admin/isv/playback";
    }

}
