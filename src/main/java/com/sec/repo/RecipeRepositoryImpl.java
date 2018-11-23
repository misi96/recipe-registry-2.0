package com.sec.repo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sec.entity.Recipe;

@Repository
@Transactional(readOnly = true)
public class RecipeRepositoryImpl implements RecipeRepositoryCustom{
	
	@PersistenceContext
    EntityManager entityManager;
	
	static TreeMap<String, Long> namesTreeMap = null;

	static private boolean initialized=false;
	
	
	@PostConstruct
	void init() {
		initialized =	true;
	List<String> namesList = entityManager.createQuery("SELECT rec.name FROM Recipe rec", String.class).getResultList();
	Map<String, Long> namesMap = 
			namesList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
	namesTreeMap = new TreeMap<String, Long>(namesMap);
	
	}
	
	@Override
	public Map<String, Long> GetNamesStartingWith(String startingWith) {
	
		return namesTreeMap.subMap(startingWith, startingWith + Character.MAX_VALUE );
		
	}
	
	
	@PreUpdate
	@PrePersist
	public void addName(Recipe recipe) {
		
		if(initialized == true) {
		String recipeName = recipe.getName();
		Long oldCounter = namesTreeMap.get(recipeName);
		if (oldCounter != null)
		namesTreeMap.put( recipeName, oldCounter++);
		else {
			namesTreeMap.put( recipeName, 1L);
	}
		}
	}
	
	@PreRemove
	public void deleteName(Recipe recipe) {

		if(initialized==true) {
		String recipeName = recipe.getName();
		long oldCounter = namesTreeMap.get(recipeName);
		if(oldCounter != 1 )
		namesTreeMap.put(recipe.getName(),--oldCounter);
		else {
			
			namesTreeMap.remove(recipeName);
		}
		
		
		}
	}
	
	
}
