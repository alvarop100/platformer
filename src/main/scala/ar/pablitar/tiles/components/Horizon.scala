package ar.pablitar.tiles.components

import com.uqbar.vainilla.GameComponent
import ar.pablitar.tiles.TilesScene
import java.awt.Color
import java.awt.Graphics2D
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.tiles.TilesApp

class Horizon(camera: Camera, topColor: Color, bottomColor: Color, initialPosition: Double, depth: Int) extends GameComponent[TilesScene] {
  
  this.setZ(-depth)
  
  def currentOffset = (initialPosition - (camera.position.x2 / (depth / 5))).floor.toInt
  
  override def render(g: Graphics2D):Unit = {
    g.setColor(topColor)
    g.fillRect(0, 0, TilesApp.DISPLAY_WIDTH, currentOffset)
    g.setColor(bottomColor)
    g.fillRect(0, currentOffset, TilesApp.DISPLAY_WIDTH, TilesApp.DISPLAY_HEIGHT - currentOffset)
  }
}