
package cn.zhny.ivs.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="args0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="args1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="args2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="args3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="args4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "args0",
    "args1",
    "args2",
    "args3",
    "args4"
})
@XmlRootElement(name = "userLogin", namespace = "http://webservice.cms.hikvision.com")
public class UserLogin {

    @XmlElementRef(name = "args0", namespace = "http://webservice.cms.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> args0;
    @XmlElementRef(name = "args1", namespace = "http://webservice.cms.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> args1;
    @XmlElementRef(name = "args2", namespace = "http://webservice.cms.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> args2;
    @XmlElement(namespace = "http://webservice.cms.hikvision.com")
    protected Integer args3;
    @XmlElementRef(name = "args4", namespace = "http://webservice.cms.hikvision.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> args4;

    /**
     * 获取args0属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getArgs0() {
        return args0;
    }

    /**
     * 设置args0属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setArgs0(JAXBElement<String> value) {
        this.args0 = value;
    }

    /**
     * 获取args1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getArgs1() {
        return args1;
    }

    /**
     * 设置args1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setArgs1(JAXBElement<String> value) {
        this.args1 = value;
    }

    /**
     * 获取args2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getArgs2() {
        return args2;
    }

    /**
     * 设置args2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setArgs2(JAXBElement<String> value) {
        this.args2 = value;
    }

    /**
     * 获取args3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getArgs3() {
        return args3;
    }

    /**
     * 设置args3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setArgs3(Integer value) {
        this.args3 = value;
    }

    /**
     * 获取args4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getArgs4() {
        return args4;
    }

    /**
     * 设置args4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setArgs4(JAXBElement<String> value) {
        this.args4 = value;
    }

}
