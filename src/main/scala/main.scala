package org.pawelsberg.notebook
import java.io
import scala.swing.{Button, Dimension, EditorPane, ScrollPane, BorderPanel, Frame, Label}
import scala.swing.event.{ButtonClicked, WindowClosing}
import scala.sys.exit

@main
def main(): Unit = {
  val fileName = "notebook.txt"
  new Frame {
    val editorPane = new EditorPane() {
      text = readFromFile(fileName)
    }
    title = "Notebook"
    preferredSize = new Dimension(800,600)
    contents = new BorderPanel {
      add(new ScrollPane(editorPane),BorderPanel.Position.Center)
    }
    reactions += {
      case WindowClosing(_) =>
        printToFile(fileName,editorPane.text)
        exit(0)
    }
    pack()
    centerOnScreen()
    open()
  }
}

// Tools
import java.io.{File,PrintWriter}
import java.nio.file.{Files,Paths}
def readFromFile(fileName: String): String =
  val exists = File(fileName).isFile
  if exists
  then String(Files.readAllBytes(Paths.get(fileName)))
  else ""

def printToFile(fileName:String, text: String): Unit =
  val pw = PrintWriter(File(fileName))
  pw.print(text)
  pw.close()
