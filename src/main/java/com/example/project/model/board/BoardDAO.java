package com.example.project.model.board;

import java.util.List;

public interface BoardDAO{
void delete_attach(String file_name);
List<String> list_attach(int idx);
void insert_attach(String file_name);
void update_attach(String file_name, int idx);
void insert(BoardDTO dto);
BoardDTO detail(int idx);
void update(BoardDTO dto);
void delete(int idx);
List<BoardDTO> list(int start,int end,String search_option,String keyword);
void increase_hit(int idx);
int count(String search_option,String keyword);
}