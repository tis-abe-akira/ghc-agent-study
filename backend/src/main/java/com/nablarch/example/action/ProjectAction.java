package com.nablarch.example.action;

import com.nablarch.example.dto.ProjectResponseDto;
import com.nablarch.example.dto.ProjectSearchDto;
import com.nablarch.example.entity.Project;
import com.nablarch.example.form.ProjectForm;
import com.nablarch.example.form.ProjectRenameForm;
import com.nablarch.example.form.ProjectSearchForm;
import com.nablarch.example.form.ProjectUpdateForm;
import nablarch.common.dao.EntityList;
import nablarch.common.dao.UniversalDao;
import nablarch.core.beans.BeanUtil;
import nablarch.core.validation.ee.ValidatorUtil;
import nablarch.fw.jaxrs.JaxRsHttpRequest;
import nablarch.fw.web.HttpResponse;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * プロジェクト検索・登録・更新機能。
 *
 * @author Nabu Rakutaro
 */
@Path("/projects")
public class ProjectAction {

    /**
     * プロジェクト情報を検索する。
     *
     * @param req HTTPリクエスト
     * @return プロジェクト情報リスト
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectResponseDto> find(JaxRsHttpRequest req) {

        // リクエストパラメータをBeanに変換
        ProjectSearchForm form = BeanUtil.createAndCopy(ProjectSearchForm.class, req.getParamMap());

        // BeanValidation実行
        ValidatorUtil.validate(form);

        ProjectSearchDto searchCondition = BeanUtil.createAndCopy(ProjectSearchDto.class, form);
        EntityList<Project> projectList = UniversalDao.findAllBySqlFile(Project.class, "FIND_PROJECT", searchCondition);

        return projectList.stream()
                .map(project -> BeanUtil.createAndCopy(ProjectResponseDto.class, project))
                .collect(Collectors.toList());
    }

    /**
     * プロジェクト情報を登録する。
     *
     * @param project プロジェクト情報
     * @return HTTPレスポンス
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Valid
    public HttpResponse save(ProjectForm project) {
        UniversalDao.insert(BeanUtil.createAndCopy(Project.class, project));
        return new HttpResponse(HttpResponse.Status.CREATED.getStatusCode());
    }

    /**
     * プロジェクト情報を更新する。
     *
     * @param form プロジェクト情報
     * @return HTTPレスポンス
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Valid
    public HttpResponse update(ProjectUpdateForm form) {
        Project project = BeanUtil.createAndCopy(Project.class, form);

        UniversalDao.update(project);

        return new HttpResponse(HttpResponse.Status.OK.getStatusCode());
    }

    /**
     * プロジェクト名を更新する。
     * 
     * @param form プロジェクト情報(プロジェクト名)
     * @return HTTPレスポンス
     */
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Valid
    public HttpResponse patch(ProjectRenameForm form) {
        Project project = UniversalDao.findById(Project.class, form.getProjectId());
        project.setProjectName(form.getProjectName());

        UniversalDao.update(project);

        return new HttpResponse(HttpResponse.Status.NO_CONTENT.getStatusCode());
    }

    /**
     * プロジェクトIDを指定して単一のプロジェクト情報を取得する。
     *
     * @param req HTTPリクエスト
     * @return プロジェクト情報
     * @see https://nablarch.github.io/docs/LATEST/doc/application_framework/application_framework/web_service/rest/feature_details/resource_signature.html
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    // public ProjectResponseDto findById(@PathParam("id") Integer id) {
    public ProjectResponseDto findById(JaxRsHttpRequest req) {
        Integer projectId = Integer.valueOf(req.getPathParam("id"));
        System.out.println("projectId = " + projectId);
        // プロジェクト情報を取得
        Project project = UniversalDao.findById(Project.class, projectId);
        if (project == null) {
            // 404 Not Foundを返す（Nablarch標準の例外で対応）
            throw new nablarch.fw.web.HttpErrorResponse(HttpResponse.Status.NOT_FOUND.getStatusCode());
        }
        return BeanUtil.createAndCopy(ProjectResponseDto.class, project);
    }

}
