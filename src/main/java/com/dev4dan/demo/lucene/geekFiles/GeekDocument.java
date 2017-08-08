package com.dev4dan.demo.lucene.geekFiles;

import org.apache.lucene.document.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danielwood on 15/05/2017.
 */
public class GeekDocument {
    private Document document;
    private Map<String, List<String>> terms4Document;

    public Map<String, List<String>> getTerms4Document() {
        if(terms4Document == null){
            terms4Document = new HashMap<>();
        }
        return terms4Document;
    }

    public void setTerms4Document(Map<String, List<String>> terms4Document) {
        this.terms4Document = terms4Document;
    }

    public Document getDocument() {
        if(document == null){
            document = new Document();
        }
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean addTerm(String fieldName, String term){
        if(getTerms4Document().get(fieldName) == null){
            getTerms4Document().put(fieldName, new ArrayList<String>());
        }
        return getTerms4Document().get(fieldName).add(term);
    }

    public boolean addFieldName(String fieldName){
        if(getTerms4Document().get(fieldName) == null){
            if(getTerms4Document().put(fieldName, new ArrayList<String>()) != null){
                return true;
            }
        }
        return false;
    }
}
