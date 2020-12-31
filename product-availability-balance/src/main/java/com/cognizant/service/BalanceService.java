package com.cognizant.service;

import com.cognizant.model.BalanceList;
import com.cognizant.model.Product;
import com.cognizant.repository.BalanceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public BalanceList getAllAvailableItems(int pageNum, String sortBy, boolean isAscending) {
        Pageable paging = pagination(pageNum, sortBy, isAscending);
        BalanceList list = new BalanceList(this.balanceRepository.findAllPageable(paging),
                this.balanceRepository.countNumberOfAllItems());
        return list;
    }

    public BalanceList getAvailableItemsByLocation(int locId, int pageNum, String sortBy, boolean isAscending) {
        Pageable paging = pagination(pageNum, sortBy, isAscending);
        BalanceList list = new BalanceList(this.balanceRepository.findByLocationPageable(locId, paging),
                this.balanceRepository.countNumberOfItemsByLocation(locId));
        return list;
    }

    public BalanceList getAvailableItemsByProduct(int prodId, int pageNum, String sortBy, boolean isAscending) {
        if (pageNum < 0) {
            Product prod = new Product();
            prod.setId(prodId);
            BalanceList list = new BalanceList(this.balanceRepository.findByProduct(prod), 0);
            return list;
        } else {
            Pageable paging = pagination(pageNum, sortBy, isAscending);
            BalanceList list = new BalanceList(this.balanceRepository.findByProductPageable(prodId, paging),
                    this.balanceRepository.countNumberOfItemsByProduct(prodId));
            return list;
        }
    }

    public BalanceList getAvailableItemsByDept(int deptId, int pageNum, String sortBy, boolean isAscending) {
        Pageable paging = pagination(pageNum, sortBy, isAscending);
        BalanceList list = new BalanceList(this.balanceRepository.findByDeptPageable(deptId, paging),
                this.balanceRepository.countNumberOfItemsByDept(deptId));
        return list;
    }

    public BalanceList getAvailableItemsByLocationAndProduct(int locId, int prodId, int pageNum, String sortBy, boolean isAscending) {
        Pageable paging = pagination(pageNum, sortBy, isAscending);
        BalanceList list = new BalanceList(this.balanceRepository.findByProductAndLocationPageable(prodId, locId, paging),
                this.balanceRepository.countNumberOfItemsByProductAndLocation(prodId, locId));
        return list;
    }

    public BalanceList getAvailableItemsByLocationAndDept(int locId, int deptId, int pageNum, String sortBy, boolean isAscending) {
        Pageable paging = pagination(pageNum, sortBy, isAscending);
        BalanceList list = new BalanceList(this.balanceRepository.findByDeptAndLocationPageable(deptId, locId, paging),
                this.balanceRepository.countNumberOfItemsByDeptAndLocation(deptId, locId));
        return list;
    }

    public Pageable pagination(int pageNum, String sortBy, boolean ascending) {
        return ascending ? PageRequest.of(pageNum, 8, Sort.by(sortBy).ascending())
                : PageRequest.of(pageNum, 8, Sort.by(sortBy).descending());
    }
}
