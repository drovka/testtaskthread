/**
 * 
 */
package com.testtaskthreads.testtaskthread.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.testtaskthreads.testtaskthread.entity.JsonAnswerEntity;

/**
 * @author Vova
 *
 */
public interface JsonAnswerRepository extends CrudRepository<JsonAnswerEntity, Long> {
	
	List<JsonAnswerEntity> findAnswerByKeyValue(Long keyValue);

}
