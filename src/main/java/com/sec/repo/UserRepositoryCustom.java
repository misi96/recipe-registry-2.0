package com.sec.repo;

import java.util.Map;


public interface UserRepositoryCustom {

	
	boolean UserHasEvents(long userID);

	void setEventIndicatorOfUserInMemory(long userID,boolean eventIndicator);
	
	
	
}