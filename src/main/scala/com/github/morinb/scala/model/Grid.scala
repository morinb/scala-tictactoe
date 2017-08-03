package com.github.morinb.scala.model

import com.github.morinb.scala.exception.{CellAlreadyUsedException, PlayerCantPlayTwiceException}
import com.github.morinb.scala.model.CellCoordinateConstants._

import scala.annotation.tailrec

case class Grid(lastPlayer: Cell = Empty, grid: List[Cell] = List(Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty)) {


  val ROW_NUMBER = 3
  val COL_NUMBER = 3

  def apply(cc: CellCoordinate): Cell = grid(cc.row * COL_NUMBER + cc.col)

  def play(cc: CellCoordinate, playedCell: Cell): Grid = {
    if (lastPlayer.equals(playedCell)) {
      throw new PlayerCantPlayTwiceException
    }

    if (Empty != this (cc)) {
      throw new CellAlreadyUsedException
    }

    this.copy(lastPlayer = playedCell, this.grid.updated(cc.row * COL_NUMBER + cc.col, playedCell))
  }

  private[this] val CHECKS = Set(
    Set(TOP_LEFT, TOP_MIDDLE, TOP_RIGHT),
    Set(MIDDLE_LEFT, MIDDLE_MIDDLE, MIDDLE_RIGHT),
    Set(BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT),

    Set(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT),
    Set(TOP_MIDDLE, MIDDLE_MIDDLE, BOTTOM_MIDDLE),
    Set(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT),

    Set(TOP_LEFT, MIDDLE_MIDDLE, BOTTOM_RIGHT),
    Set(TOP_RIGHT, MIDDLE_MIDDLE, BOTTOM_LEFT)
  )

  def winner(): Option[Set[CellCoordinate]] = {
    (for {
      possibleWin <- CHECKS
      if possibleWin.map(cc => this (cc)).size == 1
    } yield possibleWin).headOption
  }

  def isFull: Boolean = {

    @tailrec
    def walk(ccs: List[CellCoordinate]): Boolean = {
      if (ccs.isEmpty) true
      else if (this (ccs.head) == Empty) false
      else walk(ccs.tail)
    }

    walk(COORDINATES)
  }

  override def toString: String = {

    s"${
      this (TOP_LEFT)
    }|${
      this (TOP_MIDDLE)
    }|${
      this (TOP_RIGHT)
    }\n" +
      "-+-+-\n" +
      s"${
        this (MIDDLE_LEFT)
      }|${
        this (MIDDLE_MIDDLE)
      }|${
        this (MIDDLE_RIGHT)
      }\n" +
      "-+-+-\n" +
      s"${
        this (BOTTOM_LEFT)
      }|${
        this (BOTTOM_MIDDLE)
      }|${
        this (BOTTOM_RIGHT)
      }"

  }

}
