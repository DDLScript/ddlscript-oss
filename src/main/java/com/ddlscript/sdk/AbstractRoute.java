package com.ddlscript.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class AbstractRoute<INPUT, OUTPUT> implements Route {

	private final Class<INPUT> inputClass;

	public AbstractRoute() {
		this.inputClass = null;
	}

	public AbstractRoute(@NonNull Class<INPUT> withInputClass) {
		this.inputClass = withInputClass;
	}

	private final ObjectMapper objectMapper = new ObjectMapper();

	public abstract OUTPUT handle(final INPUT withBody, final Request request, final Response response) throws Exception;

	@Override
	public Object handle(final Request request, final Response response) throws Exception {

		INPUT input = null;
		if (this.inputClass != null) {
			input = objectMapper.readValue(request.body(), this.inputClass);
		}

		OUTPUT output = this.handle(input, request, response);

		if (output == null) {
			return null;
		}

		response.status(HttpStatus.OK_200);
		response.type("application/json");
		return objectMapper.writeValueAsString(output);
	}
}
