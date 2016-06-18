package ar.pablitar.tiles.components

import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.tiles.TilesScene
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.math.Orientation
import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.appearances.Camera

class Bolt(initialPosition: Vector2D, orientation: Orientation)(implicit val camera:Camera) extends SpeedyComponent[TilesScene] {
  this.position = initialPosition
  
  val speedScalar = 1500
  speed = orientation.versor * speedScalar
  
  this.setAppearance(Resources.boltAnimation(camera))
}