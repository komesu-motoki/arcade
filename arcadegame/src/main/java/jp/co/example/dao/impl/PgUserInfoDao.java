package jp.co.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.dao.UserInfoDao;
import jp.co.example.entity.UserInfo;

@Repository
public class PgUserInfoDao implements UserInfoDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<UserInfo> findAll() {

		String sql = "SELECT * FROM user_info";
		List<UserInfo> result = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return result.isEmpty() ? null : result;

	}

	public List<UserInfo> findIdPass(String loginId, String password) {

		String sql = "SELECT * FROM user_info WHERE login_id = :loginId AND password = :password";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", loginId);
		param.addValue("password", password);

		List<UserInfo> resultList = jdbcTemplate.query(sql, param,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return resultList.isEmpty() ? null : resultList;

	}

	public void insert(String loginId, String userName, String password) {

		String sql = "INSERT INTO user_info (login_id, user_name, password) VALUES (:LoginId, :UserName, :Password)";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("LoginId", loginId);
		param.addValue("UserName", userName);
		param.addValue("Password", password);

		jdbcTemplate.update(sql, param);

	}

	public List<UserInfo> findByUserId(Integer userId) {

		String sql = "SELECT * FROM user_info WHERE user_id = :UserId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserId", userId);

		List<UserInfo> result1 = jdbcTemplate.query(sql, param,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return result1.isEmpty() ? null : result1;

	}

	public List<UserInfo> findByLoginId(String loginId) {

		String sql = "SELECT * FROM user_info WHERE login_id = :LoginId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("LoginId", loginId);

		List<UserInfo> result1 = jdbcTemplate.query(sql, param,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return result1.isEmpty() ? null : result1;

	}

	public List<UserInfo> allItem(Integer userId) {
		String sql = "select g.game_name, i.item_name, i.item_explan, s.item_have "
				+ "from user_info u join item_stocks s on u.user_id=s.user_id "
				+ "join items i on s.item_id=i.item_id join games g on i.game_id=g.game_id where u.user_id = :UserId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserId", userId);

		List<UserInfo> allItem = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return allItem.isEmpty() ? null : allItem;
	}

	public List<UserInfo> findRanking(Integer gameId) {
		String sql = "SELECT user_name, rank() over (order by(MAX(score))desc) rank, MAX(score) AS ハイスコア, score_date FROM user_info "
				+ "JOIN scores "
				+ "ON user_info.user_id = scores.user_id"
				+ "where game_id = :Game_id"
				+ "group by user_info.user_id,score_date";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("GameId", gameId);

		List<UserInfo> ranking = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return ranking.isEmpty() ? null : ranking;
	}

	public List<UserInfo> playCount(Integer userId, Integer gameId) {
		String sql = "select count(*) from scores"
				+ "where user_id = :UserId and game_id = :GameId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserId", userId);
		param.addValue("GameId", gameId);

		List<UserInfo> playCount = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return playCount.isEmpty() ? null : playCount;
	}

	@Override
	public void coinWast(Integer userId) {
		String sql = "UPDATE user_info SET coin_have = coin_have - 30 WHERE user_id = :UserId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserId", userId);

		jdbcTemplate.update(sql, param);

	}

	@Override
	public List<UserInfo> userHaveCoin(Integer userId) {
		String sql = "SELECT * FROM user_info WHERE user_id = :UserId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserId", userId);

		List<UserInfo> userHaveCoin = jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return userHaveCoin.isEmpty() ? null : userHaveCoin;

	}

	@Override
	public void update(String loginId, String userName, String password, Integer userId) {

		String sql = "UPDATE user_info SET login_id = :LoginId, user_name = :UserName, password = :Password WHERE user_id = :UseId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("LoginId", loginId);
		param.addValue("UserName", userName);
		param.addValue("Password", password);
		param.addValue("UserId", userId);

		jdbcTemplate.update(sql, param);

	}

	@Override
	public List<UserInfo> findByUserName(String userName) {

		String sql = "SELECT * FROM user_info WHERE user_name = :UserName";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserName", userName);

		List<UserInfo> result2 = jdbcTemplate.query(sql, param,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));

		return result2.isEmpty() ? null : result2;

	}
}
