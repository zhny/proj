
package cn.zhny.ivs.webservice;



public class PlaybackParamReq {

    protected String cameraIndexCode;
    protected Integer playWndIndex;
    protected Integer recordLocation;
	public String getCameraIndexCode() {
		return cameraIndexCode;
	}
	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}
	public Integer getPlayWndIndex() {
		return playWndIndex;
	}
	public void setPlayWndIndex(Integer playWndIndex) {
		this.playWndIndex = playWndIndex;
	}
	public Integer getRecordLocation() {
		return recordLocation;
	}
	public void setRecordLocation(Integer recordLocation) {
		this.recordLocation = recordLocation;
	}

    
}
