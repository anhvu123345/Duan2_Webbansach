package duan2.nhom11.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import duan2.nhom11.demo.entity.Product;
import duan2.nhom11.demo.service.CatagoryService;
import duan2.nhom11.demo.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CatagoryService catagoryService;
    
	@GetMapping(value = "/manager/product/list")
	public ModelAndView productList() {
		ModelAndView model = new ModelAndView();
		model.addObject("products", productService.findAll());
		model.setViewName("employee/productList");

		return model;
	}

	@GetMapping(value = "/manager/product/add")
	public String productAdd(Model model) {
		model.addAttribute("catagory", catagoryService.findAll());
		model.addAttribute("product", new Product());
		return "employee/productform";
	}

	@PostMapping(value = "/manager/product/save")
	public String productSave(@Valid Product product, Model model) {
		productService.save(product);
		return "redirect:/manager/product/list";
	}

	@GetMapping(value = "/manager/product/{id}/edit")
	public String productEdit(Model model, @PathVariable Long id) {
		model.addAttribute("catagory", catagoryService.findAll());
		model.addAttribute("product", productService.findById(id));
		return "employee/productform";
	}

	@GetMapping(value = "/manager/product/{id}/delete")
	public String productDelete(@PathVariable Long id) {
		productService.delete(id);
		return "redirect:/manager/product/list";
	}
}
