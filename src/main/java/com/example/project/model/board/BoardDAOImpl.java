package com.example.project.model.board;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
@Repository
public class BoardDAOImpl implements BoardDAO{
@Inject
SqlSession sqlSession;
@Override
public BoardDTO detail(int idx){
return sqlSession.selectOne("board.detail",idx);
}
@Override
public void update(BoardDTO dto){
sqlSession.update("board.update",dto);
}
@Override
public void delete(int idx){
sqlSession.delete("board.delete",idx);
}
@Override
public List<BoardDTO> list(int start,int end,String search_option,String keyword){
Map<String,Object> map=new HashMap<>();
map.put("search_option",search_option);
map.put("keyword",keyword);
map.put("start",start);
map.put("end",end);
return sqlSession.selectList("board.list", map);
}
@Override
public void increase_hit(int idx){
sqlSession.update("board.increase_hit",idx);
}
@Override
public void delete_attach(String file_name){
sqlSession.delete("board.delete_attach",file_name);
}
@Override
public List<String> list_attach(int idx){
return sqlSession.selectList("board.list_attach",idx);
}
@Override
public void insert_attach(String file_name){
sqlSession.insert("board.insert_attach",file_name);
}
@Override
public void update_attach(String file_name,int idx){
Map<String,Object> map=new HashMap<>();
map.put("file_name",file_name);
map.put("idx",idx);
sqlSession.insert("board.update_attach",map);
}
@Override
public void insert(BoardDTO dto){
sqlSession.insert("board.insert",dto);
}
@Override
public int count(String search_option,String keyword){
Map<String,Object> map=new HashMap<>();
map.put("search_option", search_option);
map.put("keyword", keyword);
return sqlSession.selectOne("board.count", map);
}
}
