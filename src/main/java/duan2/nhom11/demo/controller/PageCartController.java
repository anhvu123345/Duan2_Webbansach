package duan2.nhom11.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import duan2.nhom11.demo.entity.Order;
import duan2.nhom11.demo.entity.OrderDetail;
import duan2.nhom11.demo.entity.Product;
import duan2.nhom11.demo.service.OrderDetailSerive;
import duan2.nhom11.demo.service.OrderSerive;
import duan2.nhom11.demo.service.ProductService;
import duan2.nhom11.demo.service.UserSerive;

@Controller
public class PageCartController {

	@Autowired
	private OrderDetailSerive orderDetailSerive;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserSerive userSerive;

	@Autowired
	private OrderSerive orderSerive;

// ========================  Giỏ Hàng ===========================================

	//
	@GetMapping(value = "/cart")
	public String caerr(Model model) {
		model.addAttribute("cart", new OrderDetail());
		return "custormer/giohang";
	}

	// Thêm thông tin sản phẩm vao database
	@PostMapping(value = "/save/cart")
	public ModelAndView addCart(Model model, @Valid OrderDetail cart, RedirectAttributes redirectAttributes) {
		ModelAndView modelview = new ModelAndView();
		List<OrderDetail> orders = orderDetailSerive.findByUserIDAndOrderID(cart.getUser().getUserid());

		for (OrderDetail od : orders) {
			if (od.getProduct().getProductid().equals(cart.getProduct().getProductid())) {
				cart.setId(od.getId());				
				cart.setIntoMoney(cart.getQuantity() * cart.getProduct().getPrice());
				orderDetailSerive.save(cart);
				modelview.setViewName("redirect:/cart/info?&id=" + cart.getUser().getUserid() + "");				
			} 
				
			}
		cart.setIntoMoney(cart.getQuantity() * cart.getProduct().getPrice());
		orderDetailSerive.save(cart);
		modelview.setViewName("redirect:/cart/info?&id=" + cart.getUser().getUserid() + "");

		return modelview;
	}

	// Trang hiển thị thông tin giỏ hàng
	@GetMapping(value = "/cart/info")
	public String info(Model model, @RequestParam Long id) {
		model.addAttribute("user", userSerive.findAll());
		List<OrderDetail> oderdetails = orderDetailSerive.findByUserIDAndOrderID(id);
		model.addAttribute("cartInfo", oderdetails );
		double tong = 0;
		for(OrderDetail od : oderdetails) {
			tong += od.getIntoMoney();
		}
		model.addAttribute("tongtien", tong);
		return "custormer/cart";

	}

	// Xóa thông tin giỏ hàng
	@GetMapping(value = "/cart/delete/{id}")
	public String delete(@PathVariable Long id) {
		OrderDetail od = orderDetailSerive.findById(id).get();
		Long idpage = od.getUser().getUserid();
		orderDetailSerive.delete(id);
		return "redirect:/cart/info?&id=" + idpage + "";
	}

//========================== phần controller của đơn đặt hàng (order) ===========================

	// chuyển tới trang mua hàng
	@GetMapping(value = "/mua-hang")
	public String muahang(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("user", userSerive.findAll());
		return "custormer/order";
	}

	// lưu đơn hàng
	@PostMapping(value = "/mua-hang/save")
	public String savemuaHang(@Valid Order order, Model model,RedirectAttributes redirectAttributes) {
		orderSerive.save(order);
		List<OrderDetail> od = orderDetailSerive.findByUserIDAndOrderID(order.getUser().getUserid());
		for (OrderDetail op : od) {
			op.setOrders(order);
			orderDetailSerive.save(op);
		}
		redirectAttributes.addFlashAttribute("muahang", "Bạn đã mua hàng thành công thành công hệ thống của chúng tôi");
		return "redirect:/mua-hang";
	}

}
