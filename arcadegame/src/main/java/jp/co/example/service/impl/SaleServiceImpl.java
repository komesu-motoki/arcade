package jp.co.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.dao.ItemDao;
import jp.co.example.dao.ItemStocksDao;
import jp.co.example.dao.SalesDao;
import jp.co.example.entity.Items;
import jp.co.example.entity.Sales;
import jp.co.example.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ItemStocksDao itemStocksDao;

	@Autowired
	private SalesDao salesDao;

	public List<Items> findAll() {
		return itemDao.findAll();
	}

	@Override
	public List<Items> havingItem(Integer userId) {
		return itemDao.havingItem(userId);
	}

	@Override
	public void marketOpen(Integer userId, Integer giveItem,Integer takeItem) {
		itemStocksDao.itemWast(userId,giveItem);
		salesDao.marketOpen(userId,giveItem,takeItem);
	}

	@Override
	public List<Sales> itemWar(Integer saleId) {
		return salesDao.itemWer(saleId);
	}
}
