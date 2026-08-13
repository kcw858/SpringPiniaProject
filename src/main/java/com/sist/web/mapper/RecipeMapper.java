package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.web.vo.*;

@Mapper
@Repository
public interface RecipeMapper {
	/*
	<select id="recipeListData" resultType="com.sist.web.vo.RecipeVO" parameterType="int">
		SELECT no,poster,title,chef
		FROM recipe
		<include refid="where-no"/>
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	*/
	public List<RecipeVO> recipeListData(int start);
	
	
	/*
	<select id="recipeCount" resultType="int">
		SELECT COUNT(*) FROM recipe
		<include refid="where-no"/>
	</select>
	 */
	public int recipeCount();
	
	//조회수 증가
	@Update("UPDATE SET recipe SET "
			+ "hit = hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(@Param("no") int no);
	
	//상세보기
	@Select("SELECT * FROM recopedetail "
			+ "WHERE no=#{no}")
	public RecipeDetailVO recipeDtailData(@Param("no")int no);
}
