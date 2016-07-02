package ar.pablitar.tiles.appearances

import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.appearances.WorldSpaceAppearance
import java.awt.Graphics2D
import com.uqbar.vainilla.appearances.Sprite
import ar.pablitar.tiles.components.Character
import com.uqbar.vainilla.appearances.SimpleAppearance
import ar.pablitar.vainilla.appearances.worldspace.WorldSpaceSimpleAppearance
import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.appearances.worldspace.ReseteableAppearance

trait MultiAppearance[T <: WorldSpaceAppearance, C <: GameComponent[_]] extends WorldSpaceAppearance {
  
  def component: C
  
  def appearances: Seq[T]

  lazy val height: Double = {
    appearances.map(_.getHeight).max
  }

  def width: Double = {
    appearances.map(_.getWidth).max
  }

  def doRenderAt(x: Double, y: Double, graphics: Graphics2D): Unit = {
    appearanceFor(component).doRenderAt(x.toInt, y.toInt, graphics)
  }
  
  override def update(delta:Double) {
    appearanceFor(component).update(delta)
  }

  def appearanceFor(c: C): T
  
  implicit def simpleToWorldSpace(sp: SimpleAppearance[_]) = WorldSpaceSimpleAppearance(sp)
}