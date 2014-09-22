package com.juls.rest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juls.model.Good;
import com.juls.persist.GoodDAOImpl;

@Service
public class GoodsService {
	
	public List<Good> getSearchResult(String query){
		List<Good> allGoods = new GoodDAOImpl().getAll();
		List<Good> result = new ArrayList<Good>(20);
		for(Good g : allGoods){
			if (g.getName().toLowerCase().contains(query)){
				result.add(g);
			}
		}
		return result;
	}
}
