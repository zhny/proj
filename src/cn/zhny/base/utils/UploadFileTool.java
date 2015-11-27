package cn.zhny.base.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.zhny.base.entity.Attachment;
import cn.zhny.base.service.AttachmentService;
import cn.zhny.base.vo.Jo;


/**
 * 上传工具包
 * @author zhny
 *
 */
@Component
public class UploadFileTool {
	
	private static final Logger log = LoggerFactory.getLogger(UploadFileTool.class);
	
	
	@Autowired 
	private AttachmentService attachmentService;
	/**
	 * 文件上传
	 * @param file
	 * @param confDir
	 * @return Jo 如果成功，则返回Attachment对象
	 */
	public Jo upload(MultipartFile file,String confDir,HttpServletRequest request){
		log.info("upload in"); 
		String conf=Configure.getInstance().getValue("common", confDir).toString();
		String confPath=request.getSession().getServletContext().getRealPath("")+File.separator+conf;
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		String now = format.format(new Date());
		File dir=new File(confPath+now+File.separatorChar);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		if (file.isEmpty()||file==null){
			return new Jo(Jo.CODE_FAIL,"空文件");
		}
		Attachment attachment=new Attachment();
		try {
			
			if(".zip".equals(getSuffix(file).toLowerCase())){
				ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file.getBytes()));
				ZipEntry zipEntry;
			
				String indexFileName="";
				boolean hasIndex = false;
				while ((zipEntry = zis.getNextEntry()) != null) {
					if(zipEntry.isDirectory())continue;
					String name = zipEntry.getName();
					if(name!=null && name.length()>10){
						String fileName = name.substring(name.length()-10, name.length());
						if (fileName!=null && (fileName.equals("index.html") || fileName.equals("index.jsp"))) { //zipEntry.getName().matches("index\\.html|index\\.html|index\\.jsp|tmpl\\.xls")) {
							hasIndex = true;
							indexFileName=name;
							break;
						}
					}
					
				}
				if (!hasIndex) {
					return new Jo(Jo.CODE_FAIL,"ZIP文件中未找到index文件");
				}
				zis.close();
				zis = new ZipInputStream(new ByteArrayInputStream(file.getBytes()));
				byte[] buffer = new byte[4096];
				String uploadDirName=UUID.randomUUID().toString().replaceAll("-", "");
				String targetBaseDirName = confPath+now + File.separator + uploadDirName;			//创建解压文件保存路径	
				new File(targetBaseDirName).mkdirs();				
				String targetFileName;
				while ((zipEntry = zis.getNextEntry()) != null) {
					targetFileName = targetBaseDirName + File.separator+ zipEntry.getName();
					if (zipEntry.isDirectory()) {
						new File(targetFileName).mkdirs();								// 如果entry是一个目录，则创建目录
						continue;
					} 
					new File(targetFileName).getParentFile().mkdirs();				// 如果entry是一个文件，则创建父目录
					File newFile = new File(targetFileName);
		            new File(newFile.getParent()).mkdirs();
		 
		            FileOutputStream fos = new FileOutputStream(newFile);             
		 
		            int len;
		            while ((len = zis.read(buffer)) > 0) {
		       		fos.write(buffer, 0, len);
		            }
		            fos.flush();
		            fos.close();   
				}
				attachment.setUri(conf+now+"/"+uploadDirName+"/"+indexFileName);
				log.info("上传文件到："+targetBaseDirName);
			}else{
				String _filePath =UUID.randomUUID().toString().replaceAll("-", "")+getSuffix(file);	//uuid+文件后座
				String filePath = dir.getAbsolutePath() +File.separatorChar+ _filePath;
				File localFile=new File(filePath);
				FileCopyUtils.copy(file.getBytes(),localFile);
				attachment.setUri(conf+now+"/"+_filePath);		//文件保存的相对路径
				log.info("上传文件到："+filePath);
			}
			
