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

	@JsonProperty("count")
	public int getCount() {
		return this.items.size();
	}

	@JsonProperty("pages")
	public int getPageCount() {
		return this.items.getPageCount();
	}

	@JsonProperty("pageSize")
	public int getPageSize() {
		return this.items.getPageSize();
	}
}
