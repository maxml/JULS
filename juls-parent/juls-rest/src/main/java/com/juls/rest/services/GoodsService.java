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
		for(String s : query.split(" ")){
			subqueries.add(s);
		}
		
		Set<Good> result = new LinkedHashSet<Good>(20);
		List<Good> allGoods = new GoodDAOImpl().getAll();
		for(String s : subqueries){
			for(Good g : allGoods){
				if (g.getName().toLowerCase().contains(s)){
					result.add(g);
				}
			}
		}
		return result;
	}
	
	public Set<Good> doSort(String searchQuery, String parameter, String way){
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
		for(String s : subqueries){
			for (Good g : new GoodDAOImpl().getAllSortedBy(parameter, way)){
				if (g.getName().toLowerCase().contains(s)){
					result.add(g);
				}
			}
		}
		return result;
	}
}
