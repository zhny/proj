package cn.zhny.ivs.service;

import java.net.MalformedURLException;

import cn.zhny.ivs.utils.CmsServiceUtil;
import cn.zhny.ivs.webservice.CommonPageReq;
import cn.zhny.ivs.webservice.DeviceResult;

public class TestService {
	
      public static void main(String[] args) throws MalformedURLException {
    	CommonPageReq pageReq= new CommonPageReq();
    	pageReq.setReqPage(1);
    	pageReq.setPageItems(100);
        DeviceResult deviceResult=CmsServiceUtil.getPort().getAllDevice(CmsServiceUtil.getSessionId(), pageReq);
        System.err.println(deviceResult.getAllRow());
	}
}
