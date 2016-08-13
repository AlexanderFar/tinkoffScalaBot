package main.scala.bankApi

/**
  * Created by AFarashutdinov on 13.08.2016.
  */
class CurrencyExchangeApi extends ApiSettings{

  val rates = Map[String,Double]("USD" -> 123, "EUR" -> 234, "GBP" ->345)

  def getRates():  Map[String,Double] = rates


}


