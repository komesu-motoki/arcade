package jp.co.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.controller.form.TradeForm;
import jp.co.example.entity.ItemStocks;
import jp.co.example.entity.Sales;
import jp.co.example.entity.UserInfo;
import jp.co.example.service.ItemStocksService;
import jp.co.example.service.SaleService;
import jp.co.example.service.TradeService;

@Controller
public class TradeController {

	@Autowired
	HttpSession session;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private SaleService saleService;

	@Autowired
	private ItemStocksService itemStocksService;

	@RequestMapping("/tradeMenu")
	public String tradeMenu(Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		return "tradeMenu";
	}

	@RequestMapping("/trade")
	public String trade(@ModelAttribute("TradeForm") TradeForm form, Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		UserInfo user = (UserInfo) session.getAttribute("list");
		//交換に出されているアイテムを取得
		List<Sales> list = tradeService.marketTrade(user.getUserId());
		session.setAttribute("marketItem", list);

		return "trade";
	}

	@RequestMapping("/tradeResult")
	public String tradeResult(@ModelAttribute("TradeForm") TradeForm form, Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		UserInfo user = (UserInfo) session.getAttribute("list");

		List<Integer> tradeList = new ArrayList<>();

		for (int i = 0; i < form.getTrade().length; i++) {
			tradeList.add(form.getTrade()[i]);
		}

		if (tradeList.size() == 0) {
			model.addAttribute("msg", "アイテムを選択してください");
			return "trade";

		}

		for (int i = 0; i < tradeList.size(); i++) {
			//アイテムがあるか確認
			if (tradeService.tradeCheck(tradeList.get(i), user.getUserId()) == null) {
				model.addAttribute("msg", "アイテムがありません");
				return "trade";
			}
		}

		for (int i = 0; i < tradeList.size(); i++) {
			if(saleService.itemWar(tradeList.get(i)) == null || saleService.itemWar(tradeList.get(i)).toString().isEmpty()){
				model.addAttribute("msg", "アイテムの出品は取り消されました");
				//交換に出されているアイテムを取得
				List<Sales> list = tradeService.marketTrade(user.getUserId());
				session.setAttribute("marketItem", list);
				return "trade";
			}
		}

		for (int i = 0; i < tradeList.size(); i++) {

			//フラグ管理
			//			tradeService.tradeSuccess(tradeList.get(i));
			//			//アイテム交換(出品者増やす→交換者減らす→交換者増やす)
			//			tradeService.itemChange(tradeList.get(i), user.getUserId());
			//			//トレードテーブルに記録
			//			tradeService.marketLog(tradeList.get(i), user.getUserId());
			tradeService.trade(tradeList.get(i), user.getUserId());

		}

		Integer userId = user.getUserId();
		List<ItemStocks> list = itemStocksService.findStockAll(userId);
		session.setAttribute("StockAll", list);

		return "tradeResult";
	}
}
