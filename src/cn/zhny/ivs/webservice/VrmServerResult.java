
package cn.zhny.ivs.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>VrmServerResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="VrmServerResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="vrmServerDTOArray" type="{http://simple.bo.webservice.cms.hikvision.com/xsd}VrmServerDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VrmServerResult", namespace = "http://simple.bo.webservice.cms.hikvision.com/xsd", propOrder = {
    "errorCode",
    "result",
    "vrmServerDTOArray"
})
public class VrmServerResult {

    protected Integer errorCode;
    protected Boolean result;
    @XmlElement(nillable = true)
    protected List<VrmServerDTO2> vrmServerDTOArray;

    /**
     * 获取errorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * 设置errorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setErrorCode(Integer value) {
        this.errorCode = value;
    }

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResult(Boolean value) {
        this.result = value;
    }

    /**
     * Gets the value of the vrmServerDTOArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vrmServerDTOArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVrmServerDTOArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VrmServerDTO2 }
     * 
     * 
     */
    public List<VrmServerDTO2> getVrmServerDTOArray() {
        if (vrmServerDTOArray == null) {
            vrmServerDTOArray = new ArrayList<VrmServerDTO2>();
        }
        return this.vrmServerDTOArray;
    }

}
