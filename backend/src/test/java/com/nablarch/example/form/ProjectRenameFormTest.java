package com.nablarch.example.form;

import nablarch.core.message.Message;
import nablarch.core.validation.ee.ConstraintViolationConverterFactory;
import nablarch.core.validation.ee.ValidatorUtil;
import nablarch.test.core.db.EntityTestSupport;
import nablarch.test.junit5.extension.db.EntityTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EntityTest
public class ProjectRenameFormTest {
    private static final Class<?> targetClass = ProjectRenameForm.class;

    private EntityTestSupport support;

    @Test
    public void 文字列長と文字種の単項目精査結果が正しいことを検証する() {
        String sheetName = "testCharsetAndLength";
        String id = "charsetAndLength";
        support.testValidateCharsetAndLength(targetClass, sheetName, id);
    }

    @Test
    public void 文字列長と文字種以外の単項目精査結果が正しいことを検証する() {
        String sheetName = "testSingleValidation";
        String id = "singleValidation";
        support.testSingleValidation(targetClass, sheetName, id);
    }

    @Test
    public void projectIdが半角英字を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("a");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが半角記号を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId(":");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが半角カナを許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("ｱ");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角英字を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("Ａ");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角数字を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("１");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角ひらがなを許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("あ");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角カタカナを許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("ア");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角漢字を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("亜");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角記号その他を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("？");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが外字を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("㈱");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが中国語を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("你");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdがサロゲートペアを許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("𩸽");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが改行を許容しないことを検証する(){
        ProjectRenameForm form = new ProjectRenameForm();
        form.setProjectId("\n");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }


    private List<String> getActualMessages(ProjectRenameForm form) {
        return new ConstraintViolationConverterFactory().create().convert(
                ValidatorUtil.getValidator().validateProperty(form, "projectId")
        ).stream().map(Message::formatMessage).collect(Collectors.toList());
    }
}