package ar.pablitar.tiles.components

import ar.pablitar.tiles.Resources

class DeathType extends BuffType{
  def getAppearance() ={
    Resources.death
  }
  def takeEffectOn(char : Character, buff : BuffItem){
    char.die()
  }
  def isBad()={
    true
  }
}