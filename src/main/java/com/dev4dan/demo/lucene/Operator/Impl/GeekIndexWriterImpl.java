package com.dev4dan.demo.lucene.Operator.Impl;

import com.dev4dan.demo.lucene.Operator.GeekIndexWriter;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielwood on 01/05/2017.
 */
public class GeekIndexWriterImpl implements GeekIndexWriter{
    private static String indexPath = "";
    private static final Logger log=Logger.getLogger(GeekIndexWriterImpl.class);
    private IndexWriter indexWriter = null;

    public GeekIndexWriterImpl(String indexPath){
        this.indexPath = indexPath;
    }

    @Override
    public IndexWriter getIndexWriter(){
            try {
                if(indexWriter == null){

                    File indexFile=new File(indexPath);
                    if(!indexFile.exists()){
                        indexFile.mkdir();
                    }

                    Directory fsDirectory = FSDirectory.open(Paths.get(indexPath));
                    IndexWriterConfig confIndex = new IndexWriterConfig(new IKAnalyzer());
                    confIndex.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
                    indexWriter = new IndexWriter(fsDirectory, confIndex);
                }
                return indexWriter;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    @Override
    /**
     * 创建索引
     *
     * @param doc
     * @throws Exception
     */
    public boolean createIndex(Document doc) {
        List<Document> docs = new ArrayList<>();
        docs.add(doc);
        return createIndex(docs);
    }

    @Override
    /**
     * 创建索引
     *
     * @param docs
     * @throws Exception
     */
    public boolean createIndex(List<Document> docs) {
        try {
            for (Document doc : docs) {
                getIndexWriter().addDocument(doc);
            }
            // 优化操作
            getIndexWriter().commit();
            getIndexWriter().forceMerge(1); // forceMerge代替optimize
            log.info("lucene create success.");
            return true;
        } catch (Exception e) {
            log.error("lucene create failure.", e);
            return false;
        } finally {
            if (getIndexWriter() != null) {
                try {
                    getIndexWriter().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    /**
     * 更新索引
     *
     * 例如：Term term = new Term("id","1234567");
     * 先去索引文件里查找id为1234567的Document，如果有就更新它(如果有多条，最后更新后只有一条)，如果没有就新增。
     * 数据库更新的时候，我们可以只针对某个列来更新，而lucene只能针对一行数据更新。
     *
     * @param field Document的Field(类似数据库的字段)
     * @param value Field中的一个关键词
     * @param doc
     * @return
     */
    public boolean updateIndex(String field, String value, Document doc) {
        try {
            getIndexWriter().updateDocument(new Term(field, value), doc);

            log.info("lucene update success.");
            return true;
        } catch (Exception e) {
            log.error("lucene update failure.", e);
            return false;
        }finally{
            if(getIndexWriter()!=null){
                try {
                    getIndexWriter().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除索引
     *
     * @param field Document的Field(类似数据库的字段)
     * @param value Field中的一个关键词
     * @return
     */
    public boolean deleteIndex(String field, String value) {
        try {
            getIndexWriter().deleteDocuments(new Term(field, value));

            log.info("lucene delete success.");
            return true;
        } catch (Exception e) {
            log.error("lucene delete failure.", e);
            return false;
        }finally{
            if(getIndexWriter()!=null){
                try {
                    getIndexWriter().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除整个索引库
     *
     * @return
     */
    public boolean deleteAllIndex() {
        try {
            getIndexWriter().deleteAll();
            log.info("lucene delete all success.");
            return true;
        } catch (Exception e) {
            log.error("lucene delete all failure.", e);
            return false;
        }finally{
            if(getIndexWriter()!=null){
                try {
                    getIndexWriter().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
