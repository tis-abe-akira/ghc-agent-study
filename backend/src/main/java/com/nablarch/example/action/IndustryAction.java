package com.nablarch.example.action;

import com.nablarch.example.dto.IndustryDto;
import com.nablarch.example.dto.IndustrySearchDto;
import com.nablarch.example.form.IndustrySearchForm;
import com.nablarch.example.service.IndustryService;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Validation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nablarch.core.beans.BeanUtil;
import nablarch.core.validation.ee.ValidatorUtil;
import nablarch.fw.jaxrs.JaxRsHttpRequest;

import java.util.List;

/**
 * 業種に関するアクション。
 */
@Path("/industries")
public class IndustryAction {

    /** 業種サービス */
    private IndustryService industryService = new IndustryService();

    /**
     * 全業種の一覧を取得する。
     *
     * @return 業種一覧
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<IndustryDto> find(JaxRsHttpRequest req) {

        // リクエストパラメータをBeanに変換
        IndustrySearchForm from = BeanUtil.createAndCopy(IndustrySearchForm.class, req.getParamMap());
        
        // BeanValidation実行
        ValidatorUtil.validate(from);

        IndustrySearchDto searchCondition = BeanUtil.createAndCopy(IndustrySearchDto.class, from);

        // 業種情報を取得
        return industryService.findAll(searchCondition);
    }
}
