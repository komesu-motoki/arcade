package jp.co.example.dao;

import java.util.List;

import jp.co.example.entity.Sales;

public interface SalesDao {
	public List<Sales> findAll(Integer userId);

}