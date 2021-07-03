package com.ddlscript.sdk;

import java.util.function.Consumer;

/**
 * Represents a Builder.
 *
 * @param <BUILDER>
 *     the builder type (this)
 * @param <TYPE>
 *     the type that the builder will build
 */
public interface SdkBuilder<TYPE, BUILDER extends SdkBuilder<TYPE, BUILDER>> extends Buildable {

	/**
	 * A convenience operator that takes something that will mutate the builder in some way and allows inclusion of it in chaining operations.
	 * For example instead of:
	 *
	 * <pre><code>
	 *	Builder builder = ClassBeingBuilt.builder();
	 *	builder = Util.addSomeDetailToTheBuilder(builder);
	 *	ClassBeingBuilt clz = builder.build();
	 * </code></pre>
	 * <p>
	 * This can be done in a statement:
	 *
	 * <pre><code>
	 *	ClassBeingBuilt = ClassBeingBuilt.builder().applyMutation(Util::addSomeDetailToTheBuilder).build();
	 * </code></pre>
	 *
	 * @param mutator
	 * 		the function that mutates the builder
	 * @return
	 * 		the mutated builder instance
	 */
	@SuppressWarnings("unchecked")
	default BUILDER applyMutation(final Consumer<BUILDER> mutator) {
		mutator.accept((BUILDER) this);
		return (BUILDER) this;
	}

	/**
	 * An immutable object that is created from the properties that have been set on the builder.
	 *
	 * @return
	 * 		An instance of {@link TYPE}.
	 */
	TYPE build();
}
