package com.example.project.service.board;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.example.project.model.board.BoardDTO;
public interface BoardService{
void delete_attach(String file_name);
List<String> list_attach(int bno);
void insert(BoardDTO dto);
BoardDTO detail(int bno);
void update(BoardDTO dto);
void delete(int bno);
List<BoardDTO> list(int start, int end,
String search_option,String keyword);
void increase_hit(int bno);
int count(String search_option,String keyword);
}
