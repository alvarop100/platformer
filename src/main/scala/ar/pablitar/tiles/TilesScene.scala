package ar.pablitar.tiles

import com.uqbar.vainilla.GameScene
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.tiles.components.Floor
import ar.pablitar.vainilla.commons.components.CameraKeyboardMover
import ar.pablitar.tiles.components.Character

class TilesScene extends GameScene {
  implicit val mainCamera = new Camera
  val floor = new Floor(10,3)(Vector2D(-300, 300))
  this.addComponent(floor)
  this.addComponent(new Character)
}