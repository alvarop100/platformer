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
import ar.pablitar.tiles.components.BuffItem
import ar.pablitar.tiles.components.GoodType
import ar.pablitar.tiles.components.BadType
import ar.pablitar.tiles.components.PokemonType
import ar.pablitar.tiles.components.DeathType
import java.util.ArrayList

class TilesScene extends GameScene {
  implicit val mainCamera = new Camera
  val soundPlay = Resources.loop.play()
  val floors = ArrayBuffer(
    new Floor(15, 3)(Vector2D(-300, 500)),
    new Floor(15, 1)(Vector2D(800, 500)),
    new Floor(15, 1)(Vector2D(1000, 250)),
    new Floor(15, 1)(Vector2D(1700, 50)),
    new Floor(15, 1)(Vector2D(1500, 150)))
  floors.foreach(this.addComponent(_))
   val buffs = ArrayBuffer(
      new BuffItem(new GoodType) (Vector2D(250, 480)),
      new BuffItem(new BadType) (Vector2D(1150, 480)),
      new BuffItem(new GoodType) (Vector2D(1550, 480)),
      new BuffItem(new GoodType) (Vector2D(1050, 230)),
      new BuffItem(new BadType) (Vector2D(1950, 30)),
      new BuffItem(new DeathType) (Vector2D(2450, 30)),
      new BuffItem(new GoodType) (Vector2D(1750, 130)),
      new BuffItem(new PokemonType) (Vector2D(1400, 230)))
 buffs.foreach(this.addComponent(_))
  
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
  
  this.addComponent(new Bar[TilesScene]("Life",Color.RED, 30, 10) {
    def getMaxValue = Character.maxHP
    def getCurrentValue = character.life
  })
  this.addComponent(new Bar[TilesScene]("Cooldown",Color.BLUE, 30, 30) {
    def getMaxValue = (Character.firingStateTime * 100).toInt
    def getCurrentValue = (character.cooldownElapsed * 100).toInt
  })
  
  
  def removeBadBuffs(){
    var bads = buffs.filter { _.isBad }
    bads.foreach{_.die}
    
  }
  def addBuffs(){
    
      buffs.+=(new BuffItem(new GoodType) (Vector2D(250, 480)))
      buffs.+=(new BuffItem(new BadType) (Vector2D(1150, 480)))
      buffs.+=(new BuffItem(new GoodType) (Vector2D(1550, 480)))
      buffs.+=(new BuffItem(new GoodType) (Vector2D(1050, 230)))
      buffs.+=(new BuffItem(new BadType) (Vector2D(1950, 30)))
      buffs.+=(new BuffItem(new DeathType) (Vector2D(2450, 30)))
      buffs.+=(new BuffItem(new GoodType) (Vector2D(1750, 130)))
      buffs.+=(new BuffItem(new PokemonType) (Vector2D(1400, 230)))
     buffs.foreach(this.addComponent(_))
  }
  def addFloor(floor: Floor) = {
    this.addComponent(floor)
    floors += floor
  }
  
  def removeFloor(floor: Floor) = {
    floors-=floor
    this.removeComponent(floor)
  }
}










