//package com.omnia.app.model;
//
//import java.io.Serializable;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//
//@Entity
//@Table(name = "compani")
//public class Companies implements Serializable  {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    
//    @Lob 
//    @Column(name="logo")
//    private byte[] logo;
//    private  String phone ;
//    private  String email;
//    private String address;
//    private String facebook;
//    private String instagram;
//    @Column(unique = true)
//    private String isbn;
//
//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SELECT)
//    private Set<Ads> ad;
//    
//    
//    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SELECT)
//    //@JsonManagedReference
//    private Set<Area> area;
//
//    public Companies() {
//    }
//
//    public Companies(String name, byte[] logo, String isbn) {
//        this.name = name;
//        this.logo = logo;
//        this.isbn = isbn;
//    }
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public byte[] getLogo() {
//		return logo;
//	}
//
//	public void setLogo(byte[] logo) {
//		this.logo = logo;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getFacebook() {
//		return facebook;
//	}
//
//	public void setFacebook(String facebook) {
//		this.facebook = facebook;
//	}
//
//	public String getInstagram() {
//		return instagram;
//	}
//
//	public void setInstagram(String instagram) {
//		this.instagram = instagram;
//	}
//
//	public String getIsbn() {
//		return isbn;
//	}
//
//	public void setIsbn(String isbn) {
//		this.isbn = isbn;
//	}
//
//	public Set<Ads> getAd() {
//		return ad;
//	}
//
//	public void setAd(Set<Ads> ad) {
//		this.ad = ad;
//	}
//
//	public Set<Area> getArea() {
//		return area;
//	}
//
//	public void setArea(Set<Area> area) {
//		this.area = area;
//	}
//
//	public Companies(Long id, String name, byte[] logo, String phone, String email, String address, String facebook,
//			String instagram, String isbn, Set<Ads> ad, Set<Area> area) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.logo = logo;
//		this.phone = phone;
//		this.email = email;
//		this.address = address;
//		this.facebook = facebook;
//		this.instagram = instagram;
//		this.isbn = isbn;
//		this.ad = ad;
//		this.area = area;
//	}
//
//	public Companies(String name, byte[] logo, String phone, String email, String address, String facebook,
//			String instagram, String isbn, Set<Ads> ad, Set<Area> area) {
//		super();
//		this.name = name;
//		this.logo = logo;
//		this.phone = phone;
//		this.email = email;
//		this.address = address;
//		this.facebook = facebook;
//		this.instagram = instagram;
//		this.isbn = isbn;
//		this.ad = ad;
//		this.area = area;
//	}
//
//	
//
//
//}
