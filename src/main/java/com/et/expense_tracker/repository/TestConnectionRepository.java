package com.et.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.et.expense_tracker.model.TestConnection;

public interface TestConnectionRepository extends JpaRepository<TestConnection, Long> {
    
}
