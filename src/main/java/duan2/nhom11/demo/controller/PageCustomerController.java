package duan2.nhom11.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import duan2.nhom11.demo.entity.OrderDetail;
import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.service.CatagoryService;
import duan2.nhom11.demo.service.ProductService;
import duan2.nhom11.demo.service.UserSerive;

@Controller
public class PageCustomerController {
	@Autowired
	private ProductService productService;

	@Autowired
	private UserSerive userService;
	
	@Autowired
	private CatagoryService catagoryService;

	
	// controller cho người dùng đã có tài khoản
	
	// trang chủ
	@GetMapping(value = "/index")
	public String index(Model model, HttpServletRequest request) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("user", userService.findAll());
		model.addAttribute("cate", catagoryService.findAll());
		User listt = userService.findByEmail1(request.getUserPrincipal().getName());
		model.addAttribute("user1", listt);
		model.addAttribute("userinfo", listt);
		return "custormer/index";
	}
	
	
	// hiển thị danh sách sản phẩm
	@GetMapping(value="/mua-sam")
	public String muasam(Model model, HttpServletRequest request) {
	   // lấy ra danh sách sản phầm
		model.addAttribute("products", productService.findAll());
		//lấy ra danh sách người dùng 
		model.addAttribute("user", userService.findAll());
		// lấy ra danh sách loại sách
		model.addAttribute("cate", catagoryService.findAll());
		// tìm user theo email 
		 User listt = userService.findByEmail1(request.getUserPrincipal().getName());
		 model.addAttribute("user1", listt);
		 model.addAttribute("userinfo", listt);
		 // trả về tràng mua sắm sản phẩm
		return "custormer/home";
	}
	
	// hiển thị sản phẩm theo danh muc (user có tài khoản)
	@GetMapping(value="danh-muc/{id}")
	public String danhmuc(Model model, @PathVariable Long id, HttpServletRequest request) {
		model.addAttribute("products", productService.findByIdCategory(id));
		model.addAttribute("user", userService.findAll());
		model.addAttribute("cate", catagoryService.findAll());
		 User listt = userService.findByEmail1(request.getUserPrincipal().getName());
		 model.addAttribute("user1", listt);
		 model.addAttribute("userinfo", listt);
		return "custormer/categories";
	}

	// chi tiết sản phẩm
	@GetMapping(value = "/chi-tiet-san-pham/{id}")
	public String chitiet(Model model, @PathVariable Long id, HttpServletRequest request) {
		model.addAttribute("cart", new OrderDetail());
		model.addAttribute("user", userService.findAll());
		model.addAttribute("product", productService.findById(id).get());
		 User listt = userService.findByEmail1(request.getUserPrincipal().getName());
		 model.addAttribute("user1", listt);
		 model.addAttribute("userinfo", listt);
		return "custormer/single";
	}

	@GetMapping(value="/index/lien-he")
	public String lienhe123(Model model, HttpServletRequest request) {
		model.addAttribute("user", userService.findAll());
		 User listt = userService.findByEmail1(request.getUserPrincipal().getName());
		 model.addAttribute("user1", listt);
		 model.addAttribute("userinfo", listt);
		return "custormer/contact";
	}
	
	
	
    // =============== Phần cho user không có tài khoản =================================================
	
	//
	@GetMapping(value="/home")
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("cate", catagoryService.findAll());
		return "index";
	}
	
	@GetMapping(value="/san-pham")
	public String sanPham(Model model, HttpServletRequest request) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("cate", catagoryService.findAll());
		return "categories";
	}
	@GetMapping(value="/{id}/danh-muc")
	public String danhmucNOUser(Model model, HttpServletRequest request, @PathVariable Long id) {
		
		model.addAttribute("products", productService.findByIdCategory(id));
		model.addAttribute("cate", catagoryService.findAll());
		return "categories";
	}
	
	// trang thong tin chi tiet cho nguoi khong co san pham 
	@GetMapping(value="/thong-tin-san-pham/{id}")
	public String thongtin(Model model, HttpServletRequest request, @PathVariable Long id) {
		model.addAttribute("product", productService.findById(id).get());
		return "single";
	}
	
	// trang liên hệ
	@GetMapping(value = "/lien-he")
	public String lienhe(Model model, HttpServletRequest request) {		
		return "contact";
	}

}
