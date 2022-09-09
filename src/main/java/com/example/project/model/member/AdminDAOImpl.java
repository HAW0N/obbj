package com.example.project.model.member;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
@Repository
	public class AdminDAOImpl implements AdminDAO{
	
	@Inject
		SqlSession sqlSession;
	@Override
		public String login(MemberDTO dto){
		return sqlSession.selectOne("admin.login", dto);
	}
}
