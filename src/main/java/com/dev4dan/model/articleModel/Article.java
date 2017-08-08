package com.dev4dan.model.articleModel;

import java.util.Date;

/**
 * Created by danielwood on 12/05/2017.
 */
public class Article {
    private long id; // 记录id
    private int articleID; // 文章id
    private String title; // 文章标题
    private String content; // 文章内容
    private String titleFontColor; // 标题字体颜色
    private String author; // 作者
    private int copyFrom; // 文章来源
    private int classID; // 文章类型id
    private String keyWord; // 关键词
    private String artDescription; // 文章描述
    private int contentID; // 文章具体内容
    private Date presentDate; // 文章发布日期
    private int hits; // 点击数量
    private int yn; //
    private int isTop; // 是否头条(前10，100)
    private int isHot; // 是否为热门
    private int isFlash; // 是否为动画
    private String imageUrl; // 图片链接
    private int pageNum; // 页数
    private long userId; // 用户
    private String linkUrl; // 文章链接
    private String vote; // 推荐文章
    private int isImg; // 是否有图片

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitleFontColor() {
        return titleFontColor;
    }

    public void setTitleFontColor(String titleFontColor) {
        this.titleFontColor = titleFontColor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopyFrom() {
        return copyFrom;
    }

    public void setCopyFrom(int copyFrom) {
        this.copyFrom = copyFrom;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getArtDescription() {
        return artDescription;
    }

    public void setArtDescription(String artDescription) {
        this.artDescription = artDescription;
    }

    public int getContentID() {
        return contentID;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getIsFlash() {
        return isFlash;
    }

    public void setIsFlash(int isFlash) {
        this.isFlash = isFlash;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public int getIsImg() {
        return isImg;
    }

    public void setIsImg(int isImg) {
        this.isImg = isImg;
    }
}
