package jp.co.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.controller.form.UserInfoForm;
import jp.co.example.entity.UserInfo;
import jp.co.example.service.UserInfoService;

@Controller
public class UserInfoController {

	@Autowired
	HttpSession session;

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("/userInfo")
	public String userInfo(Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		return "userInfo";

	}

	@RequestMapping("/userInfoChange")
	public String userInfoChange(@ModelAttribute("userInfo") UserInfoForm userInfoform, BindingResult result,
			Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		return "userInfoChange";

	}

	@RequestMapping("/userInfoChange1")
	public String userInfoChange1(@Validated @ModelAttribute("userInfo") UserInfoForm userInfoform,
			BindingResult result, Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		if (result.hasErrors()) {

			return "userInfoChange";

		}

		if (session.getAttribute("loginId").equals(userInfoform.getLoginId())
				&& session.getAttribute("userName").equals(userInfoform.getUserName())
				&& session.getAttribute("password").equals(userInfoform.getPassword())) {
			model.addAttribute("msg", "一つ以上変更してください");
			return "userInfoChange";
		}

		List<UserInfo> userInfo2 = userInfoService.findIdUserNamePass((Integer) session.getAttribute("userId"),
				userInfoform.getLoginId(),
				userInfoform.getUserName());

		if (userInfo2 != null) {

			model.addAttribute("msg", "IDまたはユーザー名が既に存在します。");

			return "userInfoChange";

		} else {

			session.setAttribute("LoginId", userInfoform.getLoginId());
			session.setAttribute("UserName", userInfoform.getUserName());
			session.setAttribute("Password", userInfoform.getPassword());

			//model.addAttribute("list",list);
			return "userInfoConfirm";

		}

	}

	@RequestMapping("/userInfoResult")
	public String userInfoConfirm(Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		String loginId = (String) session.getAttribute("LoginId");
		String userName = (String) session.getAttribute("UserName");
		String password = (String) session.getAttribute("Password");
		Integer userId = (Integer) session.getAttribute("userId");

		session.setAttribute("loginId", loginId);
		session.setAttribute("userName", userName);
		session.setAttribute("password", password);
		session.setAttribute("userId", userId);

		userInfoService.update(loginId, userName, password, userId);

		UserInfo list = userInfoService.findIdIdName(userId, loginId, userName);

		session.setAttribute("list", list);

		return "userInfoResult";

	}

	@RequestMapping("/userInfoResult1")
	public String userInfoResult1(@ModelAttribute("userInfo") Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		return "menu";

	}

	@RequestMapping("/menu")
	public String menu(Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		return "menu";

	}

	@RequestMapping("/logout")
	public String logout(Model model) {

		if (session.getAttribute("userName") == null || session.getAttribute("userName").toString().isEmpty()) {
			return "top";
		}

		session.invalidate();
		return "logout";

	}

}
