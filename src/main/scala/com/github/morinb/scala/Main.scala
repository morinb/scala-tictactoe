package com.github.morinb.scala

import com.github.morinb.scala.engine.{DummyEngine, Engine}
import com.github.morinb.scala.model.{Circle, Cross, Empty, Grid}

object Main {

  def main(args: Array[String]): Unit = {
    val engineCross: Engine = new DummyEngine(Cross)
    val engineCircle: Engine = new DummyEngine(Circle)
    var grid = Grid()

    var currentEngine = engineCircle
    while (grid.winner().isEmpty) {
      grid = currentEngine.playNextMove(grid)
      println(grid)
      println
      currentEngine = currentEngine.player match {
        case Circle => engineCross
        case Cross => engineCircle
        case Empty => throw new IllegalArgumentException // Should never occur
      }
    }
    grid.winner() match {
      case Some(s) => println(grid(s.head) + " WINS !")
      case None => println("It's a DRAW")
    }
  }


}
