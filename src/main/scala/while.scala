def uncompress(input: ByteBuffer) = {
    val bytes = input.array
    val bais = new ByteArrayInputStream(bytes)
    val baos = new ByteArrayOutputStream
    val is = new GZIPInputStream(bais)

    val chunkSize = 1024
    val buffer = new Array[Byte](chunkSize)
    //    var length = 0

    val iterator = Iterator.continually(is.read(buffer, 0, chunkSize)).takeWhile(_ != -1)
    iterator.foreach { l =>
      baos.write(buffer, 0, l)
    }

    val uncompressed = new String(baos.toByteArray, "UTF-8")
     logger.debug(s"compressing $input...")
    uncompressed
  }
