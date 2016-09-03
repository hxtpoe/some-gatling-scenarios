package user

object PersonsEndPoints {
  val basePath: String = "/persons"

  def createSingle = basePath

  def createBulkPath = s"$basePath/bulk"

  def listPaginatedPath(page: Int) = s"$basePath/$page"

  def findByNamePath(name: String) = s"$basePath/name/$name"

  def count = s"$basePath/count"

  def clean = s"$basePath/clean"

  def reset = s"$basePath/reset"
}