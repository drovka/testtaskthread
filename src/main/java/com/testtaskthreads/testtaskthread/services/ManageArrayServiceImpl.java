/**
 * 
 */
package com.testtaskthreads.testtaskthread.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testtaskthreads.testtaskthread.entity.JsonAnswerEntity;
import com.testtaskthreads.testtaskthread.repository.JsonAnswerRepository;

import rx.Observable;
import rx.functions.Func2;
import rx.functions.Action1;

/**
 * @author Vova
 *
 */
@Service
public class ManageArrayServiceImpl implements ManageArrayService {
	
	private TwoMethodsService tmService;
	
	@Autowired
	private JsonAnswerRepository repository;
	
	Func2<String, String, String> combine = 
			new Func2<String, String, String>() {
				@Override
				public String call(String str1, String str2) {
					return str1.concat(" ").concat(str2);
				}
			};
	
	
	public ManageArrayServiceImpl() {}
	
	//public ManageArrayServiceImpl(JsonAnswerRepository repository) {
	
		//this.repository = repository;
		//	tmService = new TwoMethodsServiceImpl();
	//	jaDao = new JsonAnswerDAO();
	//}

	/* This method should be called after ferstMethod and secondMethod
	 * methods end all their operations
	 * (non-Javadoc)
	 * @see com.testtaskthreads.testtaskthread.services.ManageArrayService#combine(java.lang.String, java.lang.String)
	 */	
	@Override
	public String combine(String str1, String str2) {
		
		return str1.concat(" ").concat(str2);
		
	}
	
	/**
	 * Этот метод должен быть Observable. Также не уверен в 
	 * использовании цикла, думаю, можно было обойтись без него
	 * @param values
	 * @return
	 */
	public Map<Long, String> buildAnswer(List<Long> values) {

		
		Map<Long, String> result = new HashMap<Long, String>();
		
		
		for(Long val : values) {
			
			Observable<String> valFromFirstMethod =
						Observable.just(tmService.firstMethod(val));
			
			Observable<String> valFromSecondMethod = 
						Observable.just(tmService.secondMethod(val));
			
			Action1<String> storeValueToDatabase = 
					new Action1<String>() {
						@Override
						public void call(String str) {
								
								JsonAnswerEntity jae = new JsonAnswerEntity(
										val, str, System.currentTimeMillis());
								if(repository.findAnswerByKeyValue(jae.getKeyValue()).isEmpty()) {
									repository.save(jae);
								}
								result.put(val, jae.getMessage());
						}
					};
			
			Observable.zip(valFromFirstMethod, valFromSecondMethod, combine)
						.subscribe(storeValueToDatabase);
		}
		return result;
	}
	
		
	// Not multithreading 
	@Override
	@Transactional
	public Map<Long,String> processArray(List<Long> values) {
		tmService = new TwoMethodsServiceImpl();
		
		// list should be checked for dublicates there
		
		Map<Long, String> result = new HashMap<Long, String>();
		
		for(Long vl : values) {
			
			String combStr = combine(tmService.firstMethod(vl),
								tmService.secondMethod(vl));
			
			JsonAnswerEntity jae = new JsonAnswerEntity(
							vl, combStr, System.currentTimeMillis());
			
			if(repository.findAnswerByKeyValue(jae.getKeyValue()).isEmpty()) {
				repository.save(jae);
			}
			
			result.put(vl, combStr);
			
		}
		
		return result;
		
	}
	
	public Iterable<JsonAnswerEntity> loadAll() {
		return repository.findAll();
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public List<JsonAnswerEntity> loadEntityByLong(List<Long> keys) {
		
		List<JsonAnswerEntity> answr = new ArrayList<JsonAnswerEntity>();
		
		if (keys == null || keys.size() <= 0) {
			answr =  (List<JsonAnswerEntity>) loadAll();
		}
		else {
			
			for(Long k : keys) {
				answr.addAll(repository.findAnswerByKeyValue(k));
			}
			
		}
		return answr;
	}

}
