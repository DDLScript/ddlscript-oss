package com.ddlscript.def.sessions;

import com.ddlscript.sdk.models.CreatableRequest;
import com.ddlscript.def.users.UserModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class CreateSessionRequest implements CreatableRequest<SessionModel> {

	public static CreateSessionRequestBuilder builder() {
		return new CreateSessionRequestBuilder();
	}

	@Getter
	@Setter(AccessLevel.PRIVATE)
	@NonNull
	private UserModel user;

	private CreateSessionRequest() { }

	private CreateSessionRequest(@NonNull CreateSessionRequest other) {
		this.setUser(other.getUser());
	}

	public static class CreateSessionRequestBuilder
			implements CreatableRequestBuilder<SessionModel, CreateSessionRequest, CreateSessionRequestBuilder> {

		private final CreateSessionRequest request = new CreateSessionRequest();

		public CreateSessionRequestBuilder setUser(@NonNull final UserModel withModel) {
			this.request.setUser(withModel);
			return this;
		}

		@Override
		public CreateSessionRequest build() {
			return new CreateSessionRequest(this.request);
		}
	}
}
