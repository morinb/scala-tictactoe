package com.github.morinb.scala.model

object CellCoordinateConstants {
  val TOP = 0
  val MIDDLE = 1
  val BOTTOM = 2
  val LEFT = 0
  val RIGHT = 2

  val TOP_LEFT = CellCoordinate(TOP, LEFT)
  val TOP_MIDDLE = CellCoordinate(TOP, MIDDLE)
  val TOP_RIGHT = CellCoordinate(TOP, RIGHT)

  val MIDDLE_LEFT = CellCoordinate(MIDDLE, LEFT)
  val MIDDLE_MIDDLE = CellCoordinate(MIDDLE, MIDDLE)
  val MIDDLE_RIGHT = CellCoordinate(MIDDLE, RIGHT)

  val BOTTOM_LEFT = CellCoordinate(BOTTOM, LEFT)
  val BOTTOM_MIDDLE = CellCoordinate(BOTTOM, MIDDLE)
  val BOTTOM_RIGHT = CellCoordinate(BOTTOM, RIGHT)

  val COORDINATES = List(
    TOP_LEFT,
    TOP_MIDDLE,
    TOP_RIGHT,
    MIDDLE_LEFT,
    MIDDLE_MIDDLE,
    MIDDLE_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_MIDDLE,
    BOTTOM_RIGHT
  )

}
