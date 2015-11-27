package cn.zhny.base.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.zhny.base.entity.Attachment;
import cn.zhny.base.entity.Constraint;
import com.avaje.ebean.EbeanServer;


@Service
public class AttachmentService extends AbstractService<Attachment>{

	@Autowired
	private EbeanServer ebeanServer;
 
	
	@Override
	public Class<Attachment> getClazz() {
		return Attachment.class;
	}

	public Attachment queryAttachmentByuri(String uri){
		return ebeanServer.find(Attachment.class).where().eq("uri", uri).ne("status", Constraint.StatusEnum.D).findUnique();
	}
	
	 
}
