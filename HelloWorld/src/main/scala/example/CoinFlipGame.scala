package example

import scala.annotation.tailrec
import scala.util.Random

/**
  * var input = ""
  *
  * while (input != "q") {
  * //prompt the player to select heads, tails, or quit
  * //get the player's input
  *if (input == "q") {
  *print the game summary
  *quit
  *}
  * //flip the coin
  * //see if the player guessed correctly
  * //print the #flips and #correct
  *
  * }
  */

case class GameState(
                      numFlips: Int,
                      numCorrectGuesses: Int
                    )

case class GlobalGameState(pastGames: List[GameState])

object CoinFlipGame extends App {

  val globalgs = GlobalGameState(Nil)
  val gs = GameState(0, 0)
  val r = new Random()
  runGame(globalgs, gs, r)

  def showPrompt(): Unit = { print("\n(h)eads, (t)ails, or (q)uit: ") }

  def getUserInput:String = scala.io.StdIn.readLine.trim.toUpperCase

  def printableFlipResult(flip: String) = flip match {
    case "H" => "Heads"
    case "T" => "Tails"
  }

  def printGameState(printableResult: String, gameState: GameState): Unit = {
    print(s"Flip was $printableResult. ")
    printGameState(gameState)
  }

  def printGameState(gameState: GameState): Unit = {
    println(s"#Flips: ${gameState.numFlips}, #Correct: ${gameState.numCorrectGuesses}")
  }

  def printGlobalGameState(globalGameState: GlobalGameState): Unit = {
    globalGameState.pastGames.foreach(printGameState)
  }

  def printGameOver(): Unit = println("\n=== GAME OVER ===")

  def printHelp(): Unit = println("Type n for new game, q for quit, h for heads or t for tails")

  def printNewGame(): Unit = println("Starting new game...")

  // returns "H" for heads, "T" for tails
  def tossCoin(r: Random):String = {
    val i = r.nextInt(2)
    i match {
      case 0 => "H"
      case 1 => "T"
    }
  }

  /**
    * To write multi game state: create a multigamestate
    * that tracks the outcome of each game
    * @param gameState
    * @param r
    */
  @tailrec
  private def runGame(globalGameState: GlobalGameState, gameState: GameState, r: Random): Unit = {

    printHelp()
    val inp = getUserInput
    inp match {
      case "Q" => {
        printGameOver()
        val newGlobal = GlobalGameState(globalGameState.pastGames :+ gameState)
        printGlobalGameState(newGlobal)
      }
      case "N" => {
        printNewGame()
        val newGlobal = GlobalGameState(globalGameState.pastGames :+ gameState)
        val newState = GameState(0, 0)
        runGame(newGlobal, newState, r)
      }
      case "H" | "T" =>
        val tossResult = tossCoin(r)
        val isCorrectResult = inp == tossResult
        if (isCorrectResult) {
          // new immutable game state created
          val newState = GameState(gameState.numFlips + 1, gameState.numCorrectGuesses + 1)
          printGameState(printableFlipResult(inp), newState)
          runGame(globalGameState, newState, r)
        }
        else {
          // new immutable game state created
          val newState = GameState(gameState.numFlips + 1, gameState.numCorrectGuesses)
          printGameState(printableFlipResult(inp), newState)
          runGame(globalGameState, newState, r)
        }
      case _ => printHelp()
    }
  }


}
