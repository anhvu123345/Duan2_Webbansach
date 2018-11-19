package duan2.nhom11.demo.controller;

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
import duan2.nhom11.demo.service.OrderSerive;
import duan2.nhom11.demo.service.UserSerive;


@Controller
public class OrderController {

	@Autowired
	private OrderSerive orderservice;
	
	@Autowired
	private UserSerive userservice;
	
	@GetMapping(value = "/manager/order/list")
	public ModelAndView orderList() {
		ModelAndView model = new ModelAndView();
		model.addObject("orders", orderservice.findAll());
		model.setViewName("employee/orderList");
		return model;
	}

	@GetMapping(value = "/manager/order/add")
	public String orderAdd(Model model) {
		model.addAttribute("user", userservice.findAll());
		model.addAttribute("order", new Product());
		return "employee/orderList";
	}

	@PostMapping(value = "/manager/order/save")
	public String orderSave(@Valid Order order, Model model) {
			
		orderservice.save(order);
	
		return "redirect:/manager/order/list";
	}



	@GetMapping(value = "/manager/order/{id}/delete")
	public String orderDelete(@PathVariable Long id) {
		orderservice.delete(id);
		return "redirect:/manager/order/list";
	}


}



