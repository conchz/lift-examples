package com.github.dolphineor

import java.sql.SQLException
import javax.naming.InitialContext

import com.github.dolphineor.controller.RootController
import com.zaxxer.hikari.HikariDataSource
import net.liftmodules.JQueryModule
import net.liftweb.common._
import net.liftweb.http._
import net.liftweb.squerylrecord.SquerylRecord
import net.liftweb.util.Props
import org.squeryl.Session
import org.squeryl.adapters.MySQLInnoDBAdapter


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Bootstrap extends Bootable {

  def boot() {
    // where to search snippet
    LiftRules.addToPackages("com.github.dolphineor")

    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQueryArtifacts

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.dispatch.append(RootController)

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    JQueryModule.InitParam.JQuery = JQueryModule.JQuery191
    JQueryModule.init()

    configureDB()

  }

  def configureDB(): Unit = {
    val ds = new InitialContext().lookup(Props.get("default.jndi.name", "jdbc/lift")) match {
      case dataSource: HikariDataSource => dataSource
      case _ => throw new SQLException("HikariCP pool initialize failed")
    }

    SquerylRecord.initWithSquerylSession(Session.create(ds.getConnection, new MySQLInnoDBAdapter))
  }
}
