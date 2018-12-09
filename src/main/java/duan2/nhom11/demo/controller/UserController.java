package duan2.nhom11.demo.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.ListRoleUser;
import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.service.UserSerive;

@Controller
public class UserController {
	@Autowired
	private UserSerive userSerive;

	@GetMapping(value = "/admin/userlist")
	public String userList(Model mm, HttpServletRequest request
		,RedirectAttributes redirect) {
		request.getSession().setAttribute("employeelist", null);
		if(mm.asMap().get("success") != null)
			redirect.addFlashAttribute("success",mm.asMap().get("success").toString());
		return "redirect:/admin/userlist/page/1";
	}
	
	@GetMapping("/admin/userlist/page/{pageNumber}")
	public String showEmployeePage(HttpServletRequest request, 
			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("employeelist");
		int pagesize = 8;
		List<User> list = (List<User>) userSerive.findAll();
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
		String baseUrl = "/admin/userlist/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("Listuser", pages);
		model.addAttribute("userRole", new User());
		return "admin/listUser";
	}

	@GetMapping(value = "/user/registrantion")
	public String useradd( Model model) {
		
		model.addAttribute("user", new User());

		return "web/registration";
	}

	@GetMapping(value = "/admin/{id}/edit")
	public ModelAndView userEdit(@PathVariable Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("user", userSerive.findById(id));
		model.setViewName("");
		return model;
	}

	@PostMapping(value = "/user/sign")
	public String userSave(@ModelAttribute @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		User usercheck = userSerive.findByUsername(user.getEmail());

		if (bindingResult.hasErrors()== true) {
			return "web/registration";
		}
		
		if (usercheck != null ) {
		    redirectAttributes.addFlashAttribute("emailerror", "Email đã tồn tại");
			return "redirect:/user/registrantion";
		}
		
		user.setActive(true);
		user.setRole("ROLE_USER");
		userSerive.save(user);
		return "redirect:/login";
	}

	@GetMapping(value = "/admin/user/{id}/delete")
	public ModelAndView userDelete(@PathVariable Long id) {
		ModelAndView model = new ModelAndView();
		userSerive.delete(id);
		model.setViewName("redirect:/admin/userlist");
		return model;
	}

	/*===========================Lấy user từ list để thêm lần lượt===========================*/
	@PostMapping(value = "/user/setrole")
	public ModelAndView setRole(@ModelAttribute("listtt") ListRoleUser listtt) {
		ModelAndView model = new ModelAndView();

		for (User usa : listtt.getListuser()) {
			userSerive.saverole(usa);
		}
		model.setViewName("redirect:/admin/userlist");
		return model;
	}
	/*========================================================================================*/
	
	/*	=======================Dùng vòng lặp đếm và  những user hiện có add vào list==========*/
	@ModelAttribute("listtt")
	public ListRoleUser thanhtich() {
		User us = new User();
		us.setRole("");
		ListRoleUser listtt = new ListRoleUser();
		List<User> listrole = new ArrayList<User>();

		for (int i = 0; i < listrole.size(); i++) {

			listrole.add(us);
		}
		listtt.setListuser(listrole);
		return listtt;
	}
	/*========================================================================================*/
	@GetMapping("/admin/userlist/search/{pageNumber}")
	public String search(@RequestParam("s") String s, Model model, HttpServletRequest request,
			@PathVariable int pageNumber) {
		if (s.equals("")) {
			return "redirect:/admin/userlist/page/1";
		}
		List<User> list = userSerive.search(s);
		if (list == null) {
			return "redirect:/admin/userlist/page/1";
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
		String baseUrl = "/admin/userlist/page/";

		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("Listuser", pages);
		model.addAttribute("userRole", new User());

		return "admin/listUser";
	}
}