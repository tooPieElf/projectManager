package com.springfullstack.ppmt.repository;

import com.springfullstack.ppmt.domain.BackLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BacklogRepository extends JpaRepository<BackLog, Long> {
BackLog findByProjectIdentifier(String Identifier);

}
