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

/*
    @GetMapping("getName")
    fun servantName(
        req: HttpServletRequest,
        @RequestParam name: List<String>,
        @PageableDefault pageable: Pageable
    ): ResponseEntity<Any> {
        val specS : Specification<Servant?> = Specification<Servant?> { root: Root<Servant?>, query: CriteriaQuery<*>?, criteriaBuilder: CriteriaBuilder ->
            root.get<String>("name").`in`(name.toMutableList())
        }

        val spec2 : Specification<Servant?> = Specification<Servant?> { root: Root<Servant?>, query: CriteriaQuery<*>?, criteriaBuilder: CriteriaBuilder ->
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get<String>("name").`as`(String::class.java)),
                "%bu%"
            )
        }
        return ResponseEntity.ok(servantRepository.findAll(specS.or(spec2), pageable))
//        return ResponseEntity.ok(servantRepository.findAll(pageable))
    }*/
