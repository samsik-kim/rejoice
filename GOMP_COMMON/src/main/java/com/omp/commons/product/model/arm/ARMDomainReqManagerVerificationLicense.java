package com.omp.commons.product.model.arm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ARM XML 통신용 XML 표현 객체
 * @author pat
 *
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "reqManagerVerificationLicense")
public class ARMDomainReqManagerVerificationLicense {
	
	public List<ARMDevice> device = new ArrayList<ARMDevice>();
	public ARMDeviceItem limited = new ARMDeviceItem();

}
