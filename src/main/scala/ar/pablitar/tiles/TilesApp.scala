package ar.pablitar.tiles

import com.uqbar.vainilla.DesktopGameLauncher
import ar.pablitar.vainilla.commons.math.Vector2D

object TilesApp extends App {
  val DISPLAY_WIDTH = 1280
  val DISPLAY_HEIGHT = 720
  
  def screenCenter = {
    Vector2D(DISPLAY_WIDTH / 2 , DISPLAY_HEIGHT / 2)
}
  new DesktopGameLauncher(TilesGame).launch
}