package com.github.morinb.scala.model

case class CellCoordinate(row: Int, col: Int) {
  require(row >= 0, "Row must be a positive number")
  require(col >= 0, "Col must be a positive number")
}

