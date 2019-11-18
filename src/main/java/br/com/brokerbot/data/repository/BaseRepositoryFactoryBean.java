package br.com.brokerbot.data.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * This factory bean replaces the default implementation of the repository interface 
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
  extends JpaRepositoryFactoryBean<R, T, I> {

  public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
		// TODO Auto-generated constructor stub
	}

protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
	  
    return new BaseRepositoryFactory(entityManager);
  }

  private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public BaseRepositoryFactory(EntityManager entityManager) {
      super(entityManager);

      this.entityManager = entityManager;
    }

    protected Object getTargetRepository(RepositoryMetadata metadata) {

      return new BaseRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

      // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
      //to check for QueryDslJpaRepository's which is out of scope.
      return IBaseRepository.class;
    }
  }
}