package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(@RequestPart("profilePicture")  MultipartFile profilePicture, @Valid Spitter spitter, Errors errors, RedirectAttributes model) throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		spitterRepository.save(spitter);
		
		if(!profilePicture.isEmpty()){ profilePicture.transferTo(
				new File("c:\\tmp\\spittr\\uploads\\" + "image_"+profilePicture.getOriginalFilename()));}
		//taki zapis jest bezpieczniejszy niż konkatenacja
		model.addAttribute("username", spitter.getUsername());
		//spitterId został by dołączony do modelu w postaci parametru (bo nie ma odwzorowania w url)
		//model.addAttribute("spitterId", spitter.getId());
		//umożliwia dodanie atrybutu jednorazowego przy przekierowaniu (przez sesję)
		model.addFlashAttribute("spitter", spitter);
		return "redirect:/spitter/{username}";
	}

	/*
	 * @RequestMapping(value = "/register", method = POST) public String
	 * processRegistration(Spitter spitter) { spitterRepository.save(spitter);
	 * return "redirect:/spitter/" + spitter.getUsername(); }
	 */

	@RequestMapping(value = "/{username}", method = GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}
}