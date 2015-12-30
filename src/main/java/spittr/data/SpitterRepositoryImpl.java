package spittr.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import spittr.Spitter;

@Service
public class SpitterRepositoryImpl implements SpitterRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public long count() {
		return findAll().size();
	}

	public Spitter save(Spitter spitter) {
		entityManager.persist(spitter);
		return spitter;
	}

	public Spitter findOne(long id) {
		return entityManager.find(Spitter.class, id);
	}

	public Spitter findByUsername(String username) {
		return (Spitter) entityManager.createQuery("select s from Spitter s where s.username=?")
				.setParameter(1, username).getSingleResult();
	}

	public List<Spitter> findAll() {
		return (List<Spitter>) entityManager.createQuery("select s from Spitter s").getResultList();
	}

}
