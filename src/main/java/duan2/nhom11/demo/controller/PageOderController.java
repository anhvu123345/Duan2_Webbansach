package duan2.nhom11.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import duan2.nhom11.demo.service.OrderSerive;

@Controller
public class PageOderController {

	@Autowired
	private OrderSerive orderservice;
	
	
	@GetMapping(value = "/manager/order/list")
	public ModelAndView orderList() {
		ModelAndView model = new ModelAndView();
		model.addObject("orders", orderservice.findAll());
		model.setViewName("employee/orderList");
		return model;
	}
	

}
