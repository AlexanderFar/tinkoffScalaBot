package main.scala

import main.scala.commands._

/**
  * Created by AFarashutdinov on 13.08.2016.
  */
object CommandFactory {
  def getCommand(text: String): Command = {
    val firstWorld = text.split(' ').headOption getOrElse ""
    firstWorld match {
      case c if c.equalsIgnoreCase("ex") || c.equalsIgnoreCase("/ex") => new CurrencyExchangeRates
      case _ => new CommandNotFound
    }
  }
}
