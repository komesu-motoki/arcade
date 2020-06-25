package jp.co.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.dao.ItemStocksDao;
import jp.co.example.dao.UserInfoDao;
import jp.co.example.entity.UserInfo;
import jp.co.example.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private ItemStocksDao itemStocksDao;

	@Override
	public List<UserInfo> findAll() {

		return userInfoDao.findAll();

	}

	@Override
	public List<UserInfo> findIdPass(String loginId, String password) {

		return userInfoDao.findIdPass(loginId, password);

	}

	@Override
	public void insert(String loginId, String userName, String password) {

		userInfoDao.insert(loginId, userName, password);

	}

	@Override
	public List<UserInfo> findByUserId(Integer userId) {

		return userInfoDao.findByUserId(userId);

	}

	@Override
	public List<UserInfo> findByLoginId(String loginId) {

		return userInfoDao.findByLoginId(loginId);

	}

	@Override
	public List<UserInfo> allItem(Integer userId) {

		return userInfoDao.allItem(userId);

	}

	@Override
	public List<UserInfo> findRanking(Integer gameId) {

		return userInfoDao.findRanking(gameId);

	}

	@Override
	public List<UserInfo> findMyRanking(Integer gameId, Integer userId) {

		return userInfoDao.findMyRanking(gameId, userId);

	}

	@Override
	public List<UserInfo>  higtScoreDate(String userName, Integer gameId) {

		return userInfoDao. higtScoreDate(userName, gameId);

	}

	@Override
	public void update(String loginId, String userName, String password, Integer userId) {

		userInfoDao.update(loginId, userName, password, userId);

	}

	@Override
	public List<UserInfo> findByUserName(String userName) {

		return userInfoDao.findByUserName(userName);

	}
	@Override
	public List<UserInfo> findIdUserNamePass(Integer userId, String loginId, String userName) {

		return userInfoDao.findIdUserNamePass(userId, loginId, userName);

	}

	//付けたし
	public UserInfo getCoin(Integer userId) {
		return userInfoDao.getCoin(userId);
	}

	//付けたし
	public void subCoin(Integer userId,Integer coin) {
		userInfoDao.subCoin(userId, coin);
	}

	public void plusCoin(Integer userId,Integer coin) {
		userInfoDao.plusCoin(userId, coin);
	}

	public void updatelogin_date(String loginId) {
		userInfoDao.updatelogin_date(loginId);
	}

	@Transactional
	public void buyResult(Integer userId,Integer price,Integer itemId,Integer number) {
		userInfoDao.subCoin(userId, price);
		itemStocksDao.plusStock(userId,itemId, number);
	}

	@Transactional
	public void sellResult(Integer userId,Integer price,Integer itemId,Integer number) {
		userInfoDao.plusCoin(userId, price);
		itemStocksDao.minusStock(userId,itemId, number);
	}

	@Transactional
	public void userInsert(String loginId,String userName,String password,Integer itemId) {

		userInfoDao.insert(loginId,userName,password);
		itemStocksDao.itemInsert(loginId,password,itemId);
	}

}
