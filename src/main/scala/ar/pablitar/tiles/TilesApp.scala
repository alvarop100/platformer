package ar.pablitar.tiles

import com.uqbar.vainilla.DesktopGameLauncher

object TilesApp extends App {
  val DISPLAY_WIDTH = 1280
  val DISPLAY_HEIGHT = 720
  new DesktopGameLauncher(TilesGame).launch
}