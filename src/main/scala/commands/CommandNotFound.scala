package main.scala.commands

/**
  * Created by AFarashutdinov on 13.08.2016.
  */
class CommandNotFound extends Command{
  //there must be some text about bot + help page corresponding to request + general help page
  def getAnswer() = "command not found"
}
