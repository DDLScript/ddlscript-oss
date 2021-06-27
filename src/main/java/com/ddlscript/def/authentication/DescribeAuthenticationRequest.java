package com.ddlscript.def.authentication;

import com.ddlscript.sdk.models.DescribableRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class DescribeAuthenticationRequest implements DescribableRequest<AuthenticationModel> {

	public static DescribeAuthenticationRequest.DescribeAuthenticationRequestBuilder builder() {
		return new DescribeAuthenticationRequest.DescribeAuthenticationRequestBuilder();
	}

	@Getter
	@Setter(AccessLevel.PRIVATE)
	@NonNull
	private String username;

	private DescribeAuthenticationRequest() { }

	private DescribeAuthenticationRequest(@NonNull DescribeAuthenticationRequest other) {
		this.setUsername(other.getUsername());
	}

	public static class DescribeAuthenticationRequestBuilder implements
			DescribableRequestBuilder<AuthenticationModel, DescribeAuthenticationRequest,
					DescribeAuthenticationRequestBuilder> {

		private final DescribeAuthenticationRequest request = new DescribeAuthenticationRequest();

		public DescribeAuthenticationRequestBuilder setUsername(
				@NonNull final String withUsername
		) {
			this.request.setUsername(withUsername);
			return this;
		}

		@Override
		public DescribeAuthenticationRequest build() {
			return new DescribeAuthenticationRequest(this.request);
		}
	}
}
