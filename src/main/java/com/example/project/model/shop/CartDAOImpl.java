package com.example.project.model.shop;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOImpl implements CartDAO{
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<CartDTO> cart_money() {
		return sqlSession.selectList("cart.cart_money");
	}
	
	@Override
	public void insert(CartDTO dto) {
		sqlSession.insert("cart.insert", dto);
	}
	
	@Override
	public List<CartDTO> list(String userid) {
		return sqlSession.selectList("cart.list", userid);
	}
	
	@Override
	public void delete(int cart_id) {
		sqlSession.delete("cart.delete", cart_id);
	}
	
	@Override
	public void delete_all(String userid) {
		sqlSession.delete("cart.delete_all", userid);
	}
	
	@Override
	public int sum_money(String userid) {
		return sqlSession.selectOne("cart.sum_money", userid);
	}
	
	@Override
	public void modify(CartDTO dto) {
		sqlSession.update("cart.modify", dto);
	}
}
