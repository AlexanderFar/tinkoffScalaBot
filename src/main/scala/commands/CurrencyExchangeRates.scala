package main.scala.commands

import main.scala.bankApi.CurrencyExchangeApi

/**
  * Created by AFarashutdinov on 13.08.2016.
  */
class CurrencyExchangeRates extends Command {
  //there must be some text about bot + help page corresponding to request + general help page
  def getAnswer() =
    CurrencyExchangeRates.api
      .getRates()
      .map(list =>
        list.foldLeft("")((s, rate) =>
          s"$s\n${rate.currencyType} - RUB: ${rate.buy} \\ ${rate.sell}"
        ))
      .getOrElse("service unavailable")

}

object CurrencyExchangeRates {
  val api = new CurrencyExchangeApi()
}