package cn.zhny.base.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public abstract class AbstractRest{
	private static final Logger log = LoggerFactory.getLogger(AbstractRest.class);
	@Autowired
    private HttpServletRequest request;
	
//	@Autowired
//	private UploadFileTool uploadFileTool;
//	
//	/**
//	 * 通用的文件上传文件上传
//	 * @param multiRequest
//	 * @return
//	 */
//	@Loggable
//	@RequestMapping(value = "/commonUpload", method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.OK)
//	public void commonUpload(MultipartHttpServletRequest multiRequest,HttpServletResponse response) {
//		response.setContentType("text/plain;charset=utf-8");
//		try {
//			List<MultipartFile> files = multiRequest.getFiles("uploadFile");
//			if(files.size()==1){
//				MultipartFile file = files.get(0);
//				JsonUtil.toJson(uploadFileTool.upload(file,"upload.file",request,new String[]{".jpg",".jpeg",".png",".gif",".zip"}),response);
//			}else{
//				JsonUtil.toJson(new Jo(Jo.CODE_FAIL,"请选择上传的文件"),response);
//			}
//			
//		} catch (Exception e) {
//			log.error("File upload error", e);
//			JsonUtil.toJson(new Jo(Jo.CODE_FAIL,"文件上传出错，请重试"),response);
//		}
//	}
//	
//	@Loggable
//	@RequestMapping(value = "/editorUpload", method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.OK)
//	public void editorUpload(MultipartHttpServletRequest multiRequest,HttpServletResponse response) {
//		response.setContentType("text/plain;charset=utf-8");
//		Map<String,Object> result=new HashMap<String,Object>();
//		try {
//			List<MultipartFile> files = multiRequest.getFiles("imgFile");
//			if(files.size()==1){
//				MultipartFile file = files.get(0);
//				Jo jo=uploadFileTool.upload(file,"upload.file",request,new String[]{".jpg",".jpeg",".png",".gif"});
//				if(jo.getCode()==0){
//					Attachment att=(Attachment) jo.getData();
//					result.put("error", 0);
//					result.put("url", request.getServletContext().getContextPath()+"/"+att.getUri());
//				}else{
//					result.put("error", 1);
//					result.put("message", jo.getMsg());
//				}
//			}else{
//				result.put("error", 1);
//				result.put("message","上传文件解析出错");
//			}
//			
//		} catch (Exception e) {
//			log.error("File upload error", e);
//			result.put("error", 1);
//			result.put("message","文件上传出错，请重试");
//		}
//		JsonUtil.toJson(result,response);
//	}
	
	
	protected Object getParam(String paramName) {
		Object param = request.getParameter(paramName);
		if(param==null){
			param = request.getAttribute(paramName);
		}
		return param;
	}
	
	protected Object getParamISO(String paramName) {
		Object param = request.getParameter(paramName);
		if(param==null){
			param = request.getAttribute(paramName);
		}
		if(param instanceof String &&param!=null){
			try {
				return new String(param.toString().getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				log.error("get param error name="+paramName, e);
			}
		}
		return param;
	}
	
	protected Object getParams(String paramName) {
		Object param = request.getParameterValues(paramName);
		if(param==null){
			param = request.getAttribute(paramName);
		}
		return param;
	}
	
	
	protected HttpSession getSession(){
		return request.getSession();
	}
/**
 * 检查参数是否不为空
 * 空 return false
 * 非空 return true
 * @param paramName
 * @return
 */
	protected boolean checkParam(String paramName) {
		return !("".equals(this.getParam(paramName))||null==this.getParam(paramName));
	}
	
	/**
	 * 检查参数是否不为空，数组
	 * @param paramName
	 * @return
	 */
	
	protected boolean  checkParams(String paramName) {
		return !("".equals(this.getParams(paramName))||null==this.getParams(paramName));
	}
	
}
