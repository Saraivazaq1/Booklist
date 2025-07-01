package dev.saraiva.Booklist.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saraiva.Booklist.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}
