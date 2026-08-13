package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.RecipeMapper;
import com.sist.web.vo.RecipeDetailVO;
import com.sist.web.vo.RecipeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
	private final RecipeMapper rMapper;
	private int ROWSIZE = 12;
	@Override
	public List<RecipeVO> recipeListData(int page) {
		
		int start = (page*ROWSIZE)-ROWSIZE;
		
		return rMapper.recipeListData(start);
	}
	@Override
	public int recipeTotalPage() {
		int totalpage = (int)(Math.ceil(rMapper.recipeCount()/(double)ROWSIZE));
		return totalpage;
	}
	
	@Override
	public int recipeCount() {

		return rMapper.recipeCount();
	}

	@Override
	public RecipeDetailVO recipeDtailData(int no) {
		rMapper.hitIncrement(no);
		return rMapper.recipeDtailData(no);
	}

	@Override
	public int[] recipePages(int page) {
		
		int totalpage = recipeTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((page-1)/BLOCK*BLOCK)+1;
		int endPage = ((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage > totalpage)
			endPage = totalpage;
		
		int pages[] = {page,totalpage,startPage,endPage};
		
		return pages;
	}


}
