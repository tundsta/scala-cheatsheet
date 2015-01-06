trait Collection[+T] {  

	def add[U >: T](other: U): Collection[U] 
}