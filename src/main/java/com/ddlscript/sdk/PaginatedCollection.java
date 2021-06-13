package com.ddlscript.sdk;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * @param <E>
 * 		the types of elements in the collection
 */
public class PaginatedCollection<E> implements Iterable<E> {
	/**
	 * Returns a new builder capable of producing a new creation request.
	 *
	 * @return New Request builder.
	 */
	public static <E> PaginatedCollection.Builder<E> builder() {
		return new PaginatedCollection.Builder<>();
	}

	/**
	 * Elements in the collection.
	 */
	private final Collection<E> elements = new ArrayList<>();

	@Setter(AccessLevel.PRIVATE)
	@Getter
	private int pageSize;

	@Setter(AccessLevel.PRIVATE)
	@Getter
	private int pageCount;

	/**
	 * Default Constructor.
	 */
	private PaginatedCollection() { }

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 * 		Instance to copy data from.
	 */
	private PaginatedCollection(@NonNull PaginatedCollection<E> other) {
		this.elements.addAll(other.elements);
		this.setPageSize(other.getPageSize());
		this.setPageCount(other.getPageCount());
	}

	public Collection<E> getElements() {
		return Collections.unmodifiableCollection(this.elements);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Iterator<E> iterator() {
		return this.elements.iterator();
	}

	/**
	 * Returns the number of elements in this collection.  If this collection
	 * contains more than {@code Integer.MAX_VALUE} elements, returns
	 * {@code Integer.MAX_VALUE}.
	 *
	 * @return the number of elements in this collection
	 */
	public int size() {
		return this.elements.size();
	}

	public static class Builder<E> implements SdkBuilder<PaginatedCollection<E>, PaginatedCollection.Builder<E>> {

		private final PaginatedCollection<E> request = new PaginatedCollection<>();

		public Builder() {
			this.setPageCount(0);
			this.setPageSize(0);
		}

		public Builder<E> setElements(final Collection<E> withCollection) {
			this.request.elements.clear();
			if (withCollection != null) {
				this.request.elements.addAll(withCollection);
			}
			return this;
		}

		public Builder<E> setPageSize(final int withPageSize) {
			this.request.setPageSize(withPageSize);
			return this;
		}

		public Builder<E> setPageCount(final int withPageCount) {
			this.request.setPageCount(withPageCount);
			return this;
		}

		@Override
		public PaginatedCollection<E> build() {
			return new PaginatedCollection<E>(this.request);
		}
	}
}
