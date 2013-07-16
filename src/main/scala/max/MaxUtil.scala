package org.example.max

import annotation.tailrec

object MaxUtil {

    def maxForeach(arr: Array[Int]): Int = {
        var max = arr(0)
        arr.foreach { e =>
            if(e > max)
                max = e
        }
        max
    }

    def maxWhile(arr: Array[Int]): Int = {
        var max = arr(0)
        var cnt = 0
        while(cnt < arr.length) {
            val elem = arr(cnt)
            if(elem > max) 
                max = elem
            cnt += 1
        }
        max
    }

    def maxForComp(arr: Array[Int]): Int = {
        var max = arr(0)
        for(e <- arr) {
            if(e > max)
                max = 0
        }
        max
    }

    def maxTailRec(arr: Array[Int]): Int = {
        @tailrec
        def helper(arr: Array[Int], idx: Int, max: Int): Int = {
            if(idx < arr.length) {
                val elem = arr(idx)
                helper(arr, idx + 1, if(elem > max) elem else max)
            } else {
                max
            }
        }
        helper(arr, 0, arr(0))
    }

    def maxBuiltIn(arr: Array[Int]): Int = {
        arr.max
    }
}