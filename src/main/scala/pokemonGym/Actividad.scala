package pokemonGym

/**
 * @author seb
 */
// TODO las actividades podrían ser funciones (Estado => Estado)
trait Actividad

case class RealizarAtaque(ataque: Ataque) extends Actividad
case class LevantarPesas(kilos: Int) extends Actividad
case class UsarPiedraParaEvolucionar(tipo: Tipo) extends Actividad
case class Nadar(tiempo: Int) extends Actividad
case class AprenderAtaque(ataque : Ataque) extends Actividad

object UsarPocion extends Actividad
object ComerCalcio extends Actividad
object ComerZinc extends Actividad
object UsarAntidoto extends Actividad
object UsarEther extends Actividad
object Descansar extends Actividad
object FingirIntercambio extends Actividad
object ComerHierro extends Actividad
