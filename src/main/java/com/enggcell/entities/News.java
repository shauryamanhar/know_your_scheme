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
@Table(name = "news")
@SequenceGenerator(name = "news_seq", sequenceName = "news_seq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT r FROM News r")})
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_seq")
    @Column(name = "id")
    private Long id;
    
    @Size(max = 2147483647)
    @Column(name = "typeOfNews")//govenment-health-scheme or government-education-scheme 
    private String typeOfNews;
    
    @Size(max = 2147483647)
    @Column(name = "newsHeadline")
    private String newsHeadline;
    
    @Size(max = 2147483647)
    @Column(name = "newsSummary")
    private String newsSummary;
    
    @Size(max = 2147483647)
    @Column(name = "newsLink")
    private String newsLink;
    
    @Size(max = 2147483647)
    @Column(name = "detailedInfoPage")
    private String detailedInfoPage;
    
    @Size(max = 2147483647)
    @Column(name = "imageUrl")
    private String imageUrl;
    
    @Column(name = "newsDate")
    private Timestamp newsDate;
    
    @Column(name = "addedDate")
    private Date addedDate;
    
    public News() {
    }

    public News(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getTypeOfNews() {
		return typeOfNews;
	}

	public void setTypeOfNews(String typeOfNews) {
		this.typeOfNews = typeOfNews;
	}

	public String getNewsHeadline() {
		return newsHeadline;
	}

	public void setNewsHeadline(String newsHeadline) {
		this.newsHeadline = newsHeadline;
	}

	public String getNewsSummary() {
		return newsSummary;
	}

	public void setNewsSummary(String newsSummary) {
		this.newsSummary = newsSummary;
	}

	public String getNewsLink() {
		return newsLink;
	}

	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}

	public String getDetailedInfoPage() {
		return detailedInfoPage;
	}

	public void setDetailedInfoPage(String detailedInfoPage) {
		this.detailedInfoPage = detailedInfoPage;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Timestamp getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Timestamp newsDate) {
		this.newsDate = newsDate;
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
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
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
