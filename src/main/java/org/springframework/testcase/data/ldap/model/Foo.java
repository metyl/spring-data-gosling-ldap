package org.springframework.testcase.data.ldap.model;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entry(objectClasses = {"person", "top"})
public class Foo {
    @Id
    @JsonIgnore
	/**
	 * Tell Jackson to ignore since Jackson has no way to write this (and avoid
	 * writing custom serialization)
	 */
    private Name dn;

    /**
     * An actual property that will be rendered by Jackson
     * @return
     */
    public String getId() {
    	return getDn().toString();
    }

    public Name getDn() {
        return this.dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }
}
