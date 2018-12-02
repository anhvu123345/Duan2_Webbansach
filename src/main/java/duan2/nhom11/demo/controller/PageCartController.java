package duan2.nhom11.demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.OrderDetail;
import duan2.nhom11.demo.entity.Product;
import duan2.nhom11.demo.service.OrderDetailSerive;
import duan2.nhom11.demo.service.ProductService;

@Controller
public class PageCartController {

	@Autowired
	private OrderDetailSerive orderDetailSerive;
	
	@Autowired
	private ProductService  productService;
	
	@GetMapping(value="/cart")
	public String caerr(Model model) {
		model.addAttribute("cart", new OrderDetail());
		return "custormer/giohang";
	}
	@PostMapping(value="/save/cart")
	public ModelAndView addCart(Model model,@Valid OrderDetail cart, RedirectAttributes redirectAttributes) {	
		ModelAndView modelview = new ModelAndView();
		if(cart.getUser().getUserid() ==null) {
			modelview.setViewName("/login");
		}else {
			 cart.setIntoMoney(cart.getQuantity() * cart.getIntoMoney());
		       
				orderDetailSerive.save(cart);				
				redirectAttributes.addFlashAttribute("giohang","Đã thêm thành công vào giỏ hàng");
				modelview.setViewName("redirect:/chi-tiet-san-pham/" + cart.getProduct().getProductid()+ "");
		}
       
		
		return  modelview;
	}
	@GetMapping(value="/")
	public String update() {
		return "";
	
}
}
