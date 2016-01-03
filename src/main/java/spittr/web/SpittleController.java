package spittr.web;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpitterRepository;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	// Long.toString(Long.MAX_VALUE);
	private SpittleRepository spittleRepository;
	private SpitterRepository spitterRepository;
	
	@Autowired
	public void setSpitterRepository(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	// do modelu zostanie dołączona lista pod nazwą spittleList
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "20") int count, Model model) {
		model.addAttribute("spittle", new Spittle());
		model.addAttribute("spittleList",spittleRepository.findSpittles(max, count));
		return "spittles";
		
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showSpittle(@RequestParam("spittle_id") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		Spittle spittle = spittleRepository.findOne(spittleId);
		if (spittle == null) {
			throw new SpittleNotFoundException();
		}
		// dodany atrybut otrzyma automatycznie nazwę spittle
		model.addAttribute(spittle);
		return "spittle";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveSpittle( Model model, Spittle form, Principal principal) throws Exception {
		form.setTime(new Date());
		spittleRepository
				.save(form);
		return "redirect:/spittles";
	}

	/*
	 * metoda oznaczona @ExceptionHandler obsługuje wyjątki rzucone w dowolnej
	 * metodzie zdefiniowanej w tym kontrolerze
	 * 
	 * @ExceptionHandler(DuplicateSpittleException.class) public String
	 * handleDuplicateSpittle() { return "error/duplicate"; }
	 */
}
