package com.github.dolphineor.jdbc

import java.sql.SQLException
import javax.naming.InitialContext

import net.liftweb.util.Props
import slick.jdbc.HikariCPJdbcDataSource

/**
 * Created by dolphineor on 2015-7-6.
 */
trait HDataSource {
  lazy val dataSource = new InitialContext().lookup(Props.get("default.jndi.name", "jdbc/lift")) match {
    case ds: HikariCPJdbcDataSource => ds
    case _ => throw new SQLException("HikariCP pool initialize failed")
  }
}
