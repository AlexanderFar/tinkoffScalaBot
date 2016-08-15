package main.scala.core
/**
  * Created by AFarashutdinov on 13.08.2016.
  */

import java.util.Calendar

import org.glassfish.grizzly.http.util.TimeStamp

import scala.collection.mutable.Map
import scala.concurrent.duration.Duration

//too simple cache implementation, replaced by something respectable
object CacheProvider {

  val map = Map[String, CacheItem]()

  class CacheItem(val value: Any, val expiration: Option[Long])

  def get[T](key: String, f: => Option[T], expiration: Option[Duration]): Option[T] = {
    val value = map.get(key)
    val now = System.currentTimeMillis()

    if (value.flatMap(v => v.expiration.map(e => e)).getOrElse(now) > now)
      getValue[T](value.get)
    else {
      val newValue = new CacheItem(f.getOrElse(null), getExpirationDate(expiration))
      map.update(key, newValue)
      getValue[T](newValue)
    }
  }

  private def getValue[T](item: CacheItem): Option[T] =
    if (item.value != null && item.value.isInstanceOf[T])
      Option[T](item.value.asInstanceOf[T])
    else
      None

  private def getExpirationDate(expiration: Option[Duration]) = expiration.map(x => System.currentTimeMillis() + x.toMillis)

}