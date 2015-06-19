package pokemonGym

/**
 * @author seb
 */
abstract class Tipo()

//todo: Se puede definir un metodo para esta clase?
//todo: Como hago el match con los case object?
def gana_tipo(tipoX: String) : Boolean = this match {
case Fuego => debilesAnteFuego.contains(tipoX)
case Agua => debilesAnteAgua.contains(tipoX)
case Planta => debilesAntePlanta.contains(tipoX)
case Tierra => debilesAnteTierra.contains(tipoX)
case Hielo => debilesAnteHielo.contains(tipoX)
case Roca => debilesAnteRoca.contains(tipoX)
case Electrico => debilesAnteElectrico.contains(tipoX)
case Psiquico => debilesAntePsiquico.contains(tipoX)
case Pelea => debilesAntePelea.contains(tipoX)
case Fantasma => debilesAnteFantasma.contains(tipoX)
case Volador => debilesAnteVolador.contains(tipoX)
case Bicho => debilesAnteBicho.contains(tipoX)
case Veneno => debilesAnteVeneno.contains(tipoX)
case Dragon => debilesAnteDragon.contains(tipoX)
case Normal => false
}

case object Fuego extends Tipo
case object Agua extends Tipo
case object Planta extends Tipo
case object Tierra extends Tipo
case object Hielo extends Tipo
case object Roca extends Tipo
case object Electrico extends Tipo
case object Psiquico extends Tipo
case object Pelea extends Tipo
case object Fantasma extends Tipo
case object Volador extends Tipo
case object Bicho extends Tipo
case object Veneno extends Tipo
case object Dragon extends Tipo
case object Normal extends Tipo

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