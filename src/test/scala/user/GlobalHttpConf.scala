package user

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

trait GlobalHttpConf {
  def httpConfig(): HttpProtocolBuilder = {
    http
      .baseURL("http://54.78.95.58:9000")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
      .doNotTrackHeader("1")
      .acceptEncodingHeader("gzip, deflate")
  }
}