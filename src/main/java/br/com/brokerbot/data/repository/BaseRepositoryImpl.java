package br.com.brokerbot.data.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements IBaseRepository<T, ID> {

	private final EntityManager entityManager;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;

	}

	@Transactional
	public void insertAll(List<T> entity) {
		if (entity == null) {
			return;
		}
		entity.parallelStream().forEach((e) -> {
			entityManager.persist(e);
		});

	}

}