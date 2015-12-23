package cn.zhny.admin.ivs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhny.base.action.AbstractAction;
import cn.zhny.ivs.utils.CmsServiceUtil;
import cn.zhny.ivs.vo.CameraVo;
import cn.zhny.ivs.webservice.CameraDTO;
import cn.zhny.ivs.webservice.CameraResult;
import cn.zhny.ivs.webservice.CommonPageReq;
import cn.zhny.ivs.webservice.PlaybackParamReq;
import cn.zhny.ivs.webservice.PlaybackResult;
import cn.zhny.ivs.webservice.PreviewResult;

/**
 * Created by Administrator on 15-12-12.
 */
@Controller
@RequestMapping("admin/ivs")
public class IvsManagerAction extends AbstractAction {

    @RequestMapping("preview")
    public String preview(){
        return "admin/ivs/preview";
    }
    
    @RequestMapping("param/{type}")
    @ResponseBody
    public Object param(@PathVariable String type){
    	
    	if(type.equals("allCamera")){
    		return allCamera();
    	}else if(type.equals("prewviewParam")){
        	return  prewviewParam(getParam("indexCode").toString());
    	}else if(type.equals("getPlaybackParam")){
    		return getPlaybackParam(getParam("indexCode").toString(),Integer.parseInt(getParam("playWndIndex").toString()));
    	}else{
    		return null;
    	}
    	
    }
    
    private List<CameraVo> allCamera(){
    	CommonPageReq pageReq=new CommonPageReq();
    	pageReq.setPageItems(100);
    	pageReq.setReqPage(1);
    	CameraResult cameraResult=CmsServiceUtil.getPort().getAllCamera(CmsServiceUtil.getSessionId(), pageReq);
    	List<CameraDTO> cameraDTOs=cameraResult.getCameraArray();
    	if(!CollectionUtils.isEmpty(cameraDTOs)){
    		List<CameraVo> result=new ArrayList<CameraVo>();
    		for(CameraDTO dto:cameraDTOs){
    			CameraVo vo=new CameraVo(dto);
    			result.add(vo);
    		}
    		return result;
    	}
    	return null;
    }
    
    private String prewviewParam(String indexCode){
    	PreviewResult previewResult=CmsServiceUtil.getPort().getPreviewParam(CmsServiceUtil.getSessionId(), indexCode);
    	return previewResult.getPreviewXml().getValue(); 
    }
    
    private String getPlaybackParam(String indexCode,Integer playWndIndex){
    	PlaybackParamReq req=new PlaybackParamReq();
    	req.setCameraIndexCode(indexCode);
    	req.setPlayWndIndex(playWndIndex);
    	req.setRecordLocation(2);
    	PlaybackResult playbackResult=CmsServiceUtil.getPort().getPlaybackParam(CmsServiceUtil.getSessionId(), req);
    	return playbackResult.getPlaybackXml();
    }

    @RequestMapping("playback")
    public String playback(Model model){
    	
        return "admin/ivs/playback";
    }

}
