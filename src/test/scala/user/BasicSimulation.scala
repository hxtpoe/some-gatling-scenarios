package user

import io.gatling.core.Predef._
import user.actions.{Show, Create}
import user.model.User

class PersonSimulation extends Simulation with GlobalHttpConf {

  implicit val defaultPause = 5

  val users = scenario("Users")
    .exec(Show.count).pause(5)
    .exec(Show.listPaginatedWithDataExtraction(4)).pause(5)
    .exec(Create.single(User("elorap", 13))).pause(5)
    .exec(Show.listPaginated(1)).pause(5)
    .exec(Show.listPaginated(2)).pause(5)
    .exec(Show.listPaginated(3)).pause(5)
    .foreach("${usernames}", "name") {
      exec(Show.findByName("${name}")).pause(5)
    }

  setUp(
    users.inject(atOnceUsers(1)).protocols(httpConfig())
  ).assertions(
    global.responseTime.max.lessThan(2000),
    global.successfulRequests.percent.greaterThan(99)
  )
}