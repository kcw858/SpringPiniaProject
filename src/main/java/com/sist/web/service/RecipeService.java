package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.RecipeDetailVO;
import com.sist.web.vo.RecipeVO;

/*
 *  user ====== DispatcherServlet
 *  					|
 *  				service
 *  					|
 *  				Mapper
 *  					|
 *  				  오라클
 */
public interface RecipeService {
	
	public List<RecipeVO> recipeListData(int page);
	
	public int recipeCount();

	public RecipeDetailVO recipeDtailData(int no);
	
	public int recipeTotalPage();
	
	public int[] recipePages(int page);
}
