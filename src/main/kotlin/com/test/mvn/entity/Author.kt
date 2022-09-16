package com.test.mvn.entity

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    var name: String? = null

    @ManyToMany(mappedBy = "authors")
    var books: Set<Book>? = null
}

interface AuthorRepository : JpaRepository<Author, Int?>{

}