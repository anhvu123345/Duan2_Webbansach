package duan2.nhom11.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import duan2.nhom11.demo.entity.Catagory;
import duan2.nhom11.demo.service.CatagoryService;

@Controller
public class CatagoryController {

	@Autowired
	private CatagoryService catagoryService;

	@GetMapping(value = "/manager/catagory/list")
	public String CataList(Model model) {
		model.addAttribute("catagorys", catagoryService.findAll());
		return "employee/catagoryList";
	}

	@GetMapping(value = "/manager/catagory/add")
	public String CataAdd(Model model) {
		model.addAttribute("catagory", new Catagory());
		return "employee/catagoryform";
	}

	@PostMapping(value = "/manager/catagory/save")
	public String CataSave(@Valid Catagory catagory) {
		catagoryService.save(catagory);
		return "redirect:/manager/catagory/list";
	}

	@GetMapping(value = "/manager/catagory/{id}/edit")
	public String cataEdit(@PathVariable Long id, Model model) {
		model.addAttribute("catagory", catagoryService.findById(id));
		return "employee/catagoryform";
	}

	@GetMapping(value = "/manager/catagory/{id}/delete")
	public String cataDelete(@PathVariable Long id) {
		catagoryService.delete(id);
		return "redirect:/manager/catagory/list";
	}

}
