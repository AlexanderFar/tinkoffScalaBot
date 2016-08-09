package main.scala

import java.io.File

import com.typesafe.config.ConfigFactory
import org.telegram.telegrambots.logging.BotLogger
import org.telegram.telegrambots.{TelegramApiException, TelegramBotsApi}

/**
  * Created by AFarashutdinov on 09.08.2016.
  */
object Main extends App {

  val telegramBotsApi = new TelegramBotsApi();

  try {
    telegramBotsApi.registerBot(new TinkoffBotHandler());
  } catch {
    case e: TelegramApiException => BotLogger.error("LOGTAG", e)
    case _@e => throw e
  }

}

