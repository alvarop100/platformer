package ar.pablitar.tiles.components

import ar.pablitar.tiles.Resources

class GoodType extends BuffType{
  
  def getAppearance() ={
    Resources.goodFruit
  }
  def takeEffectOn(char : Character, buff : BuffItem){
    char.incrementLife(5,buff)
  }
  def isBad()={
    false
  }
}