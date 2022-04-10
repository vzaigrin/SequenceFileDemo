package ru.example.sequencefile

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.SequenceFile.Writer
import org.apache.hadoop.io.compress.GzipCodec
import org.apache.hadoop.io.{IntWritable, SequenceFile, Text}
import java.io.IOException

object WriteDemo {
  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Usage: write <output-filename>")
      sys.exit(-1)
    }

    val conf = new Configuration()
    val outFile = new Path(args(0))
    val key = new IntWritable()
    val value = new Text()
    val writer = SequenceFile.createWriter(
      conf,
      Writer.file(outFile),
      Writer.keyClass(key.getClass),
      Writer.valueClass(value.getClass),
      Writer.compression(SequenceFile.CompressionType.BLOCK, new GzipCodec())
    )
    try {
      (0 until 100).foreach { i =>
        key.set(i)
        value.set(i.toString)
        println(s"${writer.getLength}\t$key\t$value")
        writer.append(key, value)
      }
    } catch {
      case e: IOException => println(e.getLocalizedMessage)
    } finally {
      if (writer != null) writer.close()
    }
  }
}
