
package cn.zhny.ivs.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>IvaServerDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="IvaServerDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="controlUnitId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ctrlPort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="indexCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ipAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ivaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="netZoneId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="strXmlRev" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IvaServerDTO", propOrder = {
    "controlUnitId",
    "ctrlPort",
    "indexCode",
    "ipAddr",
    "ivaId",
    "name",
    "netZoneId",
    "port",
    "strXmlRev"
})
public class IvaServerDTO {

    @XmlElementRef(name = "controlUnitId", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> controlUnitId;
    @XmlElementRef(name = "ctrlPort", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> ctrlPort;
    @XmlElementRef(name = "indexCode", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> indexCode;
    @XmlElementRef(name = "ipAddr", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ipAddr;
    @XmlElementRef(name = "ivaId", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> ivaId;
    @XmlElementRef(name = "name", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> name;
    @XmlElementRef(name = "netZoneId", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> netZoneId;
    @XmlElementRef(name = "port", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> port;
    @XmlElementRef(name = "strXmlRev", namespace = "http://bo.webservice.cms.hikvision.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> strXmlRev;

    /**
     * 获取controlUnitId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getControlUnitId() {
        return controlUnitId;
    }

    /**
     * 设置controlUnitId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setControlUnitId(JAXBElement<Integer> value) {
        this.controlUnitId = value;
    }

    /**
     * 获取ctrlPort属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCtrlPort() {
        return ctrlPort;
    }

    /**
     * 设置ctrlPort属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCtrlPort(JAXBElement<Integer> value) {
        this.ctrlPort = value;
    }

    /**
     * 获取indexCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndexCode() {
        return indexCode;
    }

    /**
     * 设置indexCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndexCode(JAXBElement<String> value) {
        this.indexCode = value;
    }

    /**
     * 获取ipAddr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIpAddr() {
        return ipAddr;
    }

    /**
     * 设置ipAddr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIpAddr(JAXBElement<String> value) {
        this.ipAddr = value;
    }

    /**
     * 获取ivaId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIvaId() {
        return ivaId;
    }

    /**
     * 设置ivaId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIvaId(JAXBElement<Integer> value) {
        this.ivaId = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setName(JAXBElement<String> value) {
        this.name = value;
    }

    /**
     * 获取netZoneId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNetZoneId() {
        return netZoneId;
    }

    /**
     * 设置netZoneId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNetZoneId(JAXBElement<Integer> value) {
        this.netZoneId = value;
    }

    /**
     * 获取port属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getPort() {
        return port;
    }

    /**
     * 设置port属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setPort(JAXBElement<Integer> value) {
        this.port = value;
    }

    /**
     * 获取strXmlRev属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStrXmlRev() {
        return strXmlRev;
    }

    /**
     * 设置strXmlRev属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStrXmlRev(JAXBElement<String> value) {
        this.strXmlRev = value;
    }

}
