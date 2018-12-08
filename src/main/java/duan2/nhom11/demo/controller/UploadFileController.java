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

import duan2.nhom11.demo.entity.ImageProduct;
import duan2.nhom11.demo.entity.Multipartfile;
import duan2.nhom11.demo.service.ImageProductService;
import duan2.nhom11.demo.service.ProductService;

@Controller
public class UploadFileController {

	@Autowired
	private ImageProductService imageProductService;
	
	@Autowired
	private ProductService productService;
	
	private String saveDirectory = ".\\src\\main\\resources\\static\\images\\";
	
	@GetMapping(value="/manager/{id}/upload")
	public String uploadImgae(Model model,@PathVariable Long id) {
		model.addAttribute("imageproducts", imageProductService.findByProduct(id));
		model.addAttribute("imageproduct", imageProductService.findByProductid(id));
		model.addAttribute("idproduct", productService.findByIdProduct(id));
		return "employee/UploadImage";
	}
	
	@PostMapping(value="/manager/upload1")
	public String upload1(RedirectAttributes redirectAttributes, ImageProduct imageproduct,  Multipartfile multipart) throws IOException {
			MultipartFile[] files = multipart.getFilea();
			BufferedOutputStream stream4;
			byte[] bytes = files[0].getBytes();
			
			File dir = new File(saveDirectory);
			if (!dir.exists()) {
					dir.mkdirs();
			}
					System.out.println("cde");
					String fileSource1 = dir.getAbsolutePath() + File.separator + imageproduct.getImage1();
					File serverFile1 = new File(fileSource1);
						 stream4 = new BufferedOutputStream(new FileOutputStream(serverFile1));
						stream4.write(bytes);
						stream4.close();
		return "redirect:/manager/product/list";
	}
	
	@PostMapping(value="/manager/upload2")
	public String upload2(RedirectAttributes redirectAttributes, ImageProduct imageproduct, Multipartfile multipart) throws IOException {
		MultipartFile[] files = multipart.getFileb();
		BufferedOutputStream stream4;
		byte[] bytes = files[0].getBytes();
		
		File dir = new File(saveDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}	
		
		System.out.println("cde");
		String fileSource1 = dir.getAbsolutePath() + File.separator + imageproduct.getImage2();
		File serverFile1 = new File(fileSource1);
	 stream4 = new BufferedOutputStream(new FileOutputStream(serverFile1));
	stream4.write(bytes);
	stream4.close();
		return "redirect:/manager/product/list";
	}
	@PostMapping(value="/manager/upload3")
	public String upload3(RedirectAttributes redirectAttributes, ImageProduct imageproduct, Multipartfile multipart) throws IOException {
		MultipartFile[] files = multipart.getFilec();
		BufferedOutputStream stream4;
		byte[] bytes = files[0].getBytes();
		
		File dir = new File(saveDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}	
		
		System.out.println("cde");
		String fileSource1 = dir.getAbsolutePath() + File.separator + imageproduct.getImage3();
		File serverFile1 = new File(fileSource1);
	 stream4 = new BufferedOutputStream(new FileOutputStream(serverFile1));
	stream4.write(bytes);
	stream4.close();
		return "redirect:/manager/product/list";
	}
	@PostMapping(value="/manager/upload4")
	public String upload4(RedirectAttributes redirectAttributes, ImageProduct imageproduct, Multipartfile multipart) throws IOException {
		MultipartFile[] files = multipart.getFiled();
		BufferedOutputStream stream4;
		byte[] bytes = files[0].getBytes();
		
		File dir = new File(saveDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}	
		
		
		System.out.println("cde");
		String fileSource1 = dir.getAbsolutePath() + File.separator + imageproduct.getImage4();
		File serverFile1 = new File(fileSource1);
	 stream4 = new BufferedOutputStream(new FileOutputStream(serverFile1));
	stream4.write(bytes);
	stream4.close();
	
		
		return "redirect:/manager/product/list";
	
	}
	@GetMapping(value="/image")
	@ResponseBody
	public byte[]  imageSystem(@RequestParam("img") String img) throws IOException {
		File file=new File(saveDirectory+img);
		return Files.readAllBytes(file.toPath());
	}
	

	
}
