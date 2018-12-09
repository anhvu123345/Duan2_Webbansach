package duan2.nhom11.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.Catagory;
import duan2.nhom11.demo.entity.ImageProduct;
import duan2.nhom11.demo.entity.Product;
import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.service.CatagoryService;
import duan2.nhom11.demo.service.ImageProductService;
import duan2.nhom11.demo.service.ProductService;
import duan2.nhom11.demo.service.UserSerive;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserSerive userSerivce;
    
    @Autowired
    private ImageProductService imageProductService;

    private String saveDirectory = ".\\src\\main\\resources\\static\\images\\";

    @Autowired
    private CatagoryService catagoryService;

    @GetMapping(value = "/manager/product/list")
    public String productList(Model model, HttpServletRequest request, RedirectAttributes redirect) {
	request.getSession().setAttribute("employeelist", null);
	if (model.asMap().get("success") != null)
	    redirect.addFlashAttribute("success", model.asMap().get("success").toString());
	return "redirect:/manager/product/page/1";
    }

    @GetMapping("/manager/product/page/{pageNumber}")
    public String showEmployeePage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
	PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeelist");
	int pagesize = 8;
	List<Product> list = (List<Product>) productService.findAll();
	System.out.println(list.size());
	if (pages == null) {
	    pages = new PagedListHolder<>(list);
	    pages.setPageSize(pagesize);
	} else {
	    final int goToPage = pageNumber - 1;
	    if (goToPage <= pages.getPageCount() && goToPage >= 0) {
		pages.setPage(goToPage);
	    }
	}
	request.getSession().setAttribute("employeelist", pages);
	int current = pages.getPage() + 1;
	int begin = Math.max(1, current - list.size());
	int end = Math.min(begin + 5, pages.getPageCount());
	int totalPageCount = pages.getPageCount();
	String baseUrl = "/manager/product/page/";

	model.addAttribute("beginIndex", begin);
	model.addAttribute("endIndex", end);
	model.addAttribute("currentIndex", current);
	model.addAttribute("totalPageCount", totalPageCount);
	model.addAttribute("baseUrl", baseUrl);
	model.addAttribute("ListProduct", pages);
	model.addAttribute("search", true);
	 User listt = userSerivce.findByEmail1(request.getUserPrincipal().getName());
	 model.addAttribute("user1", listt);
	 model.addAttribute("userinfo", listt);
	return "employee/productList";
    }

    @GetMapping(value = "/manager/product/add")
    public String productAdd(Model model, HttpServletRequest request) {
	model.addAttribute("catagory", catagoryService.findAll());
	model.addAttribute("product", new Product());
	model.addAttribute("search", true);
	 User listt = userSerivce.findByEmail1(request.getUserPrincipal().getName());
	 model.addAttribute("user1", listt);
	 model.addAttribute("userinfo", listt);
	return "employee/productform";
    }

    @PostMapping(value = "/manager/product/save")
    public String productSave(@Valid Product product, @RequestParam("pro-image") MultipartFile[] files,
	    RedirectAttributes redirectAttributes, ImageProduct imageProduct) throws IOException {
    
	List<String> listname = new ArrayList<String>();
	byte[] bytes;
	BufferedOutputStream stream;
	String newFileName;
	String fileExtension;
	String filename;
	String fileSource;
	File dir = new File(saveDirectory);
	if (!dir.exists()) {
	    dir.mkdirs();
	}
	
	else if (files[0].isEmpty() == true) {
	    System.out.println("them tc");
	    imageProduct.setImage1(System.currentTimeMillis()+ "1" + ".jpg");
	    imageProduct.setImage2(System.currentTimeMillis()+ "2" + ".jpg");
	    imageProduct.setImage3(System.currentTimeMillis()+ "3" + ".jpg");
	    imageProduct.setImage4(System.currentTimeMillis()+ "4" + ".jpg");
	    imageProduct.setProduct(product);
	    productService.save(product);
	    imageProductService.saveAndFlush(imageProduct);
	    redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
	}

	
	else if (files.length == 4) {
		   System.out.println("them tc");
	    for (MultipartFile file : files) {
		bytes = file.getBytes();
		filename = file.getOriginalFilename();
		fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
		newFileName = System.currentTimeMillis() + fileExtension;

		listname.add(newFileName);
		fileSource = dir.getAbsolutePath() + File.separator + newFileName;
		File serverFile = new File(fileSource);
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
	    }

	    imageProduct.setImage1(listname.get(0));
	    imageProduct.setImage2(listname.get(1));
	    imageProduct.setImage3(listname.get(2));
	    imageProduct.setImage4(listname.get(3));

	    System.out.println(listname.size());
	    imageProduct.setProduct(product);
	    productService.save(product);
	    imageProductService.saveAndFlush(imageProduct);
	    redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
	}
	else if (files.length == 3) {
	    System.out.println("them loi3");
	    for (MultipartFile file : files) {
		bytes = file.getBytes();
		filename = file.getOriginalFilename();
		fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
		newFileName = System.currentTimeMillis() + fileExtension;

		listname.add(newFileName);
		fileSource = dir.getAbsolutePath() + File.separator + newFileName;
		File serverFile = new File(fileSource);
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
	    }

	    imageProduct.setImage1(listname.get(0));
	    imageProduct.setImage2(listname.get(1));
	    imageProduct.setImage3(listname.get(2));
	    imageProduct.setImage4(System.currentTimeMillis() +"4" + ".jpg");
	    imageProduct.setProduct(product);
	    productService.save(product);
	    imageProductService.saveAndFlush(imageProduct);
	    redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
	}
	else if (files.length == 2) {
	    System.out.println("them loi2");
	    for (MultipartFile file : files) {
		bytes = file.getBytes();
		filename = file.getOriginalFilename();
		fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
		newFileName = System.currentTimeMillis() + fileExtension;

		listname.add(newFileName);
		fileSource = dir.getAbsolutePath() + File.separator + newFileName;
		File serverFile = new File(fileSource);
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
	    }
	    imageProduct.setImage1(listname.get(0));
	    imageProduct.setImage2(listname.get(1));
	    imageProduct.setImage3(System.currentTimeMillis() +"3" + ".jpg");
	    imageProduct.setImage4(System.currentTimeMillis() +"4" + ".jpg");
	    imageProduct.setProduct(product);
	    productService.save(product);
	    imageProductService.saveAndFlush(imageProduct);
	    redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
	}
	else if (files[0].isEmpty() != true) {
	    System.out.println("them loi1");
	    for (MultipartFile file : files) {
		bytes = file.getBytes();
		filename = file.getOriginalFilename();
		fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
		newFileName = System.currentTimeMillis() + fileExtension;

		listname.add(newFileName);
		fileSource = dir.getAbsolutePath() + File.separator + newFileName;
		File serverFile = new File(fileSource);
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
	    }
	    imageProduct.setImage1(listname.get(0));
	    imageProduct.setImage2(System.currentTimeMillis() +"2" + ".jpg");
	    imageProduct.setImage3(System.currentTimeMillis() +"3" + ".jpg");
	    imageProduct.setImage4(System.currentTimeMillis() +"4" + ".jpg");
	    imageProduct.setProduct(product);
	    productService.save(product);
	    imageProductService.saveAndFlush(imageProduct);
	    redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
	}
	return "redirect:/manager/product/add";
    }

    @GetMapping(value = "/manager/product/{id}/edit")
    public String productEdit(Model model, @PathVariable Long id, HttpServletRequest request) {
	model.addAttribute("catagory", catagoryService.findAll());
	model.addAttribute("product", productService.findById(id));
	model.addAttribute("search", true);
	 User listt = userSerivce.findByEmail1(request.getUserPrincipal().getName());
	 model.addAttribute("user1", listt);
	 model.addAttribute("userinfo", listt);
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

    @GetMapping("/manager/product/search/{pageNumber}")
    public String search(@RequestParam("s") String s, Model model, HttpServletRequest request,
	    @PathVariable int pageNumber) {
	if (s.equals("")) {
	    return "redirect:/manager/product/page/1";
	}
	List<Product> list = productService.search(s);
	if (list == null) {
	    return "redirect:/manager/product/page/1";
	}
	PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeelist");
	int pagesize = 8;

	pages = new PagedListHolder<>(list);
	pages.setPageSize(pagesize);

	final int goToPage = pageNumber - 1;
	if (goToPage <= pages.getPageCount() && goToPage >= 0) {
	    pages.setPage(goToPage);
	}

	request.getSession().setAttribute("employeelist", pages);
	int current = pages.getPage() + 1;
	int begin = Math.max(1, current - list.size());
	int end = Math.min(begin + 5, pages.getPageCount());
	int totalPageCount = pages.getPageCount();
	String baseUrl = "/manager/product/page/";
	model.addAttribute("beginIndex", begin);
	model.addAttribute("endIndex", end);
	model.addAttribute("currentIndex", current);
	model.addAttribute("totalPageCount", totalPageCount);
	model.addAttribute("baseUrl", baseUrl);
	model.addAttribute("ListProduct", pages);
	model.addAttribute("search", true);
	 User listt = userSerivce.findByEmail1(request.getUserPrincipal().getName());
	 model.addAttribute("user1", listt);
	 model.addAttribute("userinfo", listt);
	return "employee/productList";
    }

    @PostMapping(value = "/manager/product/edit")
    public String editProduct(@ModelAttribute @Valid Product product, RedirectAttributes redirectAttributes) {
    	productService.save(product);
    	 redirectAttributes.addFlashAttribute("message", "Sửa thành công!");
		return "redirect:/manager/product/"+ product.getProductid()+"/edit";
    }
}