package duan2.nhom11.demo.controller;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.service.Emailservice;
import duan2.nhom11.demo.service.UserSerive;


@Controller
public class FogotPasswordController {

	@Autowired 
	private UserSerive userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private Emailservice emailService;
	
	@GetMapping(value="/fogot-password")
	public String fogotpassword() {
		return "user/fogotpassword";
	}
	
	@PostMapping(value="/fogot-password/link")
	public String fogotPageform(@RequestParam("email") String email, Model model) {
		User findemail = userService.findByUsername(email);
		if(findemail == null) {
			model.addAttribute("error", "Email không tồn tại");
		}else {
			findemail.setToken(UUID.randomUUID().toString());
			userService.save(findemail);
			String url = "http://localhost:8080";   	
			
			// Email message
			SimpleMailMessage passwordResetEmail  = new SimpleMailMessage();
			passwordResetEmail.setFrom("anhdvpd01920@fpt.edu.vn");
		
			passwordResetEmail.setTo(findemail.getEmail());
			passwordResetEmail.setSubject("Lấy lại mật khẩu");
			passwordResetEmail.setText("Đây là đường link lấy mật khẩu: "+url+"/reset?token="+findemail.getToken());
			
			//send mail
			emailService.SendMail(passwordResetEmail);
			
			
			model.addAttribute("measage", "Vui lòng kiểm tra email của bạn");
			
		}

		return "redirect:/login";
		
		
	}
	@GetMapping(value="/reset")
	public ModelAndView retset(@RequestParam("token") String token) {
		// TODO Auto-generated method stub
		 Optional<User>  user = userService.findByToken(token);
		 ModelAndView model = new ModelAndView();
		if(user.isPresent()) {
			model.addObject("user", user);
			model.addObject("token", token);
			model.setViewName("user/resetPassword");
		}else {
			model.addObject("error", "Đường link không tồn tại");
			model.setViewName("");
		}
		return model;
	}
	
	@PostMapping(value="/reset")
	public ModelAndView setNewPassword( @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
		ModelAndView model = new ModelAndView();
		Optional<User>  user = userService.findByToken(requestParams.get("token"));
		
		if(user.isPresent() ){	
			User restUser = user.get();	
			System.out.println(restUser.getEmail());
			restUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
			restUser.setToken(null);
			userService.save(restUser);
			redir.addFlashAttribute("thanhcong", "Bạn đã mật khẩu, có thể đăng nhập bây giờ");
			model.setViewName("redirect:/login");
			
		}else {
			redir.addFlashAttribute("error", "Đương link của bạn không đúng");
			model.setViewName("/fogot-password");
		}
		
		
		return model;
	}
}
