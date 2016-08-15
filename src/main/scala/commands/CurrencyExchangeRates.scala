package main.scala.commands

import main.scala.bankApi.CurrencyExchangeApi
import main.scala.core.CacheProvider
import main.scala.bankApi
import scala.concurrent.duration._
import scala.concurrent.duration.Duration

/**
  * Created by AFarashutdinov on 13.08.2016.
  */
class CurrencyExchangeRates extends Command {
  //there must be some text about bot + help page corresponding to request + general help page
  def getAnswer() =
    CurrencyExchangeRates.getRates()
      .map(list =>
        list.foldLeft("")((s, rate) =>
          s"$s\n${rate.currencyType} - RUB: ${rate.buy} \\ ${rate.sell}"
        ))
      .getOrElse("service unavailable")

}

object CurrencyExchangeRates {
  val api = new CurrencyExchangeApi()
  val cache = CacheProvider

  def getRates() = cache.get[List[CurrencyExchangeApi.Rate]]("CurrencyExchangeApi.Rate", api.getRates(), Option(1.minute))
}