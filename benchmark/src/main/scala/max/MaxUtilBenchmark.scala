package org.example.max

import annotation.tailrec
import com.google.caliper.{Param,Runner => CaliperRunner}
import org.example.SimpleScalaBenchmark

object MaxUtilBenchmark {

  def main(args: Array[String]) {
    CaliperRunner.main(classOf[MaxUtilBenchmark], args)
  }
}

class MaxUtilBenchmark extends SimpleScalaBenchmark {
  
  @Param(Array("10", "100", "1000", "10000"))
  val length: Int = 0
  
  var array: Array[Int] = _
  
  override def setUp() {
    array = new Array(length)
  }

  def timeForeach(reps: Int) = repeat(reps) {
    MaxUtil.maxForeach(array)
  }
  
  def timeWhile(reps: Int) = repeat(reps) {  
    MaxUtil.maxWhile(array)
  }

  def timeForComp(reps: Int) = repeat(reps) {
    MaxUtil.maxForComp(array)
  }

  def timeTailRec(reps: Int) = repeat(reps) {
    MaxUtil.maxTailRec(array)
  }

  def timeBuiltIn(reps: Int) = repeat(reps) {
    MaxUtil.maxBuiltIn(array)
  }
  
  override def tearDown() {
    // None
  }
  
}
