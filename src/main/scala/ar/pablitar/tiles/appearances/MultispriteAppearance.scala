package ar.pablitar.tiles.appearances

import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import java.awt.Graphics2D
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.components.Character

abstract class MultispriteAppearance(c: Character) extends WorldSpaceAppearance {
  def sprites: Seq[Sprite]

  lazy val height: Double = {
    sprites.map(_.getHeight).max
  }

  def width: Double = {
    sprites.map(_.getWidth).max
  }

  def doRenderAt(x: Double, y: Double, graphics: Graphics2D): Unit = {
    spriteFor(c).renderAt(x.toInt, y.toInt, graphics)
  }

  def spriteFor(c: Character): Sprite
}