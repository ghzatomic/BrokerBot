package br.com.brokerbot.data.repository;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author Martins
 */
@NoRepositoryBean
public interface IBaseRepository <T, ID extends Serializable> extends JpaRepository<T, ID> {

	public void insertAll(List<T> entity);

}