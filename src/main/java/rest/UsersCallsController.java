package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.model.call.Call;

/**
 * 
 * @author pfelipe
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "users/calls")
public class UsersCallsController {
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> createCall(@RequestBody Call call) {
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}
