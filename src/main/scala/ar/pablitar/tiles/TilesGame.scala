package ar.pablitar.tiles

import com.uqbar.vainilla.Game
import java.awt.Dimension

object TilesGame extends Game {
  def getDisplaySize(): Dimension = {
    new Dimension(800, 600)
  }

  def getTitle(): String = {
    "Tiles"
  }

  def initializeResources(): Unit = {
  }

  def setUpScenes(): Unit = {
    this.setCurrentScene(new TilesScene())
  }
}