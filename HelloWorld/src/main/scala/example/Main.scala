package example

object Main extends App {

  val projectIdeas = List(
    "Real life:     Contribute to open source Scala project",
    "OOP:           Build a to-do list manager (CRUD operations)",
    "String manip:  Text adventure game",
    "Logical:       Solve a programming interview problem (postfix calculator)",
    "Logical:       Cipher creation and breaking (https://learncryptography.com/classical-encryption/caesar-cipher)",
    "Networking:    Chat application - simple client/server, then chat room",
    "Threading:     Progress bar for task completion (progress bar is another thread that performs output)"
  )
  projectIdeas.foreach{println}
}
