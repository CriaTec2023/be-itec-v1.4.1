package com.ms.itec.infrastructure.persistence

import com.ms.itec.domain.repository.ContentRepository
import com.ms.itec.entity.Content
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional
interface ContentPersistence: JpaRepository<Content, String>, ContentRepository