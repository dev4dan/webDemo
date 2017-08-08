package com.dev4dan.demo.lucene.Operator.Impl;

import com.dev4dan.demo.lucene.Operator.GeekIndexReader;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by danielwood on 01/05/2017.
 */
public class GeekIndexReaderImpl implements GeekIndexReader {
    private String indexPath = "";
    private static final Logger log=Logger.getLogger(GeekIndexReaderImpl.class);
    private MultiReader reader = null;

    public GeekIndexReaderImpl (String indexPath){
        this.indexPath = indexPath;
    }

    public List<Document> getDocuments(){
        return getDocuments(null);
    }

    public Document getDocument(int docId){
        return getDocument(null, docId);
    }

    public List<Document> getDocuments(String curntPath) {
        IndexReader reader = getReader(curntPath);
        int size = reader.maxDoc();
        if (size > 0) {
            List<Document> documents = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                try {
                    documents.add(reader.document(i));
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return documents;
        }
        return null;
    }

    public Document getDocument(String curntPath, int docId) {
        IndexReader reader = getReader(curntPath);
        int size = reader.maxDoc();
        if (docId >= 0 && docId < size) {
            try {
                return reader.document(docId);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public IndexReader getReader() {
        return getReader(null);
    }

    public IndexReader getReader(String curntPath){
        if(reader == null){
            if(curntPath == null){
                curntPath = indexPath;
            }
            try {
                Directory directory = FSDirectory.open(Paths.get(curntPath));
                reader = new MultiReader(StandardDirectoryReader.open(directory));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return reader;
    }
}
