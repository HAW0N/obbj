package com.example.project.controller.shop;
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
import javax.inject.Inject; 
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.servlet.ModelAndView;

import com.example.project.model.shop.CartDAO;
import com.example.project.model.shop.CartDTO;
@Controller 
@RequestMapping("/shop/cart/*") 
public class CartController {
@Inject
CartDAO cartDao;

@RequestMapping("delete.do") 
public String delete(int cart_id) { 
	cartDao.delete(cart_id);
	return "redirect:/shop/cart/list.do";
}
 
@RequestMapping("deleteAll.do") 
public String deleteAll(HttpSession session) {
	String userid = (String) session.getAttribute("userid");
	if (userid != null) { 
		cartDao.delete_all(userid); 
	}
 return "redirect:/shop/cart/list.do";
}
@RequestMapping("update.do") 
public String update(int[] amount, int[] cart_id, HttpSession session) {
String userid = (String) session.getAttribute("userid");
if (userid == null) { 
	return "redirect:/member/login.do";
}
for (int i = 0; i < cart_id.length; i++) {
if (amount[i] == 0) {
	cartDao.delete(cart_id[i]);
} else {
CartDTO dto = new CartDTO(); 
dto.setUserid(userid); 
dto.setCart_id(cart_id[i]); 
dto.setAmount(amount[i]);
cartDao.modify(dto);
}
} 
return "redirect:/shop/cart/list.do";
}
@RequestMapping("list.do") 
public ModelAndView list(HttpSession session, ModelAndView mav) {
Map<String, Object> map = new HashMap<>();
String userid = (String) session.getAttribute("userid"); 
if (userid != null) {
List<CartDTO> list = cartDao.list(userid); 
int sumMoney = cartDao.sum_money(userid);
int fee = sumMoney >= 30000 ? 0 : 2500;
map.put("sumMoney", sumMoney); 
map.put("fee", fee); 
map.put("sum", sumMoney + fee); 
map.put("list", list); 
map.put("count", list.size());	
mav.setViewName("shop/cart_list"); 
mav.addObject("map", map);
return mav;
} else {
return new ModelAndView("member/login");
}
}

@RequestMapping("insert.do")
public String insert(CartDTO dto, HttpSession session) {
String userid = (String) session.getAttribute("userid"); 
if (userid == null) { 
	return "redirect:/member/login.do";
} dto.setUserid(userid); 
cartDao.insert(dto); 
return "redirect:/shop/cart/list.do"; 
}
}

