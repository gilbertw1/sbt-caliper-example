package org.example.sum

import annotation.tailrec

object SumUtil {

    def sumForeach(arr: Array[Int]): Int = {
        var result = 0
        arr.foreach {
            result += _
        }
        result
    }

    def sumWhile(arr: Array[Int]): Int = {
        var result = 0
        var cnt = 0
        while(cnt < arr.length) {
            result += arr(cnt)
            cnt += 1
        }
        result
    }

    def sumForComp(arr: Array[Int]): Int = {
        var result = 0
        for(i <- arr) {
            result += i
        }
        result
    }

    def sumTailRec(arr: Array[Int]): Int = {
        @tailrec
        def helper(arr: Array[Int], idx: Int, sum: Int): Int = {
            if(idx < arr.length)
                helper(arr, idx + 1, sum + arr(idx))
            else
                sum
        }
        helper(arr, 0, 0)
    }

    def sumBuiltIn(arr: Array[Int]): Int = {
        arr.sum
    }
}