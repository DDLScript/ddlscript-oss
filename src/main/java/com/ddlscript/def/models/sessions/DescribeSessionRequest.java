package com.ddlscript.def.models.sessions;

import com.ddlscript.def.models.DescribableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class DescribeSessionRequest implements DescribableRequest<SessionModel> {

	public static DescribeSessionRequestBuilder builder() {
		return new DescribeSessionRequestBuilder();
	}

	@Getter
	@Setter(AccessLevel.PRIVATE)
	@NonNull
	private String token;

	private DescribeSessionRequest() { }

	private DescribeSessionRequest(@NonNull DescribeSessionRequest other) {
		this.setToken(other.getToken());
	}

	public static class DescribeSessionRequestBuilder implements DescribableRequestBuilder<SessionModel, DescribeSessionRequest, DescribeSessionRequestBuilder> {

		private final DescribeSessionRequest request = new DescribeSessionRequest();

		public DescribeSessionRequestBuilder setToken(@NonNull final String withToken) {
			this.request.setToken(withToken);
			return this;
		}

		@Override
		public DescribeSessionRequest build() {
			return new DescribeSessionRequest(this.request);
		}
	}
}
