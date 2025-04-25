package com.nablarch.example.action;

import com.nablarch.example.dto.IndustryDto;
import com.nablarch.example.service.IndustryService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    public List<IndustryDto> list() {
        return industryService.findAll();
    }
}
