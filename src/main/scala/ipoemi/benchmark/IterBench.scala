package ipoemi.benchmark

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
class IterationOpsBench {

  @Param(Array("20", "2000", "200000"))
  var n: Int = _

  var l: List[String] = _
  var v: Vector[String] = _
  var a: Array[String] = _
  var s: Stream[String] = _

  @Setup
  def setup(): Unit = {
    a = Array.fill(n)("1")
    l = List.fill(n)("1")
    v = Vector.fill(n)("1")
    s = Stream.fill(n)("1")
  }

  @Benchmark
  def iterArray(): Array[String] = {
    val b = new Array[String](a.length)
    var i = 0
    while (i < a.length) {
      b(i) = a(i) + "."
      i += 1
    }
    b
    //val b = Array.ofDim[String](a.length)
    //for (i <- a.indices) {
    //  b(i) = a(i) + "."
    //}
    //b
  }

  @Benchmark
  def mapArray(): Array[String] = {
    a.map(_ + ".")
  }

  @Benchmark
  def mapList(): List[String] = {
    l.map(_ + ".")
  }

  @Benchmark
  def mapVector(): Vector[String] = {
    v.map(_ + ".")
  }

  @Benchmark
  def mapStream(): Stream[String] = {
    s.map(_ + ".").force
  }

  @Benchmark
  def mapStream2(): Unit = {
    s.map(_ + ".").foreach(_ => ())
  }

}
