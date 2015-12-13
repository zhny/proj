
package cn.zhny.ivs.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PreviewResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PreviewResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://bo.webservice.cms.hikvision.com/xsd}CommonResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errorDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="previewXml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreviewResult", propOrder = {
    "errorDesc",
    "previewXml"
})
public class PreviewResult
    extends CommonResult
{

    @XmlElementRef(name = "errorDesc", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> errorDesc;
    @XmlElementRef(name = "previewXml", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> previewXml;

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
     * 获取previewXml属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreviewXml() {
        return previewXml;
    }

    /**
     * 设置previewXml属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreviewXml(JAXBElement<String> value) {
        this.previewXml = value;
    }

}
