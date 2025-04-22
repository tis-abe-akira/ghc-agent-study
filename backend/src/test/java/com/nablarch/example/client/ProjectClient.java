package com.nablarch.example.client;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import nablarch.core.beans.BeanUtil;
import nablarch.core.beans.CopyOptions;

import com.nablarch.example.form.ProjectForm;
import com.nablarch.example.form.ProjectUpdateForm;

import com.nablarch.example.entity.Project;

public class ProjectClient {

    private static final String targetUrl = "http://localhost:9080/projects";

    /**
     * プロジェクト情報の文字列変換を行う。
     * @param args 引数
     */
    public static void main(String[] args) throws Exception {

        // 検索
        // 全件検索
        System.out.print(makeDataString(getProjects()));
        // 指定条件検索
        System.out.print(makeDataString(getProjects("clientId", 1)));

        // 登録
        ProjectForm project = createInsertProject();
        System.out.println("insert status:" + postProject(project));
        System.out.print(makeDataString(getProjects()));

        // 更新対象プロジェクト取得
        Map<String, String> updateTargetProject = getProjects("projectName", "プロジェクト９９９").get(0);
        final CopyOptions copyOptions = CopyOptions.options()
                                                   .datePatternByName("projectStartDate", "yyyy/MM/dd")
                                                   .datePatternByName("projectEndDate", "yyyy/MM/dd")
                                                   .build();

        Project updateProject = BeanUtil.createAndCopy(Project.class, updateTargetProject, copyOptions);
        ProjectUpdateForm updateForm = setUpdateProject(updateProject);

        // 更新
        System.out.println("update status:" + putProject(updateForm, updateProject.getProjectId()));
        System.out.print(makeDataString(getProjects()));
    }

    /**
     * 登録用プロジェクト情報生成
     * @return 登録用プロジェクト情報
     */
    private static ProjectForm createInsertProject() {
        ProjectForm form = new ProjectForm();
        form.setProjectName("プロジェクト９９９");
        form.setProjectType("development");
        form.setProjectClass("s");
        form.setProjectStartDate("20160101");
        form.setProjectEndDate("20160331");
        form.setClientId("10");
        form.setProjectManager("鈴木");
        form.setProjectLeader("佐藤");
        form.setNote("備考９９９");
        form.setSales("10000");
        form.setCostOfGoodsSold("1000");
        form.setSga("2000");
        form.setAllocationOfCorpExpenses("3000");
        return form;
    }

    /**
     * 更新用プロジェクト情報設定
     * @param project 更新対象プロジェクト情報
     * @return 更新用プロジェクト情報
     */
    private static ProjectUpdateForm setUpdateProject(Project project) {
        ProjectUpdateForm form = new ProjectUpdateForm();
        form.setProjectId(project.getProjectId().toString());
        form.setProjectName("プロジェクト８８８");
        form.setProjectType("development");
        form.setProjectClass("a");
        form.setProjectStartDate("20150101");
        form.setProjectEndDate("20150331");
        form.setClientId("1");
        form.setProjectManager("佐藤");
        form.setProjectLeader("鈴木");
        form.setNote("備考８８８");
        form.setSales("20000");
        form.setCostOfGoodsSold("2000");
        form.setSga("3000");
        form.setAllocationOfCorpExpenses("4000");
        return form;
    }

    /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @return プロジェクト情報リスト
     */
    private static List<Map<String, String>> getProjects() {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Map<String, String>>>() {});
    }

    /**
     * HTTP GETメソッドを使用したクライアント操作を行う。
     * @param key query paramのキー
     * @param value query paramの値
     * @return プロジェクト情報リスト
     */
    private static List<Map<String, String>> getProjects(String key, Object value) throws UnsupportedEncodingException {

        return ClientBuilder.newClient()
                            .target(targetUrl)
                            .queryParam(key, value)
                            .request(MediaType.APPLICATION_JSON)
                            .get(new GenericType<List<Map<String, String>>>() {});
    }

    /**
     * HTTP POSTメソッドを使用したクライアント操作を行う。
     * @param project 更新用プロジェクト情報
     * @return ステータスコード
     */
    private static Integer postProject(ProjectForm project) {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(project)).getStatus();
    }

    /**
     * HTTP PUTメソッドを使用したクライアント操作を行う。
     * @param project 更新用プロジェクト情報
     * @param projectId プロジェクトID
     * @return ステータスコード
     */
    private static Integer putProject(ProjectUpdateForm project, Integer projectId) {
        return ClientBuilder.newClient()
                .target(targetUrl)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(project)).getStatus();
    }

    /**
     * プロジェクト情報の文字列変換を行う。
     * @param projects プロジェクト情報List
     * @return プロジェクト情報
     */
    private static String makeDataString(List<Map<String, String>> projects) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("---- projects (size: %s) ----", projects.size())).append(System.lineSeparator());
        for (Map<String, String> project : projects) {
            sb.append("Project(").append(project).append(")").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
