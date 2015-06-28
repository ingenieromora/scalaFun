package pokemonGym

/**
 * @author seb
 */

abstract class Tipo() {
  var debilesAnteFuego = List("Planta", "Hielo", "Bicho")
  var debilesAnteAgua = List("Fuego", "Tierra", "Roca")
  var debilesAntePlanta = List("Agua", "Tierra", "Roca")
  var debilesAnteTierra = List("Fuego", "Electrico", "Veneno", "Roca")
  var debilesAnteHielo = List("Planta", "Tierra", "Volador", "Dragon")
  var debilesAnteRoca = List("Fuego", "Hielo", "Volador", "Bicho")
  var debilesAnteElectrico = List("Agua", "Volador")
  var debilesAntePsiquico = List("Pelea", "Veneno")
  var debilesAntePelea = List("Normal", "Hielo", "Roca")
  var debilesAnteFantasma = List("Psiquico", "Fantasma")
  var debilesAnteVolador = List("Planta", "Pelea", "Bicho")
  var debilesAnteBicho = List("Planta", "Psiquico")
  var debilesAnteVeneno = List("Planta")
  var debilesAnteDragon = List("Dragon")
}



case object Fuego extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteFuego.contains(tipoX)
}

case object Agua extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteAgua.contains(tipoX)
}

case object Planta extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAntePlanta.contains(tipoX)
}
case object Tierra extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteTierra.contains(tipoX)
}
case object Hielo extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteHielo.contains(tipoX)
}
case object Roca extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteRoca.contains(tipoX)
}
case object Electrico extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteElectrico.contains(tipoX)
}
case object Psiquico extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAntePsiquico.contains(tipoX)
}
case object Pelea extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAntePelea.contains(tipoX)
}
case object Fantasma extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteFantasma.contains(tipoX)
}
case object Volador extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteVolador.contains(tipoX)
}
case object Bicho extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteBicho.contains(tipoX)
}
case object Veneno extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteVeneno.contains(tipoX)
}
case object Dragon extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = debilesAnteDragon.contains(tipoX)
}
case object Normal extends Tipo{
  def gana_tipo(tipoX: String) : Boolean = False
}
