package duan2.nhom11.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.service.UserSerive;

@Controller
public class UserController {
	@Autowired
	private UserSerive userSerive;

	

	@GetMapping(value = "/admin/userlist")
	public ModelAndView userList() {
		ModelAndView model = new ModelAndView();
		model.addObject("users", userSerive.findAll());
		model.setViewName("admin/listUser");
		return model;
	}

	@GetMapping(value = "/user/registrantion")
	public ModelAndView useradd() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", new User());
		model.setViewName("web/registration");

		return model;
	}

	@GetMapping(value = "/admin/{id}/edit")
	public ModelAndView userEdit(@PathVariable Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("user", userSerive.findById(id));
		model.setViewName("");
		return model;
	}

	@PostMapping(value = "/user/sign")
	public ModelAndView userSave(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User usercheck = userSerive.findByUsername(user.getEmail());

		if (usercheck != null) {
			model.addObject("error", "Email đã tồn tại");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("/user/registrantion");
		}
		user.setActive(true);
		user.setRole("ROLE_USER");
		userSerive.save(user);

		model.setViewName("redirect:/login");
		return model;
	}

	@GetMapping(value = "/admin/user/{id}/delete")
	public ModelAndView userDelete(@PathVariable Long id) {
		ModelAndView model = new ModelAndView();
		userSerive.delete(id);
		model.setViewName("");
		return model;
	}
}
