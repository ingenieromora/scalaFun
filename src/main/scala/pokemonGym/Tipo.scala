package pokemonGym

/**
 * @author seb
 */
// TODO es preferible un mixin (trait de scala) a un abstract class
trait Tipo {
  def leGanaA(otroTipo: Tipo): Boolean
  def leGanaA(otroTipo: Option[Tipo]): Boolean = {
    otroTipo.exists(leGanaA(_))
  }
}

case object Agua extends Tipo {
  def leGanaA(otroTipo: Tipo) = otroTipo match {
    case Fuego | Tierra | Roca => true
    case _ => false
  }
}
case object Planta extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match{
    case Agua | Tierra | Roca => true
    case _ => false
  }
}
case object Fuego extends Tipo {
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match{
    case Planta | Hielo | Bicho => true
    case _ => false
  }
}
case object Tierra extends Tipo {
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Fuego | Electrico | Veneno | Roca => true
    case _ => false
  }
}
case object Hielo extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Planta | Tierra | Volador | Dragon => true
    case _ => false
  }
}
case object Roca extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Fuego | Hielo | Volador | Bicho => true
    case _ => false
  }
}
case object Electrico extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Agua| Volador => true
    case _ => false
  }
}
case object Psiquico extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Pelea | Veneno => true
    case _ => false
  }
}
case object Pelea extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Normal | Hielo | Roca => true
    case _ => false
  }
}
case object Fantasma extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Psiquico | Fantasma => true
    case _ => false
  }
}
case object Volador extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Normal | Hielo | Roca => true
    case _ => false
  }
}
case object Bicho extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Planta | Psiquico => true
    case _ => false
  }
}
case object Veneno extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Planta => true
    case _ => false
  }
}
case object Dragon extends Tipo{
  override def leGanaA(otroTipo: Tipo): Boolean = otroTipo match {
    case Dragon => true
    case _ => false
  }
}
case object Normal extends Tipo{
  override def leGanaA(otroTipo: Tipo) = false
}
// TODO la piedra lunar es un caso particular, no es un tipo de pokemon (no existe como Tipo)
case object Lunar extends Tipo{
  override def leGanaA(otroTipo: Tipo) = false
}



