package org.app.crud;

import org.springframework.jdbc.core.JdbcTemplate;

public class connect {

	private static JdbcTemplate template;
	connect(){}
		connect(JdbcTemplate template)
		{
			super();
			connect.template=template;
		}
		public static JdbcTemplate getTemplate()
		{
			return template;
		}

}
