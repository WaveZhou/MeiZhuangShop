package com.zx.common.pager;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/** 
 * 标签处理类
 */
public class PageTag extends TagSupport {

	private String submitUrl;//请求地址
	private PageModel pageModel;//封装页码  每页显示的记录   总的记录
	
    //当页面只开始解析  开始标签  的时候会执行 doStartTag方法
	public int doStartTag() throws JspException {

		StringBuffer page = new StringBuffer();
		try {
			JspWriter write =  this.pageContext.getOut();
			
			HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
			
			Enumeration<String> names = request.getParameterNames();
			StringBuffer params = new StringBuffer();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				if(name.equals("pageIndex")) {
					continue;
				}
				String value = request.getParameter(name);
				params.append(name).append("=").append(value).append("&");
			}
			
			submitUrl = submitUrl + "?" + params.toString();
			
			//判断总数量 :如果获取到的总记录数 小于 每页需要显示的记录数，则不需要做分页    比如 每页显示 8条  但是总共只查询到5条数据 
			if(pageModel.getTotalNum()>pageModel.getPageSize()){
				//计算尾页      
				int totalPage = pageModel.getTotalNum() % pageModel.getPageSize() == 0 ?pageModel.getTotalNum() /  pageModel.getPageSize() : pageModel.getTotalNum() /  pageModel.getPageSize() +1;
				
				
				//判断首页是否可以点击
				if(pageModel.getPageIndex()==1){
					//当前页码在第一页
					page.append("<a href='#'>首页</a>");
					page.append("<a href='#'>上一页</a>");
					
					page.append("<a href='"+submitUrl+"pageIndex="+(pageModel.getPageIndex()+1)+"'>下一页</a>");
					
					page.append("<a href='"+submitUrl+"pageIndex="+totalPage+"'>尾页</a>");
					
					page.append("&nbsp; <span>第&nbsp;"+pageModel.getPageIndex()+"&nbsp;页/共&nbsp; "+totalPage+"页 ，共 "+pageModel.getTotalNum()+"条数据 </span>");
					
				}else if(pageModel.getPageIndex() == totalPage){
					//当前页码在尾页
					page.append("<a href='"+submitUrl+"pageIndex="+1+"'>首页</a>");
					page.append("<a href='"+submitUrl+"pageIndex="+(pageModel.getPageIndex()-1)+"'>上一页</a>");
					
					page.append("<a href='#'>下一页</a>");
					
					page.append("<a href='#'>尾页</a>");
					
					page.append("&nbsp; <span>第&nbsp;"+pageModel.getPageIndex()+"&nbsp;页/共&nbsp; "+totalPage+"页 ，共 "+pageModel.getTotalNum()+"条数据 </span>");
					
				}else{
					//当前页码在中间
					page.append("<a href='"+submitUrl+"pageIndex="+1+"'>首页</a>");
					page.append("<a href='"+submitUrl+"pageIndex="+(pageModel.getPageIndex()-1)+"'>上一页</a>");
					
                    page.append("<a href='"+submitUrl+"pageIndex="+(pageModel.getPageIndex()+1)+"'>下一页</a>");
					
					page.append("<a href='"+submitUrl+"pageIndex="+totalPage+"'>尾页</a>");
					
					page.append("&nbsp; <span>第&nbsp;"+pageModel.getPageIndex()+"&nbsp;页/共&nbsp; "+totalPage+"页 ，共 "+pageModel.getTotalNum()+"条数据 </span>");
				}	
			}
			
			write.print(page.toString());
			
			write.flush();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
	

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		
		this.pageModel = pageModel;
	}
	
	 
}
