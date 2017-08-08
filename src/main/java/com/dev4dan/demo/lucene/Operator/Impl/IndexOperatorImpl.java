package com.dev4dan.demo.lucene.Operator.Impl;

import com.dev4dan.demo.lucene.Operator.GeekIndexReader;
import com.dev4dan.demo.lucene.Operator.GeekIndexWriter;
import com.dev4dan.demo.lucene.Operator.IndexOperator;
import com.dev4dan.utils.Constants;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by danielwood on 01/05/2017.
 */
public class IndexOperatorImpl implements IndexOperator{
    /** 索引库路径 */
    private String indexPath = "/luceneTxt";
    public static final ThreadLocal<IndexOperator> indexOperator = new ThreadLocal<>();
    private static final Logger log=Logger.getLogger(IndexOperatorImpl.class);
    private GeekIndexReader reader = null;
    private GeekIndexWriter writer = null;

    private IndexOperatorImpl(){
        indexPath = Constants.getDefaultProjectPath()+ indexPath;
    }

    private IndexOperatorImpl(String contentPath){
        indexPath = Constants.getDefaultProjectPath()+ indexPath + contentPath;
    }

    public static IndexOperator getOperator(){
        return getOperator(null);
    }

    public static IndexOperator getOperator(String contentPath){
        if(contentPath == null){
            if(indexOperator.get() == null){
                indexOperator.set(new IndexOperatorImpl());
            }else{
                return indexOperator.get();
            }
        }else{
            if(indexOperator.get() == null){
                indexOperator.set(new IndexOperatorImpl(contentPath));
            }else{
                return indexOperator.get();
            }
        }
        return indexOperator.get();
    }

    /**
     * 判断索引库是否已创建
     *
     * @return true:存在，false：不存在
     * @throws Exception
     */
    public boolean existsIndex() throws Exception {
        File file = new File(indexPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String indexSufix = "/segments.gen";
        // 根据索引文件segments.gen是否存在判断是否是第一次创建索引
        File indexFile = new File(indexPath + indexSufix);
        return indexFile.exists();
    }

    @Override
    public GeekIndexReader getReader() {
        if(this.reader == null){
            this.reader = new GeekIndexReaderImpl(indexPath);
        }
        return reader;
    }

    @Override
    public GeekIndexWriter getWriter() {
        if(this.writer == null){
            this.writer = new GeekIndexWriterImpl(indexPath);
        }
        return writer;
    }
}
