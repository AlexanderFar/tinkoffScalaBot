package main.scala

import java.io.File

import com.typesafe.config.ConfigFactory

/**
  * Created by AFarashutdinov on 09.08.2016.
  */
trait BotSettings {

  private val config = ConfigFactory.parseFile(new File("config/secret.conf"))

  protected def getBotUsername: String = config.getString("bot-settings.bot-username")

  protected def getBotToken: String = config.getString("bot-settings.bot-token")
}
