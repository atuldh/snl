package models


trait Chance

case object Safe extends Chance
case object Win extends Chance
case class Snake(start: Int, end: Int) extends Chance
case class Ladder(start: Int, end: Int) extends Chance

