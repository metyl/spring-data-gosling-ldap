package org.springframework.testcase.data.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.testcase.data.ldap.model.Foo;
import org.springframework.testcase.data.ldap.model.FooRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoosController {
	@Autowired
	FooRepository foos;

	@RequestMapping("/working/foos")
	public Iterable<Foo> foos() {
		return foos.findAll();
	}
}
