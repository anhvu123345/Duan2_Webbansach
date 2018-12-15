package duan2.nhom11.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import duan2.nhom11.demo.entity.Order;
import duan2.nhom11.demo.entity.Product;
import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.service.OrderDetailSerive;
import duan2.nhom11.demo.service.OrderSerive;
import duan2.nhom11.demo.service.UserSerive;


@Controller
public class OrderController {

	@Autowired
	private OrderSerive orderservice;
	
	@Autowired
	private OrderDetailSerive orderDetailSerive;
	
	@Autowired
	private UserSerive userService;

	
	@GetMapping(value = "/manager/order/list")
	public String orderList(Model model, HttpServletRequest request) {
		model.addAttribute("orders", orderservice.findAll());
		int id =  1;
		model.addAttribute("1",id);
		User listt = userService.findByEmail1(request.getUserPrincipal().getName());
		 model.addAttribute("user1", listt);
			model.addAttribute("userinfo", listt);
		return "employee/orderList";
	}

	
	@GetMapping(value = "/manager/order/{id}/delete")
	public String orderDelete(@PathVariable Long id) {
		orderservice.delete(id);
		return "redirect:/manager/order/list";
	}
	
	// xem chi tiết hóa đơn
	@GetMapping(value="/manager/order/{id}/chi-tiet")
	public String orderdeteails(Model model, @PathVariable Long id) {
		model.addAttribute("orderdetails", orderDetailSerive.finByOrderID(id));
		
		return "employee/order_chitiet";
	}
	
	
	//xác nhận đơn hàng
	@GetMapping(value="/manager/order/{id}/xac-nhan")
	public String xacnhan(@PathVariable Long id) {
		Order order = orderservice.findById(id).get();
		order.setXacnhan(true);
		orderservice.save(order);
		return "redirect:/manager/order/list";
	}


}