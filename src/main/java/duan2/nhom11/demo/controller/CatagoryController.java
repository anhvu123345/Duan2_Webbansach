package duan2.nhom11.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.Catagory;
import duan2.nhom11.demo.service.CatagoryService;

@Controller
public class CatagoryController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    private CatagoryService catagoryService;

    @GetMapping(value = "/manager/catagory/list")
    public String CataList(Model model, HttpServletRequest request, RedirectAttributes redirect) {
	request.getSession().setAttribute("employeelist", null);
	if (model.asMap().get("success") != null)
	    redirect.addFlashAttribute("success", model.asMap().get("success").toString());

	return "redirect:/manager/catagory/page/1";
    }

    @GetMapping("/manager/catagory/page/{pageNumber}")
    public String showEmployeePage( Catagory catagory,HttpServletRequest request, @PathVariable int pageNumber, Model model) {
	PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeelist");
	int pagesize = 8;
	List<Catagory> list = (List<Catagory>) catagoryService.findAll();
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
	String baseUrl = "/manager/catagory/page/";

	model.addAttribute("beginIndex", begin);
	model.addAttribute("endIndex", end);
	model.addAttribute("currentIndex", current);
	model.addAttribute("totalPageCount", totalPageCount);
	model.addAttribute("baseUrl", baseUrl);
	model.addAttribute("ListCatagory", pages);
	model.addAttribute("catagorys", catagoryService.findAll());
	model.addAttribute("catagory", catagory);
	model.addAttribute("catagorys", catagoryService.findAll());
	return "employee/catagoryList";
    }

    @PostMapping(value = "/manager/catagory/save")
    public String CataSave(@Valid Catagory catagory, RedirectAttributes redirectAttributes, Model model) {
	if (catagory.getCatagoryName() == null || catagory.getCatagoryName().length() < 4) {
		System.out.println("dsvdsv"); 
		 redirectAttributes.addFlashAttribute("CatagoryNameerr", "Loại truyện phải trên 3 kí tự!");
	    return "redirect:/manager/catagory/page/1";
	}
	if (catagory.getCatagoryName().length() > 20) {
		System.out.println("dsvdsv"); 
		 redirectAttributes.addFlashAttribute("CatagoryNameerr", "Loại truyện phải dưới 20 kí tự!");
	    return "redirect:/manager/catagory/page/1";
	}
	if (catagoryService.existsByCatagoryName(catagory.getCatagoryName())) {
	    redirectAttributes.addFlashAttribute("errorname", catagory.getCatagoryName().toUpperCase());
	    
	    return "redirect:/manager/catagory/page/1";
	} else {

	    model.addAttribute("student", catagory);

	    if (catagory.getCatagoryName() != null) {
		model.addAttribute("thanhcong", true);
		catagoryService.save(catagory);
	    }
	    return "redirect:/manager/catagory/list";
	}
	
    }

    @GetMapping(value = "/manager/catagory/{id}/edit")
    public String cataEdit( Model model, @PathVariable Long id, HttpServletRequest request,
	    RedirectAttributes redirect) {
	request.getSession().setAttribute("employeelist", null);
	if (model.asMap().get("success") != null)
	    redirect.addFlashAttribute("success", model.asMap().get("success").toString());
	
	return "redirect:/manager/catagory/page/1/"+id;
    }

    @PostMapping(value = "/manager/catagory/edit")
    public String CataEdit(@ModelAttribute @Valid Catagory catagory, Long id, RedirectAttributes redirectAttributes,
	    BindingResult bindingResult, Model model) {
    	if (catagory.getCatagoryName() == null || catagory.getCatagoryName().length() < 4) {
    		System.out.println("dsvdsv"); 
    		 redirectAttributes.addFlashAttribute("CatagoryNameerr", "Loại truyện phải trên 3 kí tự!");
    	    return "redirect:/manager/catagory/page/1/" +  catagoryService.findByCatagory(catagory.getCatagoryid());
    	}
    	
    	if (catagory.getCatagoryName().length() > 20) {
    		System.out.println("dsvdsv"); 
    		 redirectAttributes.addFlashAttribute("CatagoryNameerr", "Loại truyện phải dưới 20 kí tự!");
    	    return "redirect:/manager/catagory/page/1";
    	}
    	
	if (catagoryService.existsByCatagoryName(catagory.getCatagoryName())) {
	    redirectAttributes.addFlashAttribute("errorname", catagory.getCatagoryName().toUpperCase());
	    model.addAttribute("catagorys", catagoryService.findAll());
	    return "redirect:/manager/catagory/page/1/" +  catagoryService.findByCatagory(catagory.getCatagoryid());
	} else {

	    model.addAttribute("student", catagory);

	    if (catagory.getCatagoryName() != null) {
		model.addAttribute("thanhcong", true);
		catagoryService.save(catagory);
	    }
	    return "redirect:/manager/catagory/list";
	}
    }

    @GetMapping(value = "/manager/catagory/{id}/delete")
    public String cataDelete(@PathVariable Long id) {
	catagoryService.delete(id);
	return "redirect:/manager/catagory/list";
    }
    
    @GetMapping("/manager/catagory/page/{pageNumber}/{id}")
    public String ShowCatagoryEdit(HttpServletRequest request, @PathVariable Long id, @PathVariable int pageNumber, Model model) {
	PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeelist");
	int pagesize = 8;
	List<Catagory> list = (List<Catagory>) catagoryService.findAll();
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
	String baseUrl = "/manager/catagory/page/";

	model.addAttribute("beginIndex", begin);
	model.addAttribute("endIndex", end);
	model.addAttribute("currentIndex", current);
	model.addAttribute("totalPageCount", totalPageCount);
	model.addAttribute("baseUrl", baseUrl);
	model.addAttribute("ListCatagory", pages);
	model.addAttribute("catagory", catagoryService.findById(id));
	model.addAttribute("catagorys", catagoryService.findAll());
	model.addAttribute("productid", catagoryService.findByCatagory(id));
	return "employee/catagoryEditList";
    }
    
    @GetMapping("/manager/catagory/search/{pageNumber}")
    public String search(@RequestParam("s") String s, Model model, HttpServletRequest request,
	    @PathVariable int pageNumber) {
	if (s.equals("")) {
	    return "redirect:/manager/catagory/page/1";
	}
	List<Catagory> list = catagoryService.search(s);
	if (list == null) {
	    return "redirect:/manager/catagory/page/1";
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
	String baseUrl = "/manager/catagory/page/";
	model.addAttribute("beginIndex", begin);
	model.addAttribute("endIndex", end);
	model.addAttribute("currentIndex", current);
	model.addAttribute("totalPageCount", totalPageCount);
	model.addAttribute("baseUrl", baseUrl);
	model.addAttribute("ListCatagory", pages);
	model.addAttribute("catagorys", catagoryService.findAll());
	model.addAttribute("catagory", new Catagory());
	return "employee/catagoryList";
    }
    
}
