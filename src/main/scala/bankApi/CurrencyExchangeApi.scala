package main.scala.bankApi

import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization


/**
  * Created by AFarashutdinov on 13.08.2016.
  */
class CurrencyExchangeApi extends BaseApi with ApiSettings {

  def getRates(): Option[List[Rate]] =
    getResponse(currency_rates)
      .flatMap(apiResponse =>
        parseOpt(apiResponse)
          .map(json => getRatesFromJson(json)))
      .flatten


  private def getRatesFromJson(json: JValue): Option[List[Rate]] =
    tryParseJson[List[ApiRate]](json, "rates")
      .map(apiList => filterAndTransform(apiList))

  def filterAndTransform(apiList: List[ApiRate]): List[Rate] = apiList.filter(filterApiRates).map(transform)

  def filterApiRates(i: ApiRate): Boolean = i.buy.isDefined && i.sell.isDefined &&
    i.category.equalsIgnoreCase("DepositPayments") && getCurrency(i.fromCurrency.name).isDefined &&
    getCurrency(i.toCurrency.name).contains(CurrencyExchangeApi.Currency.RUB)


  def transform(i: ApiRate): Rate = new Rate(getCurrency(i.fromCurrency.name).get, i.buy.get, i.sell.get)

  def getCurrency(name: String): Option[CurrencyExchangeApi.Currency.Value] = name
  match {
    case "USD" => Option(CurrencyExchangeApi.Currency.USD)
    case "EUR" => Option(CurrencyExchangeApi.Currency.EUR)
    case "GBP" => Option(CurrencyExchangeApi.Currency.GBP)
    case "RUB" => Option(CurrencyExchangeApi.Currency.RUB)
    case _ => None
  }

  implicit val formats = {
    Serialization.formats(FullTypeHints(List(classOf[ApiRate])))
  }

  class Rate(
              val currencyType: CurrencyExchangeApi.Currency.Value,
              val buy: Double,
              val sell: Double)


  class ApiRate(
                 val category: String,
                 val fromCurrency: ApiCurrencyInfo,
                 val toCurrency: ApiCurrencyInfo,
                 val buy: Option[Double],
                 val sell: Option[Double])

  class ApiCurrencyInfo(val code: Int, val name: String)


}

object CurrencyExchangeApi {

  object Currency extends Enumeration {
    val RUB, USD, EUR, GBP = Value
  }


  /*  object RateCategory extends Enumeration {
    val
    DepositClosingBenefit,
    DepositClosing,
    DepositPayments,
    DebitCardsTransfers,
    DebitCardsOperations,
    CreditCardsOperations,
    PrepaidCardsTransfers,
    PrepaidCardsOperations,
    SavingAccountTransfers,
    C2CTransfers,
    CUTransferFrom10To100,
    CUTransferAbove100
    = Value
  }*/
}
