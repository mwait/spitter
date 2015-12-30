package spittr.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import spittr.Spittle;

@Service
public class SpittleRepositoryImpl implements  SpittleRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#count()
	 */
	public long count() {
		return findAll().size();
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findRecent()
	 */
	public List<Spittle> findRecent() {
		return findRecent(10);
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findRecent(int)
	 */
	public List<Spittle> findRecent(int count) {
		return (List<Spittle>) entityManager.createQuery("select s from Spittle s order by s.postedTime desc")
				.setMaxResults(count).getResultList();
	}

	public List<Spittle> findSpittles(long max, int count) {
		return (List<Spittle>) entityManager.createQuery("select s from Spittle s order by s.postedTime desc")
				.setMaxResults(count).getResultList();
	}
	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findOne(long)
	 */
	public Spittle findOne(long id) {
		return entityManager.find(Spittle.class, id);
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#save(spittr.Spittle)
	 */
	public Spittle save(Spittle spittle) {
		entityManager.persist(spittle);
		return spittle;
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findBySpitterId(long)
	 */
	public List<Spittle> findBySpitterId(long spitterId) {
		return (List<Spittle>) entityManager
				.createQuery(
						"select s from Spittle s, Spitter sp where s.spitter = sp and sp.id=? order by s.postedTime desc")
				.setParameter(1, spitterId).getResultList();
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#delete(long)
	 */
	public void delete(long id) {
		entityManager.remove(findOne(id));
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findAll()
	 */
	public List<Spittle> findAll() {
		return (List<Spittle>) entityManager.createQuery("select s from Spittle s").getResultList();
	}
}
