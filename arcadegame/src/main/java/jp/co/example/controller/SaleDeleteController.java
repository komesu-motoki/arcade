package jp.co.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.controller.form.SaleDeleteForm;
import jp.co.example.entity.ItemStocks;
import jp.co.example.entity.Sales;
import jp.co.example.entity.UserInfo;
import jp.co.example.service.ItemStocksService;
import jp.co.example.service.SaleDeleteService;
import jp.co.example.service.SaleService;

@Controller
public class SaleDeleteController {

	@Autowired
	HttpSession session;

	@Autowired
	private SaleService saleService;

	@Autowired
	private SaleDeleteService saleDeleteService;

	@Autowired
	private ItemStocksService itemStocksService;

	@RequestMapping("/saleDelete")
	public String saleDelete(@ModelAttribute("SaleDeleteForm") SaleDeleteForm form, Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		UserInfo user = (UserInfo) session.getAttribute("list");

		List<Sales> list = saleDeleteService.findAll(user.getUserId());
		session.setAttribute("marketItem", list);

		return "saleDelete";
	}

	@RequestMapping("/saleDeleteResult")
	public String saleDeleteResult(@ModelAttribute("SaleDeleteForm") SaleDeleteForm form, Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		List<Integer> saleList = new ArrayList<>();

		for (int i = 0; i < form.getDelete().length; i++) {
			saleList.add(form.getDelete()[i]);
		}

		if (saleList.size() == 0) {
			model.addAttribute("msg", "アイテムを選択してください");
			return "saleDelete";

		}

		for (int i = 0; i < saleList.size(); i++) {
			if(saleService.itemWar(saleList.get(i)) == null || saleService.itemWar(saleList.get(i)).toString().isEmpty()){
				model.addAttribute("msg", "アイテムは既に交換されました");
				UserInfo user = (UserInfo) session.getAttribute("list");
				Integer userId = user.getUserId();
				List<ItemStocks> list = itemStocksService.findStockAll(userId);
				session.setAttribute("StockAll", list);

				return "saleDelete";
			}
		}

		for (int i = 0; i < saleList.size(); i++) {
			saleDeleteService.marketCancel(saleList.get(i));
		}

		UserInfo user = (UserInfo) session.getAttribute("list");
		Integer userId = user.getUserId();
		List<ItemStocks> list = itemStocksService.findStockAll(userId);
		session.setAttribute("StockAll", list);

		return "saleDeleteResult";
	}
}
