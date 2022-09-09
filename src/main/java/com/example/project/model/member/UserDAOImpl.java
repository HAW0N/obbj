package com.example.project.model.member;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
@Repository
public class UserDAOImpl implements UserDAO{
	
@Inject
SqlSession sqlSession;

@Override
public String login(UserDTO dto){
return sqlSession.selectOne("member.login",dto);
}

}
