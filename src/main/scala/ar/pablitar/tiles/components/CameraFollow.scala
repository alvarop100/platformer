package ar.pablitar.tiles.components

import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.DeltaState

class CameraFollow(camera: Camera, positionGetter: (() => Vector2D), offset: Vector2D) extends GameComponent[GameScene] {
  override def update(state: DeltaState) = {
    camera.position = positionGetter() + offset
  }
}