sealed class Route(val path: String) {
    object Login : Route("login")
    object SignUp : Route("signup")
    object Home : Route("home")
    object Profile : Route("profile")
    object Settings : Route("settings")
}
