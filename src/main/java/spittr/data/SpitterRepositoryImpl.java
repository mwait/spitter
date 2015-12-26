package spittr.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import spittr.Spitter;
@Service
public class SpitterRepositoryImpl implements SpitterRepository {
private static List<Spitter> spitterList = new ArrayList<Spitter>();
	
	public Spitter save(Spitter spitter) {
		spitterList.add(spitter);
		return spitter;
	}

	public Spitter findByUsername(String username) {
		for(Spitter s:spitterList){
			if(s.getUsername().equals(username)) return s;
		}
		return null;
	}

}
