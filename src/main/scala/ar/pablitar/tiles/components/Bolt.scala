package ar.pablitar.tiles.components

import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.tiles.TilesScene
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.math.Orientation
import ar.pablitar.vainilla.commons.math.Orientation._
import ar.pablitar.tiles.Resources
import ar.pablitar.vainilla.appearances.Camera
import com.uqbar.vainilla.DeltaState
import ar.pablitar.tiles.appearances.SimpleOrientedAppearance

class Bolt(initialPosition: Vector2D, orientation: Orientation)(implicit val camera: Camera) extends SpeedyComponent[TilesScene] with Oriented {
  this.position = initialPosition
  
  val speedScalar = 1500
  
  val maxDistance = 2000
  
  speed = orientation.versor * speedScalar
  
  this.setAppearance(new SimpleOrientedAppearance(this, Resources.boltAnimation))
  
  override def update(state: DeltaState) = {
    if(this.position.distanceTo(initialPosition) > maxDistance) {
      this.destroy()
    } else {
      super.update(state)
    }
  }

  def facingDirection: Orientation = {
    orientation
  }
}