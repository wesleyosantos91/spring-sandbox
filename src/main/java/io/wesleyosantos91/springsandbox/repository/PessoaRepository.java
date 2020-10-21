package io.wesleyosantos91.springsandbox.repository;

import io.wesleyosantos91.springsandbox.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
