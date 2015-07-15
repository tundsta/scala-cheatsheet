val chars = Seq('a','b','c','d')
for{ c<-chars; b<-chars } yield c->b