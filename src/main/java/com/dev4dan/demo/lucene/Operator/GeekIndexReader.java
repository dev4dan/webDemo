package com.dev4dan.demo.lucene.Operator;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;

import java.util.List;

/**
 * Created by danielwood on 01/05/2017.
 */
public interface GeekIndexReader {
    public List<Document> getDocuments();
    public List<Document> getDocuments(String curntPath);
    public Document getDocument(int docId);
    public Document getDocument(String curntPath, int docId);
    public IndexReader getReader();
    public IndexReader getReader(String curntPath);

}
