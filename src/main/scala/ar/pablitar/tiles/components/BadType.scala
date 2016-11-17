package ar.pablitar.tiles.components

import ar.pablitar.tiles.Resources

class BadType extends BuffType{
  
  def getAppearance() ={
    Resources.badFruit
  }
  def takeEffectOn(char : Character, buff : BuffItem){
    char.ruduceLife(5,buff)
  }
  def isBad()={
    true
  }
}