package com.github.morinb.scala.model

import org.scalatest.FunSuite

class CellCoordinateTest extends FunSuite {

  test("A Cell Row Coordinate should be positive") {
    assertThrows[IllegalArgumentException] {
      CellCoordinate(-1, 0)
    }
  }

  test("A Cell Col Coordinate should be positive") {
    assertThrows[IllegalArgumentException] {
      CellCoordinate(0, -1)
    }
  }

}
