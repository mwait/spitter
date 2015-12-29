package spittr.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Jeśli wyjątek DuplicateSpittleException zostanie wyrzucona przez metodę dowolnego kontrolera,
 * nastąpi wywołanie metody duplicateSpittleHandler
 */
@ControllerAdvice
public class AppWideExceptionHandler {
	@ExceptionHandler(DuplicateSpittleException.class)
	public String duplicateSpittleHandler() {
		return "error/duplicate";
	}
}
