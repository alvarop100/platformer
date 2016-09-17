package ar.pablitar.tiles.components

import ar.pablitar.vainilla.commons.components.RichGameComponent
import ar.pablitar.tiles.TilesScene
import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.appearances.Circle
import java.awt.Color
import com.uqbar.vainilla.events.constants.MouseButton
import ar.pablitar.vainilla.appearances.Camera
import ar.pablitar.vainilla.commons.math.Vector2D
import java.awt.Graphics2D
import ar.pablitar.tiles.Resources
import com.uqbar.vainilla.events.constants.Key
import scala.collection.mutable.Stack

class FloorCreator(implicit camera: Camera) extends RichGameComponent[TilesScene] {

  var startingPosition: Option[Vector2D] = None

  var editing = false

  this.setAppearance(new Circle(Color.YELLOW, 30).center())
  
  this.setZ(1000)

  val addedFloors = Stack.empty[Floor]

  override def update(state: DeltaState) = {
    if (editing) {
      this.position =
        (state.getCurrentMousePosition.getX, state.getCurrentMousePosition.getY)

      if (state.isMouseButtonPressed(MouseButton.LEFT)) {
        startingPosition match {
          case None =>
            this.startingPosition =
              Some(round(worldPosition))
          case Some(aPosition) =>
            this.addFloor(aPosition)
            this.startingPosition = None
        }
      }

      if (state.isKeyPressed(Key.U)) {
        undo()
      }
    }

    if (state.isKeyPressed(Key.E)) editing = !editing
  }

  def worldPosition = camera.toWorldFromLocal(this.position)

  lazy val startingTile = Resources.tileSet.getTile(2, 2)

  override def render(g: Graphics2D) = {
    if (editing) {
      startingPosition.fold(startingTile.render(round(this.worldPosition), g)) {
        x => startingTile.render(x, g)
      }
      super.render(g)
    }
  }

  val tileSize = 63

  def round(position: Vector2D): Vector2D = {
    (((position.x1 / tileSize).floor) * tileSize, ((position.x2 / tileSize).floor) * tileSize)
  }

  def addFloor(startingPosition: Vector2D) = {
    val sizeVector =
      ((worldPosition - startingPosition).abs / tileSize) + (1,1)

    val floor = new Floor(
        sizeVector.x1.toInt.max(1), sizeVector.x2.toInt.max(1)
     )(
         (worldPosition.x1.min(startingPosition.x1), worldPosition.x2.min(startingPosition.x2))
     )
    addedFloors.push(floor)
    this.getScene.addFloor(floor)
  }

  def undo() = {
    if(!addedFloors.isEmpty)
      this.getScene.removeFloor(addedFloors.pop())
  }

}




















