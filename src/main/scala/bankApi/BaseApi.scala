package main.scala.bankApi

/**
  * Created by AFarashutdinov on 13.08.2016.
  */

class BaseApi {

  def getRestContent(url: String): Option[String] = {
    try {
      val response = scala.io.Source.fromURL(url).mkString
      return Option(response)
    } catch {
      case _ => None
    }
  }

}
