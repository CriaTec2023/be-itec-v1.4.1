package com.ms.itec.infrastructure.persistence

import com.ms.itec.domain.repository.ContentRepository
import com.ms.itec.entity.Content
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContentPersistence: JpaRepository<Content, String>, ContentRepository