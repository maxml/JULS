package com.juls.rest.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.juls.model.Good;
import com.juls.persist.GoodDAOImpl;

@Service
public class GoodsService {
	
	public Set<Good> getSearchResult(String query){
		Set<String> subqueries = new LinkedHashSet<String>();
		if (!query.isEmpty()){  
			for(String s : query.split(" ")){
				subqueries.add(s);
			}
		}
		else
			subqueries.add(query);
		
		Set<Good> result = new LinkedHashSet<Good>(20);
		List<Good> allGoods = new GoodDAOImpl().getAll();
		for(String s : subqueries){
			for(Good g : allGoods){
				if (s.isEmpty())
					result.add(g);
				else if (g.getName().toLowerCase().contains(s)){
					result.add(g);
				}
			}
		}
		return result;
	}
	
	public List<Good> getGoodsByCategory (String category){
		return new GoodDAOImpl().getAllByCategory(category);
	}
	
	public Set<Good> doSort(String searchQuery, String sortBy, String way, String category){
		Set<Good> result = new LinkedHashSet<Good>(20);
		Set<String> subqueries = new LinkedHashSet<String>();
		if (!searchQuery.equals("null")){
			for(String s : searchQuery.split(" ")){
				subqueries.add(s);
			}
		}
		else
		{
			subqueries.add("");
		}
		List<Good> sortedGoodsList = null;
		if (category.equals("all"))
			sortedGoodsList = new GoodDAOImpl().getAllSortedBy(sortBy, way);
		else{
			sortedGoodsList = new GoodDAOImpl().getAllFromCategorySortedBy(category, sortBy, way);
		}
		for(String s : subqueries){
			for (Good g : sortedGoodsList){
				if (g.getName().toLowerCase().contains(s)){
					result.add(g);
				}
			}
		}
		return result;
	}
}
