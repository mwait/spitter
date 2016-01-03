package spittr.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spittr.Spitter;


@Service
public class SpitterRepositoryImpl implements SpitterRepository {

	@PersistenceContext(unitName="spittr")
	private EntityManager entityManager;

	public long count() {
		return findAll().size();
	}
	@Transactional
	public Spitter save(Spitter spitter) {
		entityManager.persist(spitter);
		return spitter;
	}
	@Transactional
	public Spitter findOne(long id) {
		return entityManager.find(Spitter.class, id);
	}
	@Transactional
	public Spitter findByUsername(String username) {
		Spitter spittDao = (Spitter) entityManager.createQuery("select s from Spitter s where s.username=:username")
				.setParameter("username", username).getSingleResult();
		//Spitter spitter = new Spitter(spittDao.getId(), spittDao.getUsername(), spittDao.getPassword(), spittDao.getFullName(), spittDao.getFullName());
		return spittDao;
	}
	@Transactional
	public List<Spitter> findAll() {
		return (List<Spitter>) entityManager.createQuery("select s from Spitter s").getResultList();
	}

}
