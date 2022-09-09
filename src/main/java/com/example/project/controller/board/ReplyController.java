package com.example.project.controller.board;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.project.model.board.ReplyDAO;
import com.example.project.model.board.ReplyDTO;
import com.example.project.service.board.PageUtil;
@RestController
@RequestMapping("/reply/*")
public class ReplyController{
@Inject
ReplyDAO replyDao;
@RequestMapping("insert.do")
public void insert(ReplyDTO dto,HttpSession session){
String userid=(String)session.getAttribute("userid");
dto.setReplyer(userid);
replyDao.insert(dto);//댓글저장
}
@RequestMapping("/delete/{idx}")
public ResponseEntity<String> delete(@PathVariable("idx")
int idx){
ResponseEntity<String> entity=null;
try{
replyDao.delete(idx);//레코드삭제
entity=new ResponseEntity<>("success",HttpStatus.OK);
}catch(Exception e){
e.printStackTrace();
entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
}
return entity;
}
@RequestMapping("/detail/{idx}")
public ModelAndView detail(@PathVariable("idx")int idx,ModelAndView mav){
ReplyDTO dto=replyDao.detail(idx);//댓글상세내용
mav.setViewName("board/reply_detail");
mav.addObject("dto",dto);
return mav;
}
@RequestMapping("list.do")
public ModelAndView list(int board_idx, 
@RequestParam(defaultValue="1")int curPage, ModelAndView mav){
int count=replyDao.count(board_idx);
PageUtil page_info=new PageUtil(count,curPage);
int start=page_info.getPageBegin();
int end=page_info.getPageEnd();
List<ReplyDTO> list = replyDao.list(board_idx, start, end);
mav.setViewName("board/reply_list");
mav.addObject("list",list);
mav.addObject("page_info",page_info);
return mav;
}
@RequestMapping("list_json.do")
public @ResponseBody List<ReplyDTO> list_json(@RequestParam(defaultValue="1")int curPage, @RequestParam int idx){
int count=replyDao.count(idx);//댓글갯수
PageUtil pager=new PageUtil(count,curPage);
int start=pager.getPageBegin();
int end=pager.getPageEnd();
List<ReplyDTO> list=replyDao.list(idx,start,end);
return list;
}
@RequestMapping("/update/{idx}")
public ResponseEntity<String> update(@PathVariable("idx") int idx, @RequestBody ReplyDTO dto){
ResponseEntity<String> entity=null;
try{
dto.setIdx(idx);
replyDao.update(dto);//레코드수정
entity=new ResponseEntity<String>("success",HttpStatus.OK);
}catch(Exception e){
e.printStackTrace();
entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
}
return entity;
}
}