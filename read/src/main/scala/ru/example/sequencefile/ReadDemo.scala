package ru.example.sequencefile

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.SequenceFile.Reader
import org.apache.hadoop.io.{IntWritable, SequenceFile, Text}
import java.io.IOException

object ReadDemo {
  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Usage: read <input-filename>")
      sys.exit(-1)
    }

    val conf = new Configuration()
    val inFile = new Path(args(0))
    val key = new IntWritable()
    val value = new Text()
    val reader = new SequenceFile.Reader(
      conf,
      Reader.file(inFile),
      Reader.bufferSize(4096)
    )

    try {
      while (reader.next(key, value)) println(s"Key $key\tValue $value")
    } catch {
      case e: IOException => println(e.getLocalizedMessage)
    } finally {
      if (reader != null) reader.close()
    }
  }
}
