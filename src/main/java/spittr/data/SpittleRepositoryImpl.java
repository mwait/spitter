package spittr.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spittr.Spittle;

@Service
public class SpittleRepositoryImpl implements  SpittleRepository {

	@PersistenceContext(unitName="spittr")
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
	@Transactional
	public List<Spittle> findRecent(int count) {
		return (List<Spittle>) entityManager.createQuery("select s from Spittle s order by s.time desc")
				.setMaxResults(count).getResultList();
	}
	@Transactional
	public List<Spittle> findSpittles(long max, int count) {
		return (List<Spittle>) entityManager.createQuery("select s from Spittle s order by s.time desc")
				.setMaxResults(count).getResultList();
	}
	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findOne(long)
	 */
	@Transactional
	public Spittle findOne(long id) {
		return entityManager.find(Spittle.class, id);
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#save(spittr.Spittle)
	 */
	@Transactional
	public Spittle save(Spittle spittle) {
		entityManager.persist(spittle);
		return spittle;
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findBySpitterId(long)
	 */
	@Transactional
	public List<Spittle> findBySpitterId(long spitterId) {
		return (List<Spittle>) entityManager
				.createQuery(
						"select s from Spittle s, Spitter sp where s.spitter = sp and sp.id=? order by s.time desc")
				.setParameter(1, spitterId).getResultList();
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#delete(long)
	 */
	@Transactional
	public void delete(long id) {
		entityManager.remove(findOne(id));
	}

	/* (non-Javadoc)
	 * @see spittr.data.SpittleRepository#findAll()
	 */
	@Transactional
	public List<Spittle> findAll() {
		return (List<Spittle>) entityManager.createQuery("select s from Spittle s").getResultList();
	}


}
