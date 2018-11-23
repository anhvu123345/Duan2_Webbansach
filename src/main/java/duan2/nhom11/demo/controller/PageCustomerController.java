package duan2.nhom11.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import duan2.nhom11.demo.service.ProductService;

@Controller
public class PageCustomerController {
	@Autowired
	private ProductService productService;
	
	

	@GetMapping(value="/index")
	public String index(Model model) {
		model.addAttribute("products", productService.findAll());
		return "custormer/home";
	}
	
	@GetMapping(value="/chi-tiet-san-pham/{id}")
	public String chitiet(Model model,@PathVariable Long id) {
		model.addAttribute("product", productService.findById(id).get());
		return "custormer/single";
	}
     
	
	
	@GetMapping(value="/lien-he")
	public String lienhe() {
		return "contact";
	}
	
}
