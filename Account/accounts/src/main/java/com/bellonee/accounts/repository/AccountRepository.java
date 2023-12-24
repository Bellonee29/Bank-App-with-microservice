package com.bellonee.accounts.repository;

import com.bellonee.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, Long> {

}
