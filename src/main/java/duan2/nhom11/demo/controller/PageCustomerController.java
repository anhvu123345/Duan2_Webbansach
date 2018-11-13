package duan2.nhom11.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageCustomerController {

	@GetMapping(value="/index")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping(value="/mua-sam")
	public String muasam() {
		return "categories";
		
	}
	
	@GetMapping(value="/lien-he")
	public String lienhe() {
		return "contact";
	}
	@GetMapping(value="/chi-tiet-san-pham")
	public String chitiet() {
		return "single";
	}
}
