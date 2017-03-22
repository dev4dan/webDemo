package com.dev4dan.demo.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.util.AttributeFactory;
import org.wltea.analyzer.lucene.IKTokenizer;

import java.io.Reader;

/**
 * Created by danielwood on 21/03/2017.
 */
public class SubIKAnalyzer extends Analyzer{
    private boolean useSmart;

    public boolean useSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    /**
     * IK分词器Lucene  Analyzer接口实现类
     *
     * 默认细粒度切分算法
     */
    public SubIKAnalyzer() {
        this(false);
    }

    @Override
    protected TokenStreamComponents createComponents(String factoryName) {
        Tokenizer tokenizer = new SubIKTokenizer(AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY, useSmart());
        return new TokenStreamComponents(tokenizer);
    }

    /**
     * IK分词器Lucene Analyzer接口实现类
     *
     * @param useSmart 当为true时，分词器进行智能切分
     */
    public SubIKAnalyzer(boolean useSmart) {
        super();
        this.useSmart = useSmart;
    }

    /**
     * 重载Analyzer接口，构造分词组件
     */
    public TokenStreamComponents createComponents(String fieldName, final Reader in) {
        Tokenizer tokenizer = new SubIKTokenizer(AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY, this.useSmart());
        tokenizer.setReader(in);
        return new TokenStreamComponents(tokenizer);
    }
}
