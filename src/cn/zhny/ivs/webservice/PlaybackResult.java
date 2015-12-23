
package cn.zhny.ivs.webservice;


public class PlaybackResult
    extends CommonResult
{

    protected String errorDesc;
    protected String playbackXml;
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getPlaybackXml() {
		return playbackXml;
	}
	public void setPlaybackXml(String playbackXml) {
		this.playbackXml = playbackXml;
	}

}
