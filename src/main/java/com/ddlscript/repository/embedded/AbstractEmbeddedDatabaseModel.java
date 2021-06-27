package com.ddlscript.repository.embedded;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Optional;

@ToString(callSuper = true)
public abstract class AbstractEmbeddedDatabaseModel<ROW_DATA extends RowData> {

	@Getter(AccessLevel.PROTECTED)
	private ROW_DATA rowData;

	protected AbstractEmbeddedDatabaseModel(@NonNull final ROW_DATA withRowData) {
		this.rowData = withRowData;
	}

	protected Optional<ROW_DATA> optRowData() {
		return Optional.of(this.rowData);
	}
}
