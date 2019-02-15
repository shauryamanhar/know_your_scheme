/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author parentsgift
 */
@Entity
@Table(name = "centralGovernmentSchemesInHindi")
@SequenceGenerator(name = "centralGovernmentSchemesInHindi_seq", sequenceName = "centralGovernmentSchemesInHindi_seq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "CentralGovernmentSchemesInHindi.findAll", query = "SELECT r FROM CentralGovernmentSchemesInHindi r")})
public class CentralGovernmentSchemesInHindi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centralGovernmentSchemesInHindi_seq")
    @Column(name = "id")
    private Long id;
    
    @Size(max = 2147483647)
    @Column(name = "typeOfScheme")//Central Government Scheme or state name (eg. Chhattisgarh Government Scheme)
    private String typeOfScheme;
    
    @Size(max = 2147483647)
    @Column(name = "schemeCategory")
    private String schemeCategory;
    
    @Size(max = 2147483647)
    @Column(name = "schemeName")
    private String schemeName;
    
    @Size(max = 2147483647)
    @Column(name = "schemeLink")
    private String schemeLink;
    
    @Size(max = 2147483647)
    @Column(name = "overviewLink")
    private String overviewLink;
    
    @Size(max = 2147483647)
    @Column(name = "detailedInfo")
    private String detailedInfo;
    
    @Size(max = 2147483647)
    @Column(name = "didYouKnowLink")
    private String didYouKnowLink;
    
    @Size(max = 2147483647)
    @Column(name = "faqLink")
    private String faqLink;
    
    @Size(max = 2147483647)
    @Column(name = "relatedNewsLink")
    private String relatedNewsLink;
    
    @Size(max = 2147483647)
    @Column(name = "imageLink")
    private String imageLink;
    
    @Column(name = "addedDate")
    private Date addedDate;
    
    @Column(name = "actualLastModifiedDate")
    private Timestamp actualLastModifiedDate;
	
    public Timestamp getActualLastModifiedDate() {
		return actualLastModifiedDate;
	}

	public void setActualLastModifiedDate(Timestamp actualLastModifiedDate) {
		this.actualLastModifiedDate = actualLastModifiedDate;
	}
    
    public CentralGovernmentSchemesInHindi() {
    }

    public CentralGovernmentSchemesInHindi(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTypeOfScheme() {
		return typeOfScheme;
	}

	public void setTypeOfScheme(String typeOfScheme) {
		this.typeOfScheme = typeOfScheme;
	}

	public String getSchemeCategory() {
		return schemeCategory;
	}

	public void setSchemeCategory(String schemeCategory) {
		this.schemeCategory = schemeCategory;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeLink() {
		return schemeLink;
	}

	public void setSchemeLink(String schemeLink) {
		this.schemeLink = schemeLink;
	}

	public String getOverviewLink() {
		return overviewLink;
	}

	public void setOverviewLink(String overviewLink) {
		this.overviewLink = overviewLink;
	}
	
	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getDidYouKnowLink() {
		return didYouKnowLink;
	}

	public void setDidYouKnowLink(String didYouKnowLink) {
		this.didYouKnowLink = didYouKnowLink;
	}

	public String getFaqLink() {
		return faqLink;
	}

	public void setFaqLink(String faqLink) {
		this.faqLink = faqLink;
	}

	public String getRelatedNewsLink() {
		return relatedNewsLink;
	}

	public void setRelatedNewsLink(String relatedNewsLink) {
		this.relatedNewsLink = relatedNewsLink;
	}
	
	public String getDetailedInfo() {
		return detailedInfo;
	}

	public void setDetailedInfo(String detailedInfo) {
		this.detailedInfo = detailedInfo;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentralGovernmentSchemesInHindi)) {
            return false;
        }
        CentralGovernmentSchemesInHindi other = (CentralGovernmentSchemesInHindi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.enggcell.entities.Registrations[ id=" + id + " ]";
    }
    
}
