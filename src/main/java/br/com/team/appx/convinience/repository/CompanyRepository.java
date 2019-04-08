package br.com.team.appx.convinience.repository;

import br.com.team.appx.convinience.model.Company;
import br.com.team.appx.convinience.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
