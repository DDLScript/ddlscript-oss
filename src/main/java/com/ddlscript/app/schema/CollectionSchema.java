package com.ddlscript.app.schema;

import com.ddlscript.sdk.PaginatedCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Iterator;

public class CollectionSchema<SCHEMA> {

	@JsonIgnore
	private final PaginatedCollection<SCHEMA> items;

	public CollectionSchema(final PaginatedCollection<SCHEMA> withItems) {
		this.items = withItems;
	}

	@JsonProperty("items")
	public Iterator<SCHEMA> getItems() {
		return this.items.iterator();
	}

	@JsonProperty("item_count")
	public int getCount() {
		return this.items.size();
	}

	@JsonProperty("page_count")
	public int getPageCount() {
		return this.items.getPageCount();
	}

	@JsonProperty("page_size")
	public int getPageSize() {
		return this.items.getPageSize();
	}
}
