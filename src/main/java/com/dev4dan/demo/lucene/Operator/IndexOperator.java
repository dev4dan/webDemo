package com.dev4dan.demo.lucene.Operator;

/**
 * Created by danielwood on 01/05/2017.
 */
public interface IndexOperator {
    public GeekIndexReader getReader();
    public GeekIndexWriter getWriter();
}
