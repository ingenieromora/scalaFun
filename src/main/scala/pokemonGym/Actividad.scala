package pokemonGym

/**
 * @author seb
 */
abstract class Actividad

case class RealizarAtaque(ataque: Ataque) extends Actividad
case class LevantarPesas(kilos: Int) extends Actividad
case class UsarPocion() extends Actividad
case class ComerCalcio() extends Actividad
case class ComerZinc() extends Actividad
case class UsarAntidoto() extends Actividad