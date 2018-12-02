package duan2.nhom11.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "image_product")
public class ImageProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "image_id")
	private Long image_id;

	@OneToOne
	@JoinColumn(name = "product_product_id")
	private Product product;
	


	
	@Column(name = "image_1", length = 50)
	private String image1;
	@Column(name = "image_2")
	private String image2;
	@Column(name = "image_3")
	private String image3;
	@Column(name = "image_4", length = 100)
	private String image4;

	public Long getImage_id() {
		return image_id;
	}

	public void setImage_id(Long image_id) {
		this.image_id = image_id;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	
	public ImageProduct(Long image_id, Product product, String image1, String image2, String image3,
			String image4) {
		super();
		this.image_id = image_id;
		this.product = product;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
	}

	
	

	public ImageProduct(String image1, String image2, String image3, String image4) {
		super();
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
	}

	public ImageProduct() {
		super();
	}


	

	

}