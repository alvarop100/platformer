package ar.pablitar.tiles.components

import ar.pablitar.vainilla.commons.components.RichGameComponent
import ar.pablitar.tiles.TilesScene
import ar.pablitar.vainilla.appearances.Camera
import com.uqbar.vainilla.appearances.Sprite
import java.awt.Graphics2D
import ar.pablitar.tiles.TilesApp

class ScrollingBackground(camera: Camera, sp: Sprite, depth: Int, yPosition: Int) extends RichGameComponent[TilesScene] {
  
  def offsetX = -camera.position.x1 / (depth / 5)
  def offsetY = -camera.position.x2 / (depth / 5)
  
  val repeats = (((TilesApp.DISPLAY_WIDTH / sp.getWidth).ceil + 2).toInt).max(4)
  
  def totalWidth = repeats * sp.getWidth
  
  def normalizedOffset = offsetX.toInt % sp.getWidth
  
  this.setZ(-depth)
  
  override def render(g: Graphics2D) = {
    for(i <- 0 to repeats - 1) {
      renderRepeat(i, g)
    }   
  }

  def renderRepeat(i: Int, g: Graphics2D) = {
     val currentRepeatOffset = normalizedOffset + (i - 1) * sp.getWidth
     
     sp.renderAt(currentRepeatOffset.toInt, (yPosition + offsetY).toInt, g)
  }
}