package com.dev4dan.model.mapper;

import com.dev4dan.model.articleModel.Article;

import java.util.List;

/**
 * Created by danielwood on 12/05/2017.
 */
public interface ArticleMapper {
    /**
    *
    * @param articles
    * @return
    *
    * */
    public int insertArticles(List<Article> articles);

    /**
     * 按照文章标题模糊查询
     * @param title
     * @return
     *
     * */
    public List<Article> getArticleByTitle(String title);

    /**
    * 按照id查询文章
    * @param id
     * @return
     *
    * */
    public Article getArticleById(int id);

    /**
     * 按照文章的准确标题查询
     * @param title
     * @return
     *
     * */
    public Article getArticleByExactTitle(String title);
}
