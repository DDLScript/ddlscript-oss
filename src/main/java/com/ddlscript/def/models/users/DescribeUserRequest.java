package com.ddlscript.def.models.users;

import com.ddlscript.sdk.models.DescribableRequest;
import com.ddlscript.utils.StringParser;
import lombok.Getter;
import lombok.NonNull;

public class DescribeUserRequest implements DescribableRequest<UserModel> {

	public static DescribeUserRequestBuilder builder() {
		return new DescribeUserRequestBuilder();
	}

	@Getter
	private String username;

	private DescribeUserRequest() { }

	private DescribeUserRequest(@NonNull DescribeUserRequest other) {
		this.setUsername(other.getUsername());
	}

	private void setUsername(final String withUsername) {
		this.username = StringParser.of(withUsername)
				.trim()
				.assertMinimumLength(1)
				.assertMaximumLength(UserModel.MAX_USERNAME_LENGTH)
				.orElseThrow();
	}

	public static class DescribeUserRequestBuilder implements DescribableRequestBuilder<UserModel, DescribeUserRequest, DescribeUserRequestBuilder> {

		private final DescribeUserRequest request = new DescribeUserRequest();

		public DescribeUserRequestBuilder setUsername(@NonNull final String withUsername) {
			this.request.setUsername(withUsername);
			return this;
		}

		@Override
		public DescribeUserRequest build() {
			return new DescribeUserRequest(this.request);
		}
	}
}
