package duan2.nhom11.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.Product;
import duan2.nhom11.demo.service.CatagoryService;
import duan2.nhom11.demo.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	private String saveDirectory = ".\\src\\main\\resources\\static\\images\\";

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
	public String productSave(@Valid Product product, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			byte[] bytes = file.getBytes();
			File dir = new File(saveDirectory);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String filename = file.getOriginalFilename();
			String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
			String newFileName = System.currentTimeMillis() + fileExtension;

			String fileSource = dir.getAbsolutePath() + File.separator + newFileName;
			File serverFile = new File(fileSource);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			product.setImage(newFileName);		
			productService.save(product);
			redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/manager/product/add";
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

	@GetMapping(value = "/images")
	@ResponseBody
	public byte[] imageSystems(@RequestParam("img") String img) throws IOException {
		File file = new File(saveDirectory + img);
		return Files.readAllBytes(file.toPath());
	}

}
