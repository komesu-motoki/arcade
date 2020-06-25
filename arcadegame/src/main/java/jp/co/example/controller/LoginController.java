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

import jp.co.example.controller.form.LoginForm;
import jp.co.example.entity.UserInfo;
import jp.co.example.service.UserInfoService;

@Controller
public class LoginController {

	@Autowired
	HttpSession session;

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("/top")
	public String top(Model model) {

		return "top";

	}

	@RequestMapping("/login")
	public String login(Model model) {

		return "login";

	}

	@RequestMapping("/login1")
	public String login1(@Validated @ModelAttribute("login") LoginForm loginform, BindingResult result, Model model,
			HttpSession session) {

		if (result.hasErrors()) {
			return "login";
		}

		List<UserInfo> userInfo1 = userInfoService.findIdPass(loginform.getLoginId(), loginform.getPassword());

		String loginId = "";

		if (userInfo1 == null) {

			model.addAttribute("msg", "入力したIDとPASSは存在しません");

			return "login";

		} else {

			loginId = userInfo1.get(0).getLoginId();

			userInfoService.updatelogin_date(loginId);

			session.setAttribute("loginId", loginform.getLoginId());
			session.setAttribute("userId", userInfo1.get(0).getUserId());
			session.setAttribute("userName", userInfo1.get(0).getUserName());
			session.setAttribute("password", loginform.getPassword());

			session.setAttribute("list", userInfo1.get(0));

			return "menu";

		}

	}

}
