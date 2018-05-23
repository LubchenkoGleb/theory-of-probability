package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Theme;
import com.kpi.diploma.perevertailo.repository.ThemeRepository;
import com.kpi.diploma.perevertailo.service.primary.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

}
