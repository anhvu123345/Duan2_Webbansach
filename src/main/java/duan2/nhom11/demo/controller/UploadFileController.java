package duan2.nhom11.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.Product;
import duan2.nhom11.demo.service.ProductService;

@Controller
public class UploadFileController {
	@Autowired
	private ProductService productService;
	

	private String saveDirectory = ".\\src\\main\\resources\\static\\images\\";
	
	@GetMapping(value="/manager/{id}/upload")
	public String uploadImgae(Model model,@PathVariable Long id) {
		model.addAttribute("product", productService.findById(id) );
		return "employee/UploadImage";
	}
	@PostMapping(value="/manager/upload")
	public String upload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes, Product product) {
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
		}catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/manager/product/list";
	}
	
	@GetMapping(value="/image")
	@ResponseBody
	public byte[]  imageSystem(@RequestParam("img") String img) throws IOException {
		File file=new File(saveDirectory+img);
		return Files.readAllBytes(file.toPath());
	}
	
	
}
