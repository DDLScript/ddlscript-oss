package com.ddlscript.sdk;

/**
 * Represents a class used to build an instance of another class.
 */
public interface Buildable {

	/**
	 * An immutable object that is created from the properties that have been set on the buildable class.
	 *
	 * @return
	 * 		New Instance.
	 */
	Object build();
}
