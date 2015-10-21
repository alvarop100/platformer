package ar.pablitar.tiles.components

import ar.pablitar.tiles.Resources
import ar.pablitar.tiles.TilesScene
import ar.pablitar.vainilla.appearances.worldspace.MappedTileAppearance
import ar.pablitar.vainilla.commons.components.RichGameComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.math.Vector2D.toVector2DFromInt
import ar.pablitar.vainilla.commons.math.Semiplane

class Floor(width: Int, height: Int)(aPosition: Vector2D = (0, 0)) extends RichGameComponent[TilesScene] {
  implicit lazy val camera = this.getScene.mainCamera
  this.position = aPosition
  override def onSceneActivated() {
    this.setAppearance(new MappedTileAppearance(width, height,
      Resources.tileSet.tileHSize, Resources.tileSet.tileVSize)({
      case (i, 0)                      => Resources.tileSet.getTile(2 + i % 4, 2)
      case (i, j) if (j == height - 1) => Resources.tileSet.getTile(8 + i % 4, 0)
      case (i, j)                      => Resources.tileSet.getTile(3 + i % 4, 0)
    }))
  }
  
  def pared = Semiplane(aPosition, Vector2D(0, -1))

  def puntoEstaDetras(aPoint: Vector2D) = {
    pared.puntoEstaDetras(aPoint)
  }
}