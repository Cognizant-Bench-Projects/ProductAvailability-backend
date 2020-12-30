package com.cognizant.repository;

import com.cognizant.model.Balance;
import com.cognizant.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends PagingAndSortingRepository<Balance, Integer> {

    List<Balance> findByProduct(Product prod);

    @Query("FROM Balance")
    List<Balance> findAllPageable(Pageable paging);

    @Query("FROM Balance b WHERE b.location.id = :loc")
    List<Balance> findByLocationPageable(int loc, Pageable paging);

    @Query("FROM Balance b WHERE b.product.id = :prod")
    List<Balance> findByProductPageable(int prod, Pageable paging);

    @Query("FROM Balance b WHERE b.product.dept.id = :dept")
    List<Balance> findByDeptPageable(int dept, Pageable paging);

    @Query("FROM Balance b WHERE b.product.id = :prod AND b.location.id = :loc")
    List<Balance> findByProductAndLocationPageable(int prod, int loc, Pageable paging);

    @Query("FROM Balance b WHERE b.product.dept.id = :dept AND b.location.id = :loc")
    List<Balance> findByDeptAndLocationPageable(int dept, int loc, Pageable paging);

    @Query("SELECT COUNT(b) FROM Balance b")
    int countNumberOfAllItems();

    @Query("SELECT COUNT(b) FROM Balance b WHERE b.product.id = :prod")
    int countNumberOfItemsByProduct(int prod);

    @Query("SELECT COUNT(b) FROM Balance b WHERE b.location.id = :loc")
    int countNumberOfItemsByLocation(int loc);

    @Query("SELECT COUNT(b) FROM Balance b WHERE b.product.dept.id = :dept")
    int countNumberOfItemsByDept(int dept);

    @Query("SELECT COUNT(b) FROM Balance b WHERE b.product.id = :prod AND b.location.id = :loc")
    int countNumberOfItemsByProductAndLocation(int prod, int loc);

    @Query("SELECT COUNT(b) FROM Balance b WHERE b.product.dept.id = :dept AND b.location.id = :loc")
    int countNumberOfItemsByDeptAndLocation(int dept, int loc);
}
