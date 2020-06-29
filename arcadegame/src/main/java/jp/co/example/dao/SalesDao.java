package jp.co.example.dao;

import java.util.List;

import jp.co.example.entity.Sales;

public interface SalesDao {
	public List<Sales> findAll(Integer userId);

	public void marketCancel(Integer salesId);

	public List<Sales> marketTrade(Integer userId);

	public void tradeSuccess(Integer userId);

	public void marketOpen(Integer userId, Integer giveItem, Integer takeItem);

	public List<Sales> itemWer(Integer saleId);

}