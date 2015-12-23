package cn.zhny.ivs.vo;

import java.util.List;

import cn.zhny.ivs.webservice.CameraDTO;

public class CameraVo {

	private Integer cameraId;
	private Integer cameraType;
	private Integer chanNum;
	private Integer cmsCascadeId;
	private Integer connectType;
	private Integer deviceId;
	private String indexCode;
	private Double latitude;
	private Double longitude;
	private String matrixCode;
	private String name;
	private Integer pixel;
	private List<Integer> privilegeCode;
	private Integer ptzType;
	private List<Integer> recLacation;
	private Boolean sound;
	private Integer streamId;
	private Integer streamType;
	private Integer vrmId;

	public CameraVo() {
	}

	public CameraVo(CameraDTO cameraDTO) {
		this.cameraId = cameraDTO.getCameraId().getValue();
		this.cameraType = cameraDTO.getCameraType().getValue();
		this.chanNum = cameraDTO.getChanNum().getValue();
		this.cmsCascadeId = cameraDTO.getCmsCascadeId().getValue();
		this.connectType = cameraDTO.getConnectType().getValue();
		this.deviceId = cameraDTO.getDeviceId().getValue();
		this.indexCode = cameraDTO.getIndexCode().getValue();
		this.latitude = cameraDTO.getLatitude().getValue();
		this.longitude = cameraDTO.getLongitude().getValue();
		this.matrixCode = cameraDTO.getMatrixCode().getValue();
		this.name = cameraDTO.getName().getValue();
		this.pixel = cameraDTO.getPixel().getValue();
		this.privilegeCode = cameraDTO.getPrivilegeCode();
		this.ptzType = cameraDTO.getPtzType().getValue();
		this.recLacation = cameraDTO.getRecLacation();
		this.sound = cameraDTO.getSound().getValue();
		this.streamId = cameraDTO.getStreamId().getValue();
		this.streamType = cameraDTO.getStreamType().getValue();
		this.vrmId = cameraDTO.getVrmId().getValue();

	}

	public Integer getCameraId() {
		return cameraId;
	}

	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}

	public Integer getCameraType() {
		return cameraType;
	}

	public void setCameraType(Integer cameraType) {
		this.cameraType = cameraType;
	}

	public Integer getChanNum() {
		return chanNum;
	}

	public void setChanNum(Integer chanNum) {
		this.chanNum = chanNum;
	}

	public Integer getCmsCascadeId() {
		return cmsCascadeId;
	}

	public void setCmsCascadeId(Integer cmsCascadeId) {
		this.cmsCascadeId = cmsCascadeId;
	}

	public Integer getConnectType() {
		return connectType;
	}

	public void setConnectType(Integer connectType) {
		this.connectType = connectType;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getMatrixCode() {
		return matrixCode;
	}

	public void setMatrixCode(String matrixCode) {
		this.matrixCode = matrixCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPixel() {
		return pixel;
	}

	public void setPixel(Integer pixel) {
		this.pixel = pixel;
	}

	public List<Integer> getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(List<Integer> privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	public Integer getPtzType() {
		return ptzType;
	}

	public void setPtzType(Integer ptzType) {
		this.ptzType = ptzType;
	}

	public List<Integer> getRecLacation() {
		return recLacation;
	}

	public void setRecLacation(List<Integer> recLacation) {
		this.recLacation = recLacation;
	}

	public Boolean getSound() {
		return sound;
	}

	public void setSound(Boolean sound) {
		this.sound = sound;
	}

	public Integer getStreamId() {
		return streamId;
	}

	public void setStreamId(Integer streamId) {
		this.streamId = streamId;
	}

	public Integer getStreamType() {
		return streamType;
	}

	public void setStreamType(Integer streamType) {
		this.streamType = streamType;
	}

	public Integer getVrmId() {
		return vrmId;
	}

	public void setVrmId(Integer vrmId) {
		this.vrmId = vrmId;
	}

}
