package com.github.morinb.scala.engine

import com.github.morinb.scala.model._

import scala.util.Random

trait Engine {

  def playNextMove(grid: Grid): Grid

  def player: Cell
}

/**
  * Dummy engine plays randomly in any empty cell
  *
  * @param _player the Circle or Cross
  */
class DummyEngine(val _player: Cell) extends Engine {
  val rand = new Random(System.currentTimeMillis())
  require(_player == Circle || _player == Cross)

  override def playNextMove(grid: Grid): Grid = {
    val emptyCells = grid.emptyCells()
    val index = rand.nextInt(emptyCells.length)

    val coordinate = emptyCells(index)
    grid.play(coordinate, player)
  }

  override def player: Cell = _player
}
