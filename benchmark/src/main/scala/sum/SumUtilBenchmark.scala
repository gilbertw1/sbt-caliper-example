package org.example.sum

import annotation.tailrec
import com.google.caliper.{Param,Runner => CaliperRunner}
import org.example.SimpleScalaBenchmark

object SumUtilBenchmark {

  def main(args: Array[String]) {
    CaliperRunner.main(classOf[SumUtilBenchmark], args)
  }
}

class SumUtilBenchmark extends SimpleScalaBenchmark {
  
  @Param(Array("10", "100", "1000", "10000"))
  val length: Int = 0
  
  var array: Array[Int] = _
  
  override def setUp() {
    array = new Array(length)
  }

  def timeForeach(reps: Int) = repeat(reps) {
    SumUtil.sumForeach(array)
  }
  
  def timeWhile(reps: Int) = repeat(reps) {  
    SumUtil.sumWhile(array)
  }

  def timeForComp(reps: Int) = repeat(reps) {
    SumUtil.sumForComp(array)
  }

  def timeTailRec(reps: Int) = repeat(reps) {
    SumUtil.sumTailRec(array)
  }

  def timeBuiltIn(reps: Int) = repeat(reps) {
    SumUtil.sumBuiltIn(array)
  }
  
  override def tearDown() {
    // None
  }
  
}
