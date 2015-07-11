package pokemonGym

/**
 * @author seb
 */
// TODO detalle: en las case clases los parametros por default son "val"
case class Especie(
    resistenciaEvolutiva: Int,
    pesoMaximo: Int,
    tipoPrincipal: Tipo,
    tipoSecundario: Option[Tipo],
    aumentoVelocidad: Int,
    aumentoPeso: Int,
    aumentoFuerza: Int,
    aumentoEnergiaMaxima: Int,
    condicionEvolutiva: CondicionEvolutiva,
    evolucion: Option[Especie]) {
}

object Especie {
   /* 
    * TODO detalle: estos no deberían ser métodos 
    * (no necesitan parámetros y retornan siempre el mismo valor).
    * Definirlos como val (si tienen problemas con que quedan en "null" definanlos como lazy val
    * (los vals se evalúan en el orden de definición y si bien van a tipar, si están usados antes de estar
    * definidos van a tener null como valor).
    * Entonces:
    * - o definen primero los val que no dependen de otros y luego los que dependen de esos
    * - o si hay referencias circulares los definen como lazy val
    * - o los definen como object
    */
  def Charmander() = {
    Especie(350, 50, Fuego, None, 50, 50, 50, 50, SubirDeNivel(1000000000), Some(Charizard()))
  }
  def Charizard() = {
    Especie(400, 100, Fuego, Some(Dragon), 60, 60, 60 , 60, NoEvoluciona, None)
  }
  def Ratata() = {
    Especie(1, 1, Normal, None, 1, 1, 1, 1,Intercambiar, Some(Raticate()))
  }
  def Raticate() = {
    Especie(1, 1, Normal, None, 1, 1, 1, 1,NoEvoluciona, None)
  }
  def Monkey() = {
	  Especie(25, 6, Pelea, None, 3, 4, 5, 6, NoEvoluciona, None)
  }
  def Gengar() = {
	  Especie(390, 1, Fantasma, None, 45, 45, 50, 60, NoEvoluciona, None)
  }
  def Pikachu() = {
    Especie(390, 1, Electrico, Some(Normal), 45, 45, 50, 60, UsarPiedra, Some(Raichu()))
  }
  def Raichu() = {
    Especie(390, 1, Electrico, None, 45, 45, 50, 60, NoEvoluciona, None)
  }
  def Jigglypuff() = {
    Especie(390, 1, Normal, None, 45, 45, 50, 60, UsarPiedraLunar, Some(Wigglytuff()))
  }
  def Wigglytuff() = {
    Especie(390, 1, Normal, None, 45, 45, 50, 60, NoEvoluciona, None)
  }
  def Blastoise() = {
    Especie(390, 1, Agua, None, 45, 45, 50, 60, NoEvoluciona, None)
  }
}