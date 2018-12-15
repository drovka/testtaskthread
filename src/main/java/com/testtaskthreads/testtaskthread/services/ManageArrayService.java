/**
 * 
 */
package com.testtaskthreads.testtaskthread.services;

import java.util.List;
import java.util.Map;

import com.testtaskthreads.testtaskthread.entity.JsonAnswerEntity;


/**
 * @author Vova
 *
 */
public interface ManageArrayService {
	
	public String combine(String str1, String str2);
	
	public Map<Long,String> processArray(List<Long> values);
	
	public Iterable<JsonAnswerEntity> loadAll();
	
	public void deleteAll();
	
	public List<JsonAnswerEntity> loadEntityByLong(List<Long> keys);
	
	public Map<Long, String> buildAnswer(List<Long> values);

}
