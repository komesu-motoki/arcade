package jp.co.example.service;

import java.util.List;

import jp.co.example.entity.Items;
import jp.co.example.entity.Sales;

public interface SaleService {
	public List<Items> findAll();

	public List<Items> havingItem(Integer userId);

	public void marketOpen(Integer userId, Integer giveItem, Integer takeItem);

	public List<Sales> itemWar(Integer saleId);

}