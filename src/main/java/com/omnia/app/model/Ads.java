package com.omnia.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ads")
public class Ads   implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
	@Lob
	@Column(name="asset")
	private byte[] asset ;
    //private String asset;
    
    private boolean showAtLOgin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private Company company;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getAsset() {
		return asset;
	}

	public void setAsset(byte[] asset) {
		this.asset = asset;
	}

	public boolean isShowAtLOgin() {
		return showAtLOgin;
	}

	public void setShowAtLOgin(boolean showAtLOgin) {
		this.showAtLOgin = showAtLOgin;
	}

	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
     private Date expirationDate;

    public Ads() {
    }

	public Ads(Long id, byte[] asset, boolean showAtLOgin, Company area, Date expirationDate) {
		super();
		this.id = id;
		this.asset = asset;
		this.showAtLOgin = showAtLOgin;
		this.company = area;
		this.expirationDate = expirationDate;
	}

	public Ads(byte[] asset, boolean showAtLOgin, Company area, Date expirationDate) {
		super();
		this.asset = asset;
		this.showAtLOgin = showAtLOgin;
		this.company = area;
		this.expirationDate = expirationDate;
	}
    


}
