package com.zx.servlet;

import java.io.IOException;
import java.util.List;
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
 * 展示当前用户购物车中商品信息
 */
@WebServlet("/showShopCar.do")
public class ShowShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowShopCarServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//创建服务层对象
		ShopCarService service =  new ServiceProxyUtils().bindService(new ShopCarServiceImpl());
		//从session中获取用户信息
		User user = (User)request.getSession().getAttribute("session_user");
		
	    //根据用户id获取购物车商品信息
		List<ShopCar> shopCars = service.getShopCarByUserId(user);
		request.setAttribute("shopCars", shopCars);
		
		//计算总金额
		double totalPrice = 0.0;
		for(ShopCar shopCar : shopCars){

			totalPrice += shopCar.getArticle().getDiscountPrice() * shopCar.getBuyNum();
		}
		//将总金额存放在request中
		request.setAttribute("totalPrice", totalPrice);
	    
		//跳转至展示购物车中商品详情页面
		request.getRequestDispatcher("/WEB-INF/view/front/shopcar.jsp").forward(request, response);
		
	}

}
