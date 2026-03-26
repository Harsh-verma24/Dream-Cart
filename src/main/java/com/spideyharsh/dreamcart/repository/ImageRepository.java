package com.spideyharsh.dreamcart.repository;

import com.spideyharsh.dreamcart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
