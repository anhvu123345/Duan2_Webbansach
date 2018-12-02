package duan2.nhom11.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long orderid;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@Column(name = "custormer_name", length = 30)
	private String custormerName;

	@Column(name = "custormer_email", length = 30)
	private String custormerEmail;

	@Column(name = "phone", length = 11)
	private String phone;

	@Column(name = "address", length = 50)
	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date date;

	@Column(name = "note")
	private String note;


	@ManyToOne
	@JoinColumn(name = "id",referencedColumnName="id" )
	private OrderDetail orderDetail;

	
	public Order() {
		super();
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public String getCustormerName() {
		return custormerName;
	}

	public void setCustormerName(String custormerName) {
		this.custormerName = custormerName;
	}

	public String getCustormerEmail() {
		return custormerEmail;
	}

	public void setCustormerEmail(String custormerEmail) {
		this.custormerEmail = custormerEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}