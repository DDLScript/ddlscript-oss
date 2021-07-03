package com.ddlscript.repository.embedded;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
public class EmbeddedServerBuilder {

	private static final String TCP_ALLOW_OTHERS = "-tcpAllowOthers";

	@Setter
	private boolean allowOthers = false;

	public static EmbeddedServerBuilder builder() {
		return new EmbeddedServerBuilder();
	}

	public EmbeddedServerBuilder allowOthers() {
		this.setAllowOthers(true);
		return this;
	}

	public Server build() throws SQLException {
		List<String> args = new ArrayList<>();

		if (allowOthers) {
			args.add(TCP_ALLOW_OTHERS);
		}

		String[] argArray = args.toArray(String[]::new);
		return Server.createTcpServer(argArray);
	}
}
