package com.nablarch.example.service;

import com.nablarch.example.dto.IndustryDto;
import com.nablarch.example.entity.Industry;
import nablarch.common.dao.UniversalDao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 業種に関するサービスクラス。
 */
public class IndustryService {

    /**
     * 全業種を取得する。
     * @return 業種一覧
     */
    public List<IndustryDto> findAll() {
        List<Industry> industries = UniversalDao.findAll(Industry.class);
        
        // エンティティをDTOに変換
        return industries.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * エンティティをDTOに変換する。
     * @param entity 業種エンティティ
     * @return 業種DTO
     */
    private IndustryDto convertToDto(Industry entity) {
        IndustryDto dto = new IndustryDto();
        dto.setIndustryCode(entity.getIndustryCode());
        dto.setIndustryName(entity.getIndustryName());
        return dto;
    }
}
