package com.example.project.controller.member;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.model.member.MemberDAO;
import com.example.project.model.member.MemberDTO;

@Controller
public class MemberController {
	
	@Inject
	MemberDAO memberDao;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:/member/list.do";
	}
	
	@RequestMapping("member/list.do")
	public String memberList(Model model) {
		List<MemberDTO> list = memberDao.list();
		model.addAttribute("list", list);
		return "member/list";
	}
	
	@RequestMapping("member/write.do")
	public String write() {
		return "member/write";
	}
	
	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberDao.insert(dto);
		return "redirect:/member/list.do";
	}
	@RequestMapping("member/view.do")
	public String view(@RequestParam String userid, Model model) {
		model.addAttribute("dto", memberDao.detail(userid));
		return "member/detail";
	}
	
	@RequestMapping("member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		boolean result = memberDao.check_passwd(dto.getUserid(), dto.getPasswd());
		
		if(result) {
			memberDao.update(dto);
			return "redirect:/member/list.do";
		} else {
			MemberDTO dto2 = memberDao.detail(dto.getUserid());
			dto.setJoin_date(dto2.getJoin_date());
			model.addAttribute("dto", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/detail";
		}
	}
	
	@RequestMapping("member.delete.do")
	public String delete(@RequestParam String userid, @RequestParam String passwd, Model model) {
		boolean result = memberDao.check_passwd(userid, passwd);
		
		if(result) {
			memberDao.delete(userid);
			return "redirect:/member/list.do";
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("dto", memberDao.detail(userid));
			
			return "member/detail";
		}
	}

}
