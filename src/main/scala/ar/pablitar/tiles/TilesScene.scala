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
import ar.pablitar.tiles.components.ScrollingBackground
import ar.pablitar.tiles.components.ScrollingBackground
import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color
import ar.pablitar.tiles.components.Horizon
import ar.pablitar.vainilla.commons.components.Bar

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
  
  this.addComponent(new ScrollingBackground(mainCamera, Resources.citySprite,20,180))
  this.addComponent(new ScrollingBackground(mainCamera, Resources.deepCitySprite,26,150,444))
  this.addComponent(new ScrollingBackground(mainCamera, Resources.deepestCitySprite,32,135, 150))
  this.addComponent(new Horizon(mainCamera, new Color(200, 200, 244), Color.GRAY, 300, 40));
  
  this.addComponent(new Bar[TilesScene]("Cooldown",Color.RED, 20, 20) {
    def getMaxValue = (Character.firingStateTime * 100).toInt
    def getCurrentValue = (character.cooldownElapsed * 100).toInt
  })
  
//  val sky = new GameComponent()
//  sky.setAppearance(new Rectangle(new Color(200, 200, 244), TilesApp.DISPLAY_WIDTH, TilesApp.DISPLAY_HEIGHT))
//  sky.setZ(-1000)
//  this.addComponent(sky)
//  
//  val earth = new GameComponent()
//  earth.setAppearance(new Rectangle(Color.GRAY, TilesApp.DISPLAY_WIDTH, TilesApp.DISPLAY_HEIGHT))
//  earth.setZ(-999)
//  earth.setY(TilesApp.DISPLAY_HEIGHT * 0.7)
//  this.addComponent(earth)
  
  def addFloor(floor: Floor) = {
    this.addComponent(floor)
    floors += floor
  }
  
  def removeFloor(floor: Floor) = {
    floors-=floor
    this.removeComponent(floor)
  }
}










