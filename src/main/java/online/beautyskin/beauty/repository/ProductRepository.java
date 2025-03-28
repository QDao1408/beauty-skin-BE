package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Category;
import online.beautyskin.beauty.entity.Image;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {


    @Query("select p from Product p where p.name like %:name%")
    public List<Product> findByName(String name);
    public List<Product> findByIsDeletedFalse();
    public Product findById(long id);
    List<Product> findByCategory(Category category);

    List<Product> findBySkinTypesIdAndIsDeletedFalse(long id);

    List<Product> findBySkinConcernsIdAndIsDeletedFalse(long concernId);

    List<Product> findByTagsIdAndIsDeletedFalse(long tagId);

    List<Product> findByRoutineStepsIdAndIsDeletedFalse(long stepId);

    List<Product> findByFormsIdAndIsDeletedFalse(long id);
    List<Image> findByImagesIdAndIsDeletedFalse(long id);

    Product findByIdAndIsDeletedFalse(long id);

    List<Product> getByCategory(Category category);

    @Query("SELECT p.name, SUM(od.quantity) AS totalSold " +
            "FROM OrderDetail od " +
            "JOIN od.product p " +
            "JOIN od.order o " +
            "WHERE o.orderStatus = :orderStatus " +
            "AND o.paymentStatus = :paymentStatus " +
            "AND MONTH(o.orderDate) = MONTH(CURRENT_DATE) " +
            "AND YEAR(o.orderDate) = YEAR(CURRENT_DATE) " +
            "GROUP BY p.id, p.name " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTop5BestSellingProduct();

    @Query("SELECT COALESCE(AVG(r.rating), 0) " +
            "FROM Feedback r " +
            "WHERE r.product.id = :productId")
    Double findAverageRatingByProductId(@Param("productId") Long productId);

    @Query("SELECT COALESCE(SUM(od.quantity), 0) " +
            "FROM OrderDetail od " +
            "JOIN od.order o " +
            "WHERE od.product.id = :productId " +
            "AND o.orderStatus = 'DELIVERED' " +
            "AND o.paymentStatus = 'PAID'")
    Long findTotalSoldByProductId(@Param("productId") Long productId);

    @Query("SELECT p.name, SUM(od.quantity) AS totalSold " +
            "FROM OrderDetail od " +
            "JOIN od.product p " +
            "JOIN od.order o " +
            "WHERE o.orderStatus = :orderStatus " +
            "AND o.paymentStatus = :paymentStatus " +
            "AND MONTH(o.orderDate) = MONTH(CURRENT_DATE) " +
            "AND YEAR(o.orderDate) = YEAR(CURRENT_DATE) " +
            "GROUP BY p.id, p.name " +
            "ORDER BY totalSold DESC " +
            "LIMIT 5")
    List<Object[]> findTop5BestSellingProductsThisMonth(@Param("orderStatus") OrderStatusEnums orderStatus,
                                                        @Param("paymentStatus") PaymentStatusEnums paymentStatus);
}
