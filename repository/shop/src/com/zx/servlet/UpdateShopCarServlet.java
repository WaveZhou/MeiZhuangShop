package com.zx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.ShopCarService;
import com.zx.service.impl.ShopCarServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;
import com.zx.vo.ShopCar;
import com.zx.vo.User;


/**
 * 更新购物车中商品信息
 *  
 **/
@WebServlet("/updateShopCar.do")
public class UpdateShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateShopCarServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//创建服务层对象
		ShopCarService service =  new ServiceProxyUtils().bindService(new ShopCarServiceImpl());
		
	   //获取需要更新的商品id
		String articleId = request.getParameter("articleId");
		//从session中获取用户信息
		User user = (User)request.getSession().getAttribute("session_user");
		//获取商品数量
		String buyNum = request.getParameter("buyNum");
		
		if(articleId==null||articleId.equals("")||buyNum==null||buyNum.equals("")){
			//重定向至首页
			response.sendRedirect(request.getContextPath()+"/index");
		}else{
			ShopCar shopCar = new ShopCar();
			shopCar.setArticleId(Integer.valueOf(articleId));
			shopCar.setUserId(Integer.parseInt(user.getId()));
			shopCar.setBuyNum(Integer.valueOf(buyNum));
			service.updateShop(shopCar);

			//展示购物车中商品信息
			response.sendRedirect(request.getContextPath()+"/showShopCar.do");
		}
		
		
	}

}
