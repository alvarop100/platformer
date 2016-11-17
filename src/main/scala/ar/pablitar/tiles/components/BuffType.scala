package ar.pablitar.tiles.components

import com.uqbar.vainilla.appearances.Sprite

abstract class BuffType {
  
   def getAppearance(): Sprite
   def takeEffectOn(char : Character, buff : BuffItem)
   def isBad() :Boolean
}