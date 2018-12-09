package duan2.nhom11.demo.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "catagory")
public class Catagory  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "catagory_id")
	private Long catagoryid;
	
	

	@Column(name = "catagory_name")
	private String catagoryName;

	@OneToMany(mappedBy = "catagory", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Product> product;

	public Catagory() {
		super();
	}

	public Long getCatagoryid() {
		return catagoryid;
	}

	public void setCatagoryid(Long catagoryid) {
		this.catagoryid = catagoryid;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
	