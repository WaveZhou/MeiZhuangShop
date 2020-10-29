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
 * 添加商品至购物车
 */
@WebServlet("/addToShopCar.do")
public class AddToShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddToShopCarServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//创建服务层对象
		ShopCarService service =  new ServiceProxyUtils().bindService(new ShopCarServiceImpl());
		
	    //获取购买的商品数量、id
		String buyNum = request.getParameter("buyNum");
		String shopId = request.getParameter("id");
		
		//从session中获取用户信息
		User user = (User)request.getSession().getAttribute("session_user");
		
		
		//判断商品是否在当前用户的购物中，如果已存在，进行数量的增加，否则添加至购物车
		//判断购物车中是否有该商品信息
		ShopCar shopCar = service.getShopCarByArticleIdAndUserId(shopId,Integer.valueOf(user.getId()));
		
		if(shopCar==null){
			//说明该商品不在用户的购物车中
			//往数据中插入数据
			service.addShop(shopId,Integer.valueOf(user.getId()),buyNum);
		}else{
			//已经存在,更新该商品在购物车中的数量
			shopCar.setArticleId(Integer.valueOf(shopId));
			shopCar.setUserId(Integer.parseInt(user.getId()));
			shopCar.setBuyNum(shopCar.getBuyNum()+Integer.valueOf(buyNum));
			//进行更新操作
			service.updateShop(shopCar);
			
		}
		//展示购物车中商品信息
		response.sendRedirect(request.getContextPath()+"/showShopCar.do");
		
		
	}
}
