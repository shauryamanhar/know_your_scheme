/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enggcell.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "registrations")
@SequenceGenerator(name = "registrations_seq", sequenceName = "registrations_seq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "Registrations.findAll", query = "SELECT r FROM Registrations r")})
public class Registrations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registrations_seq")
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "branch")
    private String branch;
    @Size(max = 2147483647)
    @Column(name = "fullName")
    private String fullName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    
    @Size(max = 2147483647)
    @Column(name = "address")
    private String address;
    @Size(max = 2147483647)
    @Column(name = "date")
    private String date;
	@Size(max = 2147483647)
    @Column(name = "college")
    private String college;
	@Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "day")
    private BigInteger day;
    @Column(name = "month")
    private BigInteger month;
    @Column(name = "year")
    private BigInteger year;
    @Size(max = 2147483647)
    @Column(name = "gender")
    private String gender;
    @Size(max = 2147483647)
    @Column(name = "panOraadhar")
    private String panOraadhar;
    @Size(max = 2147483647)
    @Column(name = "city")
    private String city;
    @Size(max = 2147483647)
    @Column(name = "currentCity")
    private String currentCity;
    @Size(max = 2147483647)
    @Column(name = "companies")
    private String companies;
    @Size(max = 2147483647)
    @Column(name = "passingyear")
    private String passingyear;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;
    @Size(max = 2147483647)
    @Column(name = "state")
    private String state;
    @Size(max = 2147483647)
    @Column(name = "regId")
    private String regId;
    @Size(max = 2147483647)
    @Column(name = "filename")
    private String filename;

    public Registrations() {
    }

    public Registrations(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
    
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getAddress() {
		return address;
	}

    
	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigInteger getDay() {
        return day;
    }

    public void setDay(BigInteger day) {
        this.day = day;
    }

    public BigInteger getMonth() {
        return month;
    }

    public void setMonth(BigInteger month) {
        this.month = month;
    }

    public BigInteger getYear() {
        return year;
    }

    public void setYear(BigInteger year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPanOraadhar() {
        return panOraadhar;
    }

    public void setPanOraadhar(String panOraadhar) {
        this.panOraadhar = panOraadhar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompanies() {
        return companies;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public String getPassingyear() {
        return passingyear;
    }

    public void setPassingyear(String passingyear) {
        this.passingyear = passingyear;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
        if (!(object instanceof Registrations)) {
            return false;
        }
        Registrations other = (Registrations) object;
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
