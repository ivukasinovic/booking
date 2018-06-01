package com.project.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Ivan V. on 07-May-18
 */
@XmlAccessorType(XmlAccessType.FIELD)

@XmlType
@XmlEnum
public enum Role {
    USER, AGENT, ADMIN
}
