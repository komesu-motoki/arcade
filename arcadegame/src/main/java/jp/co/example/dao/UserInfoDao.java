package jp.co.example.dao;

import java.util.List;

import jp.co.example.entity.UserInfo;

public interface UserInfoDao {

	public List<UserInfo> findAll();
	public List<UserInfo> findIdPass(String loginId, String password);
	public void insert(String loginId, String userName, String password);
	public List<UserInfo> findByUserId(Integer userId);
	public List<UserInfo> findByLoginId(String loginId);
	public List<UserInfo> allItem(Integer userId);
	public List<UserInfo> findRanking(Integer gameId);
	public List<UserInfo> higtScoreDate(String userName, Integer gameId);
	public List<UserInfo> findMyRanking(Integer gameId, Integer userId);
	public void coinWast(Integer userId);
	public List<UserInfo> userHaveCoin(Integer userId);
	public void update(String loginId, String userName, String password, Integer userId);
	public List<UserInfo> findByUserName(String userName);
	public List<UserInfo> findIdUserNamePass(String loginId, String userName, String password);
	public UserInfo getCoin(Integer userId);
	public void subCoin(Integer userId,Integer coin);
	public void plusCoin(Integer userId,Integer coin);
}

