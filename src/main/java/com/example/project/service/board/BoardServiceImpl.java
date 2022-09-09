package com.example.project.service.board;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.model.board.BoardDAO;
import com.example.project.model.board.BoardDTO;
@Service
public class BoardServiceImpl implements BoardService{
@Inject
BoardDAO boardDao;
@Override
public void delete_attach(String file_name){
boardDao.delete_attach(file_name);
}
@Transactional
@Override
public void insert(BoardDTO dto){
boardDao.insert(dto);
String[] files=dto.getFiles();
if(files==null)
return;
for(String name:files){
boardDao.insert_attach(name);
}
}
@Transactional
@Override
public void update(BoardDTO dto){
boardDao.update(dto);
String[] files=dto.getFiles();
if(files==null)
return;
for(String name:files){
boardDao.update_attach(name,dto.getIdx());
}
}
@Override
public void delete(int bno){
boardDao.delete(bno);
}
@Override
public List<String> list_attach(int idx){
return boardDao.list_attach(idx);
}
@Override
public BoardDTO detail(int idx){
return boardDao.detail(idx);
}
@Override
public List<BoardDTO> list(int start,int end,String search_option,String keyword){
return boardDao.list(start,end,search_option,keyword);
}
@Override
public void increase_hit(int idx){
boardDao.increase_hit(idx);
}
@Override
public int count(String search_option,String keyword){
return boardDao.count(search_option, keyword);
}
}
