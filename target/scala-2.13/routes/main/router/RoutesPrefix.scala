// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Tristian/Repositories/github.com/valued-co/dev-triztian/conf/routes
// @DATE:Tue Jul 02 23:00:31 PDT 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
