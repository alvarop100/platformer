package ar.pablitar.tiles

import com.uqbar.vainilla.GameScene
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.tiles.components.Floor
import ar.pablitar.vainilla.commons.components.CameraKeyboardMover
import ar.pablitar.tiles.components.Character
import ar.pablitar.tiles.components.CameraFollow

class TilesScene extends GameScene {
  implicit val mainCamera = new Camera
  val floor = new Floor(15, 3)(Vector2D(-300, 300))
  val floors = Seq(
    new Floor(15, 3)(Vector2D(-300, 500)),
    new Floor(15, 1)(Vector2D(700, 500)),
    new Floor(15, 1)(Vector2D(1000, 250)),
    new Floor(15, 1)(Vector2D(1700, 50)),
    new Floor(15, 1)(Vector2D(1500, 150)))
  floors.foreach(this.addComponent(_))
  val character = new Character
  this.addComponent(character)
  this.addComponent(new CameraFollow(mainCamera, () => character.center(),
    Vector2D(-TilesApp.DISPLAY_WIDTH / 2, -TilesApp.DISPLAY_HEIGHT / 2)))
}