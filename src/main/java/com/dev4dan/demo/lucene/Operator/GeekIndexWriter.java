package com.dev4dan.demo.lucene.Operator;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import java.util.List;

/**
 * Created by danielwood on 01/05/2017.
 */
public interface GeekIndexWriter {

    public IndexWriter getIndexWriter();
    public boolean createIndex(Document doc);
    public boolean createIndex(List<Document> docs);
    public boolean updateIndex(String field, String value, Document doc);
    public boolean deleteIndex(String field, String value);
    public boolean deleteAllIndex();
}
