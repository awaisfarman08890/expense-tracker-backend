package com.awais.expense.tracker.repository;

import com.awais.expense.tracker.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
