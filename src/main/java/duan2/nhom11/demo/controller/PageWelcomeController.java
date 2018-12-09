package duan2.nhom11.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.service.CatagoryService;
import duan2.nhom11.demo.service.ProductService;
import duan2.nhom11.demo.service.UserSerive;

@Controller
public class PageWelcomeController {
  @Autowired 
  private UserSerive userService;
  
  @Autowired
  private CatagoryService categoryService;
  
  @Autowired
  private ProductService productService;
  
  
  
  @GetMapping(value = "/welcome")
	public String welcome(Model model, HttpServletRequest request) {
	 model.addAttribute("user", userService.count());
	 model.addAttribute("cate", categoryService.count());
	 model.addAttribute("product", productService.count());
	 User listt = userService.findByEmail1(request.getUserPrincipal().getName());
	 model.addAttribute("user1", listt);
		model.addAttribute("userinfo", listt);
	 return "employee/welcome";
	}
}
