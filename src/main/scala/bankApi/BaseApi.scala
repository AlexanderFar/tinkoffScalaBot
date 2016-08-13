package main.scala.bankApi

import org.json4s.JValue

/**
  * Created by AFarashutdinov on 13.08.2016.
  */

class BaseApi {

  protected def getResponse(url: String): Option[String] = {
    try {
      val response = scala.io.Source.fromURL(url).mkString
      return Option(response)
    } catch {
      case _ => None
    }
  }

  protected def tryParseJson[T](json: JValue, path: String)(implicit formats : org.json4s.Formats, mf : scala.reflect.Manifest[T]): Option[T] =
    try {
      (json \\ path).extractOpt[T]
    } catch {
      case e: Exception => {
        log(e)
        None
      }
      case _@e => {
        log(e.toString)
        None
      }
    }

  protected def log(exception: Exception): Unit = {
    println("Exception: " + exception)
  }

  protected def log(msg: String): Unit = {
    println(msg)
  }
}
