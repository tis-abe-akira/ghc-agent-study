package com.nablarch.example.form;

import nablarch.core.validation.ee.Domain;

import java.io.Serializable;

/**
 * 業種検索フォーム
 *
 * @author abe.akira
 */
public class IndustrySearchForm implements Serializable {
    /** シリアルバージョンUID */
    private static final long serialVersionUID = 1L;

    /** 業種コード */
    @Domain("industryCode")
    private String industryCode;

    /** 業種名 */
    @Domain("industryName")
    private String industryName;

    /**
     * 業種コードを取得する。
     * @return 業種コード
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * 業種コードを設定する。
     * @param industryCode 業種コード
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    /**
     * 業種名を取得する。
     * @return 業種名
     */
    public String getIndustryName() {
        return industryName;
    }

    /**
     * 業種名を設定する。
     * @param industryName 業種名
     */
    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

}
