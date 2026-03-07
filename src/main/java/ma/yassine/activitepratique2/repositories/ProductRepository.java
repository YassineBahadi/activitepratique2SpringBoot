package ma.yassine.activitepratique2.repositories;

import ma.yassine.activitepratique2.entities.Product;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pc
 **/
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByNameContains(String keyword, Pageable pageable);
}
