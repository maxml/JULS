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
		String[] subqueries = query.split(" ");
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
}
