package com.ddlscript.utils;

public class StringParser {

	public static StringParser of(final String withValue) {
		return new StringParser(withValue);
	}

	private final String withValue;

	public StringParser(final String withValue) {
		this.withValue = withValue;
	}

	public StringParser trim() {
		return new StringParser(this.withValue == null ? null : this.withValue.trim());
	}

	public StringParser assertMinimumLength(final int withMinimumLength) {
		if (this.withValue != null && this.withValue.length() < withMinimumLength) {
			throw new IllegalStateException("String must be at least " + withMinimumLength + " characters in length.");
		}
		return this;
	}

	public StringParser assertMaximumLength(final int withMaximumLength) {
		if (this.withValue != null && this.withValue.length() > withMaximumLength) {
			throw new IllegalStateException("String must be less than " + withMaximumLength + " characters in length.");
		}
		return this;
	}

	public String orElseThrow() {
		if (this.withValue == null) {
			throw new NullPointerException();
		}
		return this.withValue;
	}

	public String toString() {
		return this.withValue;
	}

}
