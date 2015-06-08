package pokemonGym

/**
 * @author leandro.mora
 */
package object gym{

    trait PokemonState{
      def pokemon: Pokemon;
    }

    case class Training(val pokemon: Pokemon) extends PokemonState{
      def map(f: (Pokemon => Pokemon)) = Training(f(pokemon))
      def flatMap(f: (Pokemon => Pokemon)) = f(pokemon)
      def fold[T](e: (Pokemon => T))(f: (Pokemon => T)): T = f(pokemon)
    }

    case class Sleeping(val pokemon: Pokemon) extends PokemonState{
  
    }

    case class Poisoned(val pokemon: Pokemon) extends PokemonState{
  
    }
  
    case class KO(val pokemon: Pokemon) extends PokemonState{
      
    }
  
    case class Paralized(val pokemon: Pokemon) extends PokemonState{

    }

    def train(pokemon: Pokemon, activities: Activity*): PokemonState = activities.foldLeft(Training(pokemon): PokemonState){(previousState, currentactivity) =>
      val state =
    }
}
