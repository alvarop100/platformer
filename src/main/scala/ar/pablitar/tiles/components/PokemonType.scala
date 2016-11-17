package ar.pablitar.tiles.components

import ar.pablitar.tiles.Resources

class PokemonType extends BuffType{
   def getAppearance() ={
    Resources.poke
  }
  def takeEffectOn(char : Character, buff : BuffItem){
    char.pokeStatus()
  }
  def isBad()={
    false
  }
}