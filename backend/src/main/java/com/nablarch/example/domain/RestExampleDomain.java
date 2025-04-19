package com.nablarch.example.domain;

import com.nablarch.example.code.ProjectClass;
import com.nablarch.example.code.ProjectType;
import nablarch.core.validation.ee.DateFormat;
import nablarch.core.validation.ee.Digits;
import nablarch.core.validation.ee.EnumElement;
import nablarch.core.validation.ee.Length;
import nablarch.core.validation.ee.NumberRange;
import nablarch.core.validation.ee.SystemChar;

/**
 * ドメインを定義したBean。
 *
 * @author Nabu Rakutaro
 */
@SuppressWarnings("all")
public class RestExampleDomain {
    /** ID */
    @NumberRange(min = 0)
    @Digits(integer = 9)
    private String id;

    /** プロジェクト名 */
    @Length(max = 64)
    @SystemChar(charsetDef = "全角文字")
    private String projectName;

    /** 新規開発PJ、または保守PJを表すコード値 */
    @EnumElement(ProjectType.class)
    private String projectType;

    /** プロジェクトの規模を表すコード値 */
    @EnumElement(ProjectClass.class)
    private String projectClass;

    /** 日付 */
    @DateFormat
    private String date;

    /** ユーザ氏名（漢字） */
    @Length(max = 64)
    @SystemChar(charsetDef = "全角文字")
    private String userName;

    /** 備考 */
    @Length(max = 64)
    @SystemChar(charsetDef = "システム許容文字", allowLineSeparator = true)
    private String note;

    /** 金額 */
    @NumberRange(min = 0, max = 999999999, message = "{nablarch.core.validation.ee.NumberRange.money.range.message}")
    private String amountOfMoney;

    /** バージョン */
    @NumberRange(min = 0)
    @Digits(integer = 9)
    private String version;

}
