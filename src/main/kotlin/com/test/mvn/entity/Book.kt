package com.test.mvn.entity

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
    var title: String? = null
    var description: String? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "book_author",
        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "author_id", referencedColumnName = "id")]
    )
    var authors: Set<Author>? = null
}

interface BookRepository : JpaRepository<Book, Int?> {

}