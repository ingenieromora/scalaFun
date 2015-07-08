package pokemonGym

/**
 * @author seb
 */
// TODO detalle: en las case clases los parametros por default son "val"
case class Especie(
    resistenciaEvolutiva: Int,
    pesoMaximo: Int,
    tipoPrincipal: Tipo,
    // TODO CUIDADO!: usar OPTION para las cosas que pueden estar a veces, NUNCA usar null
    tipoSecundario: Tipo,
    aumentoVelocidad: Int,
    aumentoPeso: Int,
    aumentoFuerza: Int,
    aumentoEnergiaMaxima: Int,
    condicionEvolutiva: CondicionEvolutiva,
    // TODO lo mismo que antes (es muy importante que NO usen null)
    evolucion: Especie) {
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
    Especie(350, 50, Fuego, null, 50, 50, 50, 50, SubirDeNivel(1000000000), Charizard())
  }
  def Charizard() = {
    Especie(400, 100, Fuego, Dragon, 60, 60, 60 , 60, NoEvoluciona(), null)
  }
  def Ratata() = {
    Especie(1, 1, Normal, null, 1, 1, 1, 1,Intercambiar(), Raticate())
  }
  def Raticate() = {
    Especie(1, 1, Normal, null, 1, 1, 1, 1,NoEvoluciona(), null)
  }
  def Monkey() = {
	  Especie(25, 6, Pelea, null, 3, 4, 5, 6, NoEvoluciona(), null)
  }
  def Gengar() = {
	  Especie(390, 1, Fantasma, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
  def Pikachu() = {
    Especie(390, 1, Electrico, Normal, 45, 45, 50, 60, UsarPiedra(), Raichu())
  }
  def Raichu() = {
    Especie(390, 1, Electrico, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
  def Jigglypuff() = {
    Especie(390, 1, Normal, null, 45, 45, 50, 60, UsarPiedraLunar(), Wigglytuff() )
  }
  def Wigglytuff() = {
    Especie(390, 1, Normal, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
  def Blastoise() = {
    Especie(390, 1, Agua, null, 45, 45, 50, 60, NoEvoluciona(), null)
  }
}