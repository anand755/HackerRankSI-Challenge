package string.modulo.easy

import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter}
import java.lang._

object StringModuloEasy {
  def main(args: Array[String]): Unit = {

    val reader: BufferedReader = new BufferedReader(new InputStreamReader(System.in))
    val writer: BufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))

    var testCaseCount: Int = Integer.parseInt(reader.readLine())
    while ( {
      testCaseCount -= 1
      testCaseCount + 1
    } > 0) {
      val NP: Array[String] = reader.readLine().split("\\s")
      val N: String = NP(0)
      val P: String = NP(1)
      val ans: Long = getNModP(N, P)
      writer.write(ans + "\n")
      writer.flush()
    }
  }

  private def getNModP(N: String, P: String): Long = {
    val lengthN: Int = N.length
    var power: Long = 1L
    val PLong: Long = Long.parseLong(P)
    var ans: Long = Integer.parseInt(String.valueOf(N.charAt(lengthN - 1))) % PLong
    for (i <- 1 until lengthN) {
      power = (power * 10) % PLong
      val digitVal: Long = (Integer.parseInt(String.valueOf(N.charAt(lengthN - 1 - i))) * power) % PLong
      ans = (ans + digitVal) % PLong
    }
    ans
  }
}
