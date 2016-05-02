package ar.pablitar.tiles.appearances

import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.tiles.components.Character
import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.Resources

class FallingAppearance(c: Character)(implicit val camera: Camera) extends MultispriteAppearance(c) {
  
  override val sprites = Vector(
    Resources.jumping1,
    Resources.jumping2,
    Resources.jumping3,
    Resources.jumping4)
  
  
  def doCopy: Appearance = {
    new FallingAppearance(c)
  }

  def spriteFor(c: Character): Sprite = {
    c.speed.x2 match {
      case sp if sp < -720 => sprites(0)
      case sp if sp < -660 => sprites(1)
      case sp if sp < -580 => sprites(2)
      case _               => sprites(3)
    }
  }
}