package com.github.morinb.scala.model

sealed trait Cell {

}

case object Circle extends Cell {
  override def toString: String = "O"
}

case object Cross extends Cell {
  override def toString: String = "X"
}

case object Empty extends Cell {
  override def toString: String = " "
}
