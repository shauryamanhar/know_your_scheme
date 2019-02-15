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
@Table(name = "forgetPasswordTempUser")
@SequenceGenerator(name = "forgetPasswordTempUser_seq", sequenceName = "forgetPasswordTempUser_seq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "ForgetPasswordTempUser.findAll", query = "SELECT r FROM ForgetPasswordTempUser r")})
public class ForgetPasswordTempUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forgetPasswordTempUser_seq")
    @Column(name = "id")
    private Long id;
    
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    
    @Size(max = 2147483647)
    @Column(name = "username")
    private String username;
    
    @Size(max = 2147483647)
    @Column(name = "uniqueResetPasswordID")
    private String uniqueResetPasswordID;
    
    @Size(max = 2147483647)
    @Column(name = "hashGeneratedUniqueResetPasswordID")
    private String hashGeneratedUniqueResetPasswordID;
    
    @Column(name = "addedDateTime")
    private Timestamp addedDateTime;
    
    public ForgetPasswordTempUser() {
    }

    public ForgetPasswordTempUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUniqueResetPasswordID() {
		return uniqueResetPasswordID;
	}

	public void setUniqueResetPasswordID(String uniqueResetPasswordID) {
		this.uniqueResetPasswordID = uniqueResetPasswordID;
	}

	public String getHashGeneratedUniqueResetPasswordID() {
		return hashGeneratedUniqueResetPasswordID;
	}

	public void setHashGeneratedUniqueResetPasswordID(
			String hashGeneratedUniqueResetPasswordID) {
		this.hashGeneratedUniqueResetPasswordID = hashGeneratedUniqueResetPasswordID;
	}

	public Timestamp getAddedDateTime() {
		return addedDateTime;
	}

	public void setAddedDateTime(Timestamp addedDateTime) {
		this.addedDateTime = addedDateTime;
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
        if (!(object instanceof ForgetPasswordTempUser)) {
            return false;
        }
        ForgetPasswordTempUser other = (ForgetPasswordTempUser) object;
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
