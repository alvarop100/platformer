package ar.pablitar.tiles.appearances

import java.awt.Graphics2D
import com.uqbar.vainilla.GameComponent
import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import ar.pablitar.vainilla.appearances.Camera
import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite

class JumpingAppearance(c: Character)(implicit val camera: Camera) extends MultispriteAppearance(c) {
  override val sprites = Vector(
    Resources.jumping1,
    Resources.jumping2,
    Resources.jumping3,
    Resources.jumping4)
  def doCopy: Appearance = {
    new JumpingAppearance(c)
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