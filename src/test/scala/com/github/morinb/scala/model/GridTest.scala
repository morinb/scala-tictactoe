package com.github.morinb.scala.model

import com.github.morinb.scala.exception.{CellAlreadyUsedException, PlayerCantPlayTwiceException}
import com.github.morinb.scala.model.CellCoordinateConstants._
import org.scalatest.FunSuite

class GridTest extends FunSuite {

  def fixture = new {
    val grid = Grid()
  }

  test("Playing TOP LEFT with Circle") {
    val f = fixture

    val next = f.grid.play(TOP_LEFT, Circle)

    assert(Circle.toString === next(TOP_LEFT).toString)
  }

  test("Same player can't play twice") {
    assertThrows[PlayerCantPlayTwiceException] {
      val f = fixture

      f.grid.play(TOP_LEFT, Circle).play(TOP_MIDDLE, Circle)

    }
  }

  test("Can't play on already assigned cell"){
    assertThrows[CellAlreadyUsedException] {
      val f = fixture

      f.grid.play(TOP_MIDDLE, Circle).play(TOP_MIDDLE, Cross)
    }
  }

  test("Circle wins") {
    val f = fixture

    val o = f.grid.play(TOP_LEFT, Circle)
      .play(TOP_MIDDLE, Cross)
      .play(MIDDLE_LEFT, Circle)
      .play(MIDDLE_RIGHT, Cross)
      .play(BOTTOM_LEFT, Circle).winner()

    o match {
      case Some(s) => assert(Set(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT) === s)
      case None => fail("Has not found a winner !")
    }

  }

  test("isFull") {
    val f = fixture

    var g = f.grid.play(TOP_LEFT, Circle)
    assert(!g.isFull)

    g = g.play(TOP_MIDDLE, Cross)
    assert(!g.isFull)

    g = g.play(TOP_RIGHT, Circle)
    assert(!g.isFull)

    g = g.play(MIDDLE_LEFT, Cross)
    assert(!g.isFull)

    g = g.play(MIDDLE_MIDDLE, Circle)
    assert(!g.isFull)

    g = g.play(MIDDLE_RIGHT, Cross)
    assert(!g.isFull)

    g = g.play(BOTTOM_LEFT, Circle)
    assert(!g.isFull)

    g = g.play(BOTTOM_MIDDLE, Cross)
    assert(!g.isFull)

    g = g.play(BOTTOM_RIGHT, Circle)
    assert(g.isFull)




  }

  test("Empty type toString is Space") {
    assert(" " === "" + Empty)
  }

  test("Circle type toString is O") {
    assert("O" === "" + Circle)
  }
  test("Cross type toString is X") {
    assert("X" === "" + Cross)
  }
  test("Empty grid toString") {

    val expected =

      " | | \n" +
        "-+-+-\n" +
        " | | \n" +
        "-+-+-\n" +
        " | | "

    val actual = Grid().toString()

    assert(expected === actual)

  }

}