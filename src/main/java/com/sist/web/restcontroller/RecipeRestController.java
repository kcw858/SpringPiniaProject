package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecipeRestController {
	private final RecipeService rService;
	
	@GetMapping("/recipe/list_vue")
	public ResponseEntity<Map> recipe_list_vue(@RequestParam(value="page",required=false) String page)
	{
		Map map = new HashMap();
		
		if(page == null)
			page ="1";
		
		try 
		{
			List<RecipeVO> list = rService.recipeListData(Integer.parseInt(page));
			int[] pages = rService.recipePages(Integer.parseInt(page));
			int count = rService.recipeCount();
			
			map.put("list", list);
			map.put("pages", pages);
			map.put("count", count);
			
		}catch(Exception ex)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/recipe/detail_vue")
	public ResponseEntity<Map> recipe_detail_vue(@RequestParam("no") int no)
	{
		Map map = new HashMap();
		try
		{
			RecipeDetailVO vo = rService.recipeDtailData(no);
			List<String> mList = new ArrayList<String>();
			List<String> iList = new ArrayList<String>();
			
			String[] datas = vo.getFoodmake().split("\n");
			
			for(String data : datas)
			{
				StringTokenizer st = new StringTokenizer(data,"^");
				mList.add(st.nextToken());
				iList.add(st.nextToken());
			}
			
			map.put("vo", vo);
			map.put("mList", mList);
			map.put("iList", iList);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(map);
	}
}
