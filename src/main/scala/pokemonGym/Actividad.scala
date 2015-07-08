package pokemonGym

/**
 * @author seb
 */
// TODO es preferible un mixin (trait de scala) a un abstract class
// TODO las actividades podrían ser funciones (Estado => Estado)
abstract class Actividad

case class RealizarAtaque(ataque: Ataque) extends Actividad
case class LevantarPesas(kilos: Int) extends Actividad
case class UsarPiedraParaEvolucionar(tipo: Tipo) extends Actividad
case class Nadar(tiempo: Int) extends Actividad

// TODO no usar case clases sin parámetros (son objects)
case class UsarPocion() extends Actividad
case class ComerCalcio() extends Actividad
case class ComerZinc() extends Actividad
case class UsarAntidoto() extends Actividad
case class UsarEther() extends Actividad
case class Descansar() extends Actividad
case class FingirIntercambio() extends Actividad

// TODO Falta aprender ataque
// TODO falta comer hierro