package spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import spittr.Spittle;

@Service
public class SpittleRepositoryImpl implements SpittleRepository {

	public List<Spittle> findSpittles(long max, int count) {
		return createSpittleList(count);
	}
	
	public Spittle findOne(long spittleId) {
		return new Spittle("Spittle ",new Date());
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date(),0.23,0.18));
		}
		return spittles;
	}
}
