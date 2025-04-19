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
public class ProjectUpdateFormTest {

    private static final Class<?> targetClass = ProjectUpdateForm.class;

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
    public void projectIdが半角英字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("a");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが半角記号を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId(":");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが半角カナを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("ｱ");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角英字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("Ａ");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角数字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("１");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角ひらがなを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("あ");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角カタカナを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("ア");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角漢字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("亜");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが全角記号その他を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("？");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが外字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("㈱");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが中国語を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("你");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdがサロゲートペアを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("𩸽");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void projectIdが改行を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId("\n");

        List<String> result = getActualMessages(form, "projectId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }


    @Test
    public void clientIdが半角英字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("a");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが半角記号を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId(":");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが半角カナを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("ｱ");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角英字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("Ａ");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角数字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("１");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角ひらがなを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("あ");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角カタカナを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("ア");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角漢字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("亜");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが全角記号その他を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("？");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが外字を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("㈱");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが中国語を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("你");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdがサロゲートペアを許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("𩸽");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    @Test
    public void clientIdが改行を許容しないことを検証する(){
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setClientId("\n");

        List<String> result = getActualMessages(form, "clientId");

        assertEquals(2, result.size());
        assertTrue(result.contains("数値を入力してください。"));
        assertTrue(result.contains("0以上の数値を入力してください。"));
    }

    private List<String> getActualMessages(ProjectUpdateForm form, String propertyName) {
        return new ConstraintViolationConverterFactory().create().convert(
                ValidatorUtil.getValidator().validateProperty(form, propertyName)
        ).stream().map(Message::formatMessage).collect(Collectors.toList());
    }
}
