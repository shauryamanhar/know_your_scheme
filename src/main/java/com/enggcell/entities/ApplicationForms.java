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
@Table(name = "applicationForms")
@SequenceGenerator(name = "applicationForms_seq", sequenceName = "applicationForms_seq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "ApplicationForms.findAll", query = "SELECT r FROM ApplicationForms r")})
public class ApplicationForms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicationForms_seq")
    @Column(name = "id")
    private Long id;
    
    @Size(max = 2147483647)
    @Column(name = "applicationHeadline")
    private String applicationHeadline;
    
    @Size(max = 2147483647)
    @Column(name = "detailedInfoPage")
    private String detailedInfoPage;
    
    @Size(max = 2147483647)
    @Column(name = "applicationPageLink")
    private String applicationPageLink;
    
    @Column(name = "addedDate")
    private Date addedDate;
    
    @Size(max = 2147483647)
    @Column(name = "updatedDate")
    private String updatedDate;
    
    @Size(max = 2147483647)
    @Column(name = "oldNew")
    private String oldNew;
    
    public String getOldNew() {
		return oldNew;
	}

	public void setOldNew(String oldNew) {
		this.oldNew = oldNew;
	}
    
    @Size(max = 2147483647)
    @Column(name = "videoLinks")
    private String videoLinks;
    
    @Column(name = "actualLastModifiedDate")
    private Timestamp actualLastModifiedDate;
	
    public Timestamp getActualLastModifiedDate() {
		return actualLastModifiedDate;
	}

	public void setActualLastModifiedDate(Timestamp actualLastModifiedDate) {
		this.actualLastModifiedDate = actualLastModifiedDate;
	}
	
    public String getVideoLinks() {
		return videoLinks;
	}

	public void setVideoLinks(String videoLinks) {
		this.videoLinks = videoLinks;
	}
    
    public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public ApplicationForms() {
    }

    public ApplicationForms(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	public String getApplicationHeadline() {
		return applicationHeadline;
	}

	public void setApplicationHeadline(String applicationHeadline) {
		this.applicationHeadline = applicationHeadline;
	}

	public String getDetailedInfoPage() {
		return detailedInfoPage;
	}

	public void setDetailedInfoPage(String detailedInfoPage) {
		this.detailedInfoPage = detailedInfoPage;
	}

	public String getApplicationPageLink() {
		return applicationPageLink;
	}

	public void setApplicationPageLink(String applicationPageLink) {
		this.applicationPageLink = applicationPageLink;
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
        if (!(object instanceof ApplicationForms)) {
            return false;
        }
        ApplicationForms other = (ApplicationForms) object;
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
