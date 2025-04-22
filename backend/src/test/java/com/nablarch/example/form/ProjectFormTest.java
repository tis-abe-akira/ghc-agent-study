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
public class ProjectFormTest {
    private static final Class<?> targetClass = ProjectForm.class;

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
    public void 項目間精査結果が正しいことを検証する() {

        support.testBeanValidation(targetClass, "testBeanValidation");
    }

    @Test
    public void clientIdが半角英字を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("a");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが半角記号を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId(":");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが半角カナを許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("ｱ");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角英字を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("Ａ");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角数字を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("１");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角ひらがなを許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("あ");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角カタカナを許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("ア");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角漢字を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("亜");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角記号その他を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("？");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが外字を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("㈱");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが中国語を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("你");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdがサロゲートペアを許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("𩸽");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが改行を許容しないことを検証する(){
        ProjectForm form = new ProjectForm();
        form.setClientId("\n");

        List<String> result = getActualMessages(form);

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }


    private List<String> getActualMessages(ProjectForm form) {
        return new ConstraintViolationConverterFactory().create().convert(
                ValidatorUtil.getValidator().validateProperty(form, "clientId")
        ).stream().map(Message::formatMessage).collect(Collectors.toList());
    }
}