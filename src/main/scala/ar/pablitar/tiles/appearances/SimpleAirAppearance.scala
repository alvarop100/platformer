package ar.pablitar.tiles.appearances

import com.uqbar.vainilla.appearances.Appearance
import com.uqbar.vainilla.appearances.Sprite

import ar.pablitar.tiles.components.Character
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance

class SimpleAirAppearance(val component: Character, sprites:Seq[Sprite])(implicit val camera: Camera)
  extends MultiAppearance[WorldSpaceAppearance, Character] {
  
  override val appearances = sprites.map(simpleToWorldSpace)

  def doCopy: Appearance = {
    new SimpleAirAppearance(component, sprites)
  }

  def appearanceFor(c: Character): WorldSpaceAppearance = {
    c.speed.x2 match {
      case sp if sp < -720 => appearances(0)
      case sp if sp < -660 => appearances(1)
      case sp if sp < -580 => appearances(2)
      case _ => appearances(3)
    }
  }
}