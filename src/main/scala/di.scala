trait UserRepositoryComponent {
  def userRepository: UserRepository

  trait UserRepository {
    def get(id: Int): User
    def find(username: String): User
  }
}
