package jp.co.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.dao.SalesDao;
import jp.co.example.entity.Sales;

@Repository
public class PgSalesDao implements SalesDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Sales> findAll(Integer userId) {
		String sql = "SELECT sale_id,user_name,game_name,give_item,GIVE.item_name AS give_name, take_item,TAKE.item_name AS take_name"
				+ " FROM sales"
				+ " JOIN user_info"
				+ " ON sales.user_id = user_info.user_id"
				+ " INNER JOIN items AS GIVE"
				+ " ON sales.give_item = GIVE.item_id"
				+ " INNER JOIN items AS TAKE"
				+ " ON sales.take_item = TAKE.item_id"
				+ " JOIN games"
				+ " ON GIVE.game_id = games.game_id"
				+ " WHERE sale_flag = 1"
				+ " AND sales.user_id = :UserId"
				+ " ORDER BY sale_id DESC";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserId", userId);
		List<Sales> result = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Sales>(Sales.class));
		return result.isEmpty() ? null : result;

	}

	@Override
	public void marketCancel(Integer salesId) {
		String sql = "UPDATE sales SET sale_flag = 3 WHERE sale_id = :SalesId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("SalesId", salesId);
		jdbcTemplate.update(sql, param);

	}

	@Override
	public List<Sales> marketTrade(Integer userId) {
		String sql = "SELECT sale_id,user_name,game_name,give_item,GIVE.item_name AS give_name, take_item,TAKE.item_name AS take_name"
				+ " FROM sales"
				+ " JOIN user_info"
				+ " ON sales.user_id = user_info.user_id"
				+ " JOIN items AS GIVE"
				+ " ON sales.give_item = GIVE.item_id"
				+ " JOIN items AS TAKE"
				+ " ON sales.take_item = TAKE.item_id"
				+ " JOIN games"
				+ " ON GIVE.game_id = games.game_id"
				+ " WHERE sale_flag = 1"
				+ " AND sales.user_id <> :UserId"
				+ " ORDER BY sale_id DESC";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("UserId", userId);
		List<Sales> result = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Sales>(Sales.class));
		return result.isEmpty() ? null : result;

	}

	@Override
	public void tradeSuccess(Integer saleId) {
		String sql = "UPDATE sales SET sale_flag = 2 WHERE sale_id = :SalesId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("SalesId", saleId);
		jdbcTemplate.update(sql, param);

	}

	@Override
	public void marketOpen(Integer userId, Integer giveItem, Integer takeItem) {
		String sql = "INSERT INTO sales(user_id,give_item,take_item)"
				+ " VALUES (:SalesId,:GiveItem,:TakeItem);";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("SalesId", userId);
		param.addValue("GiveItem", giveItem);
		param.addValue("TakeItem", takeItem);
		jdbcTemplate.update(sql, param);
	}

	@Override
	public List<Sales> itemWer(Integer saleId) {
		String sql = "SELECT * FROM sales WHERE sale_id = :SaleId AND sale_flag = 1;";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("SaleId", saleId);
		List<Sales> result = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Sales>(Sales.class));
		return result.isEmpty() ? null : result;
	}
}
