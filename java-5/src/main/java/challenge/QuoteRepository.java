package challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Integer> {

    @Query(value = "SELECT id, actor, detail FROM scripts ORDER BY RANDOM() LIMIT 1;", nativeQuery = true)
    Quote findQuote();

    @Query(value = "SELECT id, actor, detail FROM scripts WHERE actor LIKE :actor ORDER BY RANDOM() LIMIT 1;",
            nativeQuery = true)
    Quote findByActor(@Param("actor") String actor);	
}