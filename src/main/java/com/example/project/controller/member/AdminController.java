package com.example.project.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.model.member.AdminDAO;
import com.example.project.model.member.MemberDTO;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Inject
	AdminDAO adminDao;
	
	@RequestMapping("login.do")
	public String login() {
		return "admin/login";
	}
	@RequestMapping("login_check.do")
	public ModelAndView login_cheeck(MemberDTO dto, HttpSession session, ModelAndView mav) {
		String name = adminDao.login(dto);
		if (name != null) {
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("admin/admin");
			mav.addObject("message", "success");
		}else {
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login.do?message=logout";
	}
}
