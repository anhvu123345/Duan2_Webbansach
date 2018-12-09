package duan2.nhom11.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userid;

    @Column(name = "role", length = 20)
    private String role;

    @Email(message="Không đúng định dạng")
    @NotEmpty(message= "Không được Trống")
    @Column(name = "email", length = 100)
    private String email;

    @NotEmpty(message= "Không được Trống")
    @Column(name = "fullname", length = 30)
    private String fullname;

    @NotEmpty(message= "Không được Trống")
    @Column(name = "fristname", length = 20)
    private String fristname;

    @NotEmpty(message= "Không được Trống")
    @Column(name = "password", length = 300)
    private String password;

    @Column(name = "active", length = 1)
    private boolean active;

    @Column(name = "token", length = 300)
    private String token;
    
    @Column(name = "image_user", length = 300)
    private String imageUser;

//	quan he 1-n, noi bang user vs bang order
    @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
    private List<Order> Order;

//	quan he 1-n, noi bang user vs bang order
    @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
    private List<OrderDetail> OrderDetail;

    
    
    public String getImageUser() {
		return imageUser;
	}

	public void setImageUser(String imageUser) {
		this.imageUser = imageUser;
	}

	public List<Order> getOrder() {
		return Order;
	}

	public void setOrder(List<Order> order) {
		Order = order;
	}

	public User() {
	super();
    }

    public Long getUserid() {
	return userid;
    }

    public void setUserid(Long userid) {
	this.userid = userid;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getFullname() {
	return fullname;
    }

    public void setFullname(String fullname) {
	this.fullname = fullname;
    }

    public String getFristname() {
	return fristname;
    }

    public void setFristname(String fristname) {
	this.fristname = fristname;
    }

    @Transient
    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public List<Order> getOder() {
	return Order;
    }

    public void setOder(List<Order> Order) {
	this.Order = Order;
    }

    public List<OrderDetail> getOrderDetail() {
	return OrderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
	OrderDetail = orderDetail;
    }

}
