package pokemonGym

/**
 * Created by leandromoras on 6/8/15.
 */
case class Pokemon(val genero: Char, val especie: Especie, val estado: Estado, val energia: Int,
                   val ataques: List[Ataque], val nivel: Int = 1) {

  var experiencia: Int = 0

  def ganarExperiencia(exp: Int) = {
    experiencia += exp
    if (exp >= 2 * (nivel - 1) * especie.resistenciaEvolutiva + 
        especie.resistenciaEvolutiva) {
      subirDeNivel()
    } else {
      this
    }
  }
  
  def conoceAtaque(ataque: Ataque): Boolean = {
    ataques.contains(ataque)
  }
  
  def usarAtaque[T <: Ataque](ataque: T) {
    ataques.find { atq => atq == ataque }.map { atq => atq.usarAtaque }
  }
  
  def subirDeNivel() = copy(nivel = nivel + 1, especie = especie.copy(
      fuerza = especie.fuerza + especie.aumentoFuerza,
      velocidad = especie.velocidad + especie.aumentoVelocidad,
      peso = especie.peso + especie.aumentoPeso,
      energiaMaxima = especie.energiaMaxima + especie.aumentoEnergiaMaxima))

  def aumentarVelocidad(arg: Int) = copy(especie = especie.copy(velocidad = especie.velocidad + 1))
  
}
