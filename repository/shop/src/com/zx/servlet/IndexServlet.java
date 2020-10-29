package com.zx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zx.common.pager.PageModel;
import com.zx.common.util.CookieUtil;
import com.zx.service.ArticleService;
import com.zx.service.ArticleTypeService;
import com.zx.service.UserService;
import com.zx.service.impl.ArticleServiceImpl;
import com.zx.service.impl.ArticleTypeServiceImpl;
import com.zx.service.impl.UserServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;
import com.zx.vo.Article;
import com.zx.vo.ArticleType;
import com.zx.vo.User;

/**
 * 展示首页商品以及商品类型等相关信息
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 创建服务层对象
		ArticleService articleService =  new ServiceProxyUtils().bindService(new ArticleServiceImpl());
		ArticleTypeService articleTypeService =  new ServiceProxyUtils().bindService(new ArticleTypeServiceImpl());
		UserService userService =  new ServiceProxyUtils().bindService(new UserServiceImpl());
		
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("session_user");
		

		if (user == null) {
			// 如果session不存在的话，查找cookie中是否有用户信息
			String[] infos = null;
			Cookie cookie = CookieUtil.getCookie(request, "rem");
			if (cookie != null) {
				// 如果cookie中有用户信息的话就拿到用户信息存在info数组中
				String userInfo = cookie.getValue();
				infos = userInfo.split("_");
			}
			if(infos !=null && userService.findUserActive(infos[0], infos[1]) != null) {
				user = userService.findUserActive(infos[0], infos[1]);
//				user.setLoginName(infos[0]);
//				user.setPassword(infos[1]);
//				user.setId(infos[2]);
				session.setAttribute("session_user", user);
				
			}						
		}
		// 获取所有的一级物品类型
		List<ArticleType> firstTypes = articleTypeService.findAllFirstArticleTypes();
		request.setAttribute("firstTypes", firstTypes);

		// 由于用户刚进入首页的时候并没有选择物品类型 从firstTypes中第一个作为查询条件 typeCode不为空说明用户已经选择了物品类型
		String typeCode = request.getParameter("typeCode");

		// 获取用户输入的查询关键字
		String keyword = request.getParameter("keyword");

		// 将用户输入的查询关键字以及选择的物品类型存起来
		request.setAttribute("typeCode", typeCode);
		request.setAttribute("keyword", keyword);

		if (typeCode != null && !typeCode.equals("")) {
			// 获取二级类型商品信息 必须截取用户选择typeCode的前四位
			List<ArticleType> secondTypes = articleTypeService.findAllSecondArticleTypes(typeCode.substring(0, 4));
			request.setAttribute("secondTypes", secondTypes);

			if (typeCode.length() <= 4) {
				String chooseTypeCode = typeCode;
				request.setAttribute("chooseTypeCode", chooseTypeCode);
			} else {
				String chooseTypeCode = typeCode.substring(0, 4);
				request.setAttribute("chooseTypeCode", chooseTypeCode);
				String chooseTypeCode2 = typeCode;
				request.setAttribute("chooseTypeCode2", chooseTypeCode2);
			}
		}

		// 获取页码值
		String pageIndex = request.getParameter("pageIndex");

		// 创建分页实体，进行分页查询
		PageModel pageModel = new PageModel();
		if (pageIndex != null && !pageIndex.equals("")) {
			pageModel.setPageIndex(Integer.valueOf(pageIndex));
		}

		// 根据物品类型以及用户输入的关键字获取物品信息
		List<Article> articles = articleService.findArticlesByCode(typeCode, keyword, pageModel);

		request.setAttribute("articles", articles);
		// 将pageModel存放在request中
		request.setAttribute("pageModel", pageModel);

		request.getRequestDispatcher("/WEB-INF/view/front/main.jsp").forward(request, response);
		
	}

}
