package main.scala.bankApi

import java.io.File

import com.typesafe.config.ConfigFactory

/**
  * Created by AFarashutdinov on 13.08.2016.
  */
trait ApiSettings {
  private val config = ConfigFactory.parseFile(new File("config/application.conf")).getConfig("bank-api")

  protected def host: String = config.getString("host")

  protected def currency_rates: String = host + config.getString("methods.currency_rates")

}
