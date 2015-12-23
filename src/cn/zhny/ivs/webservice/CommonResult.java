
package cn.zhny.ivs.webservice;



public class CommonResult {

    protected Integer errorCode;
    protected String errorMsg;
    protected Boolean result;
    protected String strXmlRev;
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getStrXmlRev() {
		return strXmlRev;
	}
	public void setStrXmlRev(String strXmlRev) {
		this.strXmlRev = strXmlRev;
	}

    
}
