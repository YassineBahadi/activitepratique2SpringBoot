package ma.yassine.activitepratique2.repositories;

import ma.yassine.activitepratique2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pc
 **/
public interface ProductRepository extends JpaRepository<Product,Long> {
}
