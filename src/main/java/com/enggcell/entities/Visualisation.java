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
@Table(name = "visualisation")
@SequenceGenerator(name = "visualisation_seq", sequenceName = "visualisation_seq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "Visualisation.findAll", query = "SELECT r FROM Visualisation r")})
public class Visualisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visualisation_seq")
    @Column(name = "id")
    private Long id;
    
    @Size(max = 2147483647)
    @Column(name = "category")//govenment-health-scheme or government-education-scheme 
    private String category;
    
    @Size(max = 2147483647)
    @Column(name = "headline")
    private String headline;
    
    @Size(max = 2147483647)
    @Column(name = "link")
    private String link;
    
    @Size(max = 2147483647)
    @Column(name = "tableData")
    private String tableData;
    
    @Size(max = 2147483647)
    @Column(name = "detailedInfoPage")
    private String detailedInfoPage;
    
    @Column(name = "addedDate")
    private java.util.Date addedDate;
    
    public Visualisation() {
    }

    public Visualisation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDetailedInfoPage() {
		return detailedInfoPage;
	}

	public void setDetailedInfoPage(String detailedInfoPage) {
		this.detailedInfoPage = detailedInfoPage;
	}
	
	public String getTableData() {
		return tableData;
	}

	public void setTableData(String tableData) {
		this.tableData = tableData;
	}

	public java.util.Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(java.util.Date addedDate) {
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
        if (!(object instanceof Visualisation)) {
            return false;
        }
        Visualisation other = (Visualisation) object;
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
