package com.ddlscript.def.controllers;

/**
 * Represents a controller that is capable of deleting a model based upon it's identifier value.
 *
 * @param <MODEL>
 * 		Data type of the model being retrieved.
 */
public interface DeletableController<MODEL> {

	/**
	 * Attempts to delete the given model from the repository.
	 *
	 * @param withModel
	 * 		The model to retrieve.
	 */
	void delete(MODEL withModel);

}
