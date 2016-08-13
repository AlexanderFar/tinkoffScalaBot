package main.scala.commands

import main.scala.bankApi.CurrencyExchangeApi

/**
  * Created by AFarashutdinov on 13.08.2016.
  */
class CurrencyExchangeRates extends Command {
  //there must be some text about bot + help page corresponding to request + general help page
  def getAnswer() = {
    val rates = CurrencyExchangeRates.api.getRates()
    rates.foldLeft("")((s, tpl) => s"$s\n${tpl._1} - RUB: ${tpl._2}")
  }
}

object CurrencyExchangeRates {
  val api = new CurrencyExchangeApi()
}