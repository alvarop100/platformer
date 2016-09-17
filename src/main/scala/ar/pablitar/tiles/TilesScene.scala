package ar.pablitar.tiles

import com.uqbar.vainilla.GameScene
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.tiles.components.Floor
import ar.pablitar.vainilla.commons.components.CameraKeyboardMover
import ar.pablitar.tiles.components.Character
import ar.pablitar.tiles.components.CameraFollow
import ar.pablitar.tiles.components.FloorCreator
import scala.collection.mutable.ArrayBuffer

class TilesScene extends GameScene {
  implicit val mainCamera = new Camera
  val floors = ArrayBuffer(
    new Floor(15, 3)(Vector2D(-300, 500)),
    new Floor(15, 1)(Vector2D(800, 500)),
    new Floor(15, 1)(Vector2D(1000, 250)),
    new Floor(15, 1)(Vector2D(1700, 50)),
    new Floor(15, 1)(Vector2D(1500, 150)))
  floors.foreach(this.addComponent(_))
  val character = new Character
  this.addComponent(character)
  this.addComponent(new CameraFollow(mainCamera, () => character.center(),
    Vector2D(-TilesApp.DISPLAY_WIDTH / 2, -TilesApp.DISPLAY_HEIGHT / 2)))
    
  val floorsCreator = new FloorCreator
  this.addComponent(floorsCreator)
  
  def addFloor(floor: Floor) = {
    this.addComponent(floor)
    floors += floor
  }
  
  def removeFloor(floor: Floor) = {
    floors-=floor
    this.removeComponent(floor)
  }
}










