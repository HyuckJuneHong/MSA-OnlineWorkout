package kr.co.owocatalog.service;

import kr.co.owocatalog.domain.dto.CatalogDto;
import kr.co.owocatalog.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public CatalogDto.READ getAllCatalog() {
        return null;
    }
}