			attachment.setSize(String.valueOf((file.getSize())));
			attachment.setTitle(file.getOriginalFilename());
			attachment.setType(file.getContentType());
			attachment = attachmentService.create(attachment);
			return new Jo(attachment);
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 文件上传
	 * @param file
	 * @param confDir
	 * @return Jo 如果成功，则返回Attachment对象
	 */
	public Jo uploadSoftBind(MultipartFile file,String confDir,HttpServletRequest request,Long limitsize){
		log.info("uploadSoftBind in");
		String conf=Configure.getInstance().getValue("common", confDir).toString();
		String confPath=request.getSession().getServletContext().getRealPath("")+File.separator+conf;
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		String now = format.format(new Date());
		File dir=new File(confPath+now+File.separatorChar);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		if (file.isEmpty()||file==null){
			return new Jo(Jo.CODE_FAIL,"空文件");
		}
		Attachment attachment=new Attachment();
		try {
			
			if(".____________________zip".equals(getSuffix(file).toLowerCase())){//del by dengwei 20150519 // 判断上传的ZIP文件中是否有index.html/index.htm/index.jsp
				ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file.getBytes()));
				ZipEntry zipEntry;
			
				String indexFileName="";
				boolean hasIndex = false;
				while ((zipEntry = zis.getNextEntry()) != null) {
					if(zipEntry.isDirectory())continue;
					if (zipEntry.getName().matches("index\\.html|index\\.html|index\\.jsp|tmpl\\.xls")) {
						hasIndex = true;
						indexFileName=zipEntry.getName();
						break;
					}
				}
				if (!hasIndex) {
					return new Jo(Jo.CODE_FAIL,"ZIP文件中未找到index文件");
				}
				zis.close();
				zis = new ZipInputStream(new ByteArrayInputStream(file.getBytes()));
				byte[] buffer = new byte[4096];
				String uploadDirName=UUID.randomUUID().toString().replaceAll("-", "");
				String targetBaseDirName = confPath+now + File.separator + uploadDirName;			//创建解压文件保存路径	
				new File(targetBaseDirName).mkdirs();				
				String targetFileName;
				while ((zipEntry = zis.getNextEntry()) != null) {
					targetFileName = targetBaseDirName + File.separator+ zipEntry.getName();
					if (zipEntry.isDirectory()) {
						new File(targetFileName).mkdirs();								// 如果entry是一个目录，则创建目录
						continue;
					} 
					new File(targetFileName).getParentFile().mkdirs();				// 如果entry是一个文件，则创建父目录
					File newFile = new File(targetFileName);
		            new File(newFile.getParent()).mkdirs();
		 
		            FileOutputStream fos = new FileOutputStream(newFile);             
		 
		            int len;
		            while ((len = zis.read(buffer)) > 0) {
		       		fos.write(buffer, 0, len);
		            }
		            fos.flush();
		            fos.close();   
				}
				attachment.setUri(conf+now+"/"+uploadDirName+"/"+indexFileName);
				log.info("上传文件到："+targetBaseDirName);
			}else{
				String _filePath =UUID.randomUUID().toString().replaceAll("-", "")+getSuffix(file);	//uuid+文件后座
				String filePath = dir.getAbsolutePath() +File.separatorChar+ _filePath;
				File localFile=new File(filePath);
				
				
				//新增附件大小限制，其中limit
				Long fileSize =file.getSize();
				Long size = fileSize / 1024L; //字节转为K字节    
		        if(size>limitsize){   
					return new Jo(Jo.CODE_FAIL,"附件大小不能超过"+limitsize/1024L+"MB,请重新上传");

		        }    
		        if(size<=0){  
					return new Jo(Jo.CODE_FAIL,"附件大小不能为空");

		        }   
				
				FileCopyUtils.copy(file.getBytes(),localFile);
				attachment.setUri(conf+now+"/"+_filePath);		//文件保存的相对路径
				log.info("上传文件到："+filePath);
			}
			
			attachment.setSize(String.valueOf((file.getSize())));
			attachment.setTitle(file.getOriginalFilename());
			attachment.setType(file.getContentType());
			attachment = attachmentService.create(attachment);
			return new Jo(attachment);
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 保存上传文件
	 * @param file	 	 文件
	 * @param confDir	 上传到的文件夹路径，参见Configure类
	 * @param acceptType 可被接受的文件类型 
	 * @param size 文件大小限制 若来自前端的请求为空，则默认一个大小限制
	 * @returnJo 如果成功，则返回Attachment对象
	 */
	public Jo uploadSoftBind(MultipartFile file,String confDir,HttpServletRequest request,String[] acceptType,Long size){
		if(!accept(file, acceptType)){
			return new Jo(Jo.CODE_FAIL,"文件格式不支持");
		}else{
			return uploadSoftBind(file,confDir,request,size);
		}
	}
	
	/**
	 * 保存上传文件
	 * @param file	 	 文件
	 * @param confDir	 上传到的文件夹路径，参见Configure类
	 * @param acceptType 可被接受的文件类型 
	 * @returnJo 如果成功，则返回Attachment对象
	 */
	public Jo upload(MultipartFile file,String confDir,HttpServletRequest request,String[] acceptType){
		if(!accept(file, acceptType)){
			return new Jo(Jo.CODE_FAIL,"文件格式不支持");
		}else{
			return upload(file,confDir,request);
		}
	}

	/**
	 * 验证上传文件是否是复合类型的文件
	 * @param file
	 * @param acceptType
	 * @return
	 */
	public boolean accept(MultipartFile file,String[] acceptType){
		if(file==null||file.isEmpty())return false;
		for(String type:acceptType){
			if(type.toLowerCase().equals(getSuffix(file).toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取文件扩展名
	 * @param file 上传的文件
	 * @return 返回文件扩展名
	 */
	public String getSuffix(MultipartFile file){
		int idx=file.getOriginalFilename().lastIndexOf(".");
		if(idx<0){
			return "";
		}
		return file.getOriginalFilename().substring(idx);     
	} 
	
	
	/**
	 * 去后缀的文件名
	 * @param file
	 * @return
	 */
	public String removeSuffix(MultipartFile file){
		int idx=file.getOriginalFilename().lastIndexOf(".");
		return file.getOriginalFilename().substring(0,idx);     
	}
	
}
