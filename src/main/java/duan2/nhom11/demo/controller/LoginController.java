package duan2.nhom11.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public ModelAndView login(String error, String logout, HttpServletRequest ck) {
		ModelAndView model = new ModelAndView();

		if (ck.getUserPrincipal() != null) {
			model.setViewName("redirect:/welcome");
			return model;
		} else {
			if (error != null) {
				model.addObject("error", "Bạn nhập sai email hoặc mật khẩu");

			}
			if (logout != null) {
				model.addObject("logout", "Bạn đã đăng xuất thành công");

			}
			model.setViewName("web/login");
			return model;
		}
	}

	@GetMapping(value = "logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/login";

	}

}
