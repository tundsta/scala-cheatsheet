//Error No Manifest available for  'T'
 private def attributeToLocation[T:Manifest] = (av:AttributeValue) => {
   (parse(LocationCompressionService.decompress(av.getB)).extract[List[T]]).toVector

  }