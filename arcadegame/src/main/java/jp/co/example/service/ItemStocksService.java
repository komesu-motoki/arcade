package jp.co.example.service;

import java.util.List;

import jp.co.example.entity.ItemStocks;
import jp.co.example.entity.Items;

public interface ItemStocksService {

	public void plusStock(Integer userId,Integer itemId,Integer number);
	//付けたし
	public List<ItemStocks> findStockAll(Integer userId);
	public void minusStock(Integer userId,Integer itemId,Integer number);
	public List<Items> getStockItem(Integer userId,Integer gameId);
}