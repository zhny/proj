
package cn.zhny.ivs.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PlaybackResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PlaybackResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://bo.webservice.cms.hikvision.com/xsd}CommonResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errorDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="playbackXml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlaybackResult", propOrder = {
    "errorDesc",
    "playbackXml"
})
public class PlaybackResult
    extends CommonResult
{

    @XmlElementRef(name = "errorDesc", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> errorDesc;
    @XmlElementRef(name = "playbackXml", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> playbackXml;

    /**
     * 获取errorDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorDesc() {
        return errorDesc;
    }

    /**
     * 设置errorDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorDesc(JAXBElement<String> value) {
        this.errorDesc = value;
    }

    /**
     * 获取playbackXml属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlaybackXml() {
        return playbackXml;
    }

    /**
     * 设置playbackXml属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlaybackXml(JAXBElement<String> value) {
        this.playbackXml = value;
    }

}
