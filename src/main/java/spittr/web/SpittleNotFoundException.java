package spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Spittle nie został znaleziony ")
public class SpittleNotFoundException extends RuntimeException {

}
