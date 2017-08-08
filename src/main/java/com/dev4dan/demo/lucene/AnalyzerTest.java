package com.dev4dan.demo.lucene;

import com.dev4dan.comp4Framework.lucene.SubIKAnalyzer;
import com.dev4dan.demo.lucene.Operator.Impl.IndexOperatorImpl;
import com.dev4dan.demo.lucene.Operator.IndexOperator;
import com.dev4dan.demo.lucene.geekFiles.GeekDocument;
import com.dev4dan.utils.Constants;
import com.dev4dan.utils.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import testCase.FileTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试分词器
 * 分词器工作流程
 *     1.切分，将需要分词的内容进行切分成每个单词或者词语
 *     2.去除停用词，有些词在文本中出现的频率非常高，但是对文本所携带的信息基本不产生影响，例如英文的“a、an、the、of”，或中文的“的、了、着、是”，以及各种标点符号等，
 * 这样的词称为停用词（stop word）。文本经过分词之后，停用词通常被过滤掉，不会被进行索引。在检索的时候，用户的查询中如果含有停用词，
 * 检索系统也会将其过滤掉（因为用户输入的查询字符串也要进行分词处理）。排除停用词可以加快建立索引的速度，减小索引库文件的大小。
 *     3.对于英文字母，转为小写，因为搜索的时候不区分大小写
 * @author kencery
 *
 */
public class AnalyzerTest {
    public static final int MAX_MERGE_NUM = 10;

    public static String filePath = "/Users/danielwood/IdeaProjects/demo/target/classes/luceneTxt";

    public static void  main(String[] args){
        try {
//            standardAnalyzerTest();
//            indexWriterTest();
//            testIKAnalyzer();
            testGeekDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * StandardAnalyzer分词法测试,对中文支持不是很好,将中文分词成1个字(单字分词)
     * @throws Exception 
     */
    public static void standardAnalyzerTest() throws Exception{
        //英文测试
        String text="An IndexWriter creaters and maintains an index.";
        Analyzer analyzer=new StandardAnalyzer();
        displayTokens(analyzer,text);
        //中文测试
        String text1="Lucene是全文检索框架";
        displayTokens(analyzer,text1);    
    }

    public static void indexWriterTest() throws ParseException {
        try {
            // 0. Specify the analyzer for tokenizing text.
            //    The same analyzer should be used for indexing and searching
            StandardAnalyzer analyzer = new StandardAnalyzer();

            // 1. create the index
            Directory index = new RAMDirectory();

            IndexWriterConfig config = new IndexWriterConfig(analyzer);

            IndexWriter indexWriter = new IndexWriter(index, config);
            addDoc(indexWriter, "Lucene in Action", "193398817");
            addDoc(indexWriter, "Lucene for Dummies", "55320055Z");
            addDoc(indexWriter, "Managing Gigabytes", "55063554A");
            addDoc(indexWriter, "The Art of Computer Science", "9900333X");
            indexWriter.forceMerge(MAX_MERGE_NUM);
//            index.createOutput();
            indexWriter.close();

            // 2. query
            String querystr = "lucene";

            // the "title" arg specifies the default field to use
            // when no field is explicitly specified in the query.
            Query q = new QueryParser("title", analyzer).parse(querystr);

            // 3. search
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage);
            searcher.search(q, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;

            // 4. display results
            System.out.println("Found " + hits.length + " hits.");
            for(int i=0;i<hits.length;++i) {
                int docId = hits[i].doc;
                Document d = searcher.doc(docId);
                System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
            }

            // reader can only be closed when there
            // is no need to access the documents any more.
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void addDoc(IndexWriter indexWriter, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));

        // use a string field for isbn because we don't want it tokenized
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        indexWriter.addDocument(doc);
    }

    /**
      * CJKAnalyzerTest分词法测试,对中文支持不是很好，将中文分词成2个字(二分法分词)
      * 
      * @throws Exception
      */
   /* public static void testCJKAnalyzer() throws Exception{
        //英文测试
        String text="An IndexWriter creaters and maintains an index.";
        Version matchVersion = Version.LUCENE_6_4_2;
        CharArraySet charArraySet = new CharArraySet(matchVersion, 1000, true);
        Analyzer analyzer=new CJKAnalyzer(matchVersion, charArraySet);
        displayTokens(analyzer,text);
//        中文测试
        String text1="Lucene是全文检索框架";
        displayTokens(analyzer,text1);
    }*/

     /**
      * IKAnalyzerTest分词法测试,对中文支持很好，词库分词
      * @throws Exception
      */
    public static void testIKAnalyzer() throws Exception{
        Analyzer analyzer=new SubIKAnalyzer();

        IndexOperator operator = IndexOperatorImpl.getOperator();

        List<Document> documents = new ArrayList<>();

        File dir = new File(filePath);

        File[] files = dir.listFiles();

        for(int i=0 ; i < files.length ; i++){
            File file = files[i];
            System.out.println("file name ---- "+file.getName());
            try {
                List<String> lines = FileUtils.readFileLine(new FileInputStream(file));
                Document document = new Document();

                document.add(new StringField("title", file.getName(), Field.Store.YES));

                for(String line : lines){
                    if(line.equals("") || line.equals("\n")){
                        continue;
                    }
                    displayTokens(analyzer, line, document);

                }
                documents.add(document);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        operator.getWriter().createIndex(documents);

        analyzer.close();
    }

    public static void testGeekDocument(){
        Analyzer analyzer=new SubIKAnalyzer();

        IndexOperator operator = IndexOperatorImpl.getOperator();

        List<Document> documents = new ArrayList<>();

        File dir = new File(filePath);

        File[] files = dir.listFiles();

        for(int i=0 ; i < files.length ; i++){
            File file = files[i];
            try {
                List<String> lines = FileUtils.readFileLine(new FileInputStream(file));
                GeekDocument geekDocument = new GeekDocument();

                geekDocument.getDocument().add(new StringField("title", file.getName(), Field.Store.YES));

                for(String line : lines){
                    if(line.equals("") || line.equals("\n")){
                        continue;
                    }
                    geekDocument.getDocument().add(new StringField("content", line.trim(), Field.Store.YES));

                }
                documents.add(geekDocument.getDocument());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        operator.getWriter().createIndex(documents);

        analyzer.close();
    }

    public static TokenStream getTokenStream(Analyzer analyzer, String text) throws IOException {
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
        tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.addAttribute(PositionIncrementAttribute.class);
        tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.addAttribute(TypeAttribute.class);
        tokenStream.reset();
        return tokenStream;
    }

    public static void displayTokens(Analyzer analyzer, String text) throws Exception {
        displayTokens(analyzer, text, new Document());
    }

    /**
     * 使用指定的分词器对指定的文本进行分词，并打印出分出的词,测试分词法的方法
     * 备注说明：这里注意版本问题，暂无方法解决
     * @param analyzer
     * @param text
     * @param document
     * @throws Exception
     */
    public static void displayTokens(Analyzer analyzer, String text, Document document) throws Exception {
        System.out.println("当前使用的分词器：" + analyzer.getClass().getName());
        //分词流，即将对象分词后所得的Token在内存中以流的方式存在，
        // 也说是说如果在取得Token必须从TokenStream中获取，而分词对象可以是文档文本，也可以是查询文本。
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
        //表示token的首字母和尾字母在原文本中的位置。比如I'm的位置信息就是(0,3)，
        // 需要注意的是startOffset与endOffset的差值并不一定就是termText.length()，
        //因为可能term已经用stemmer或者其他过滤器处理过；
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        //这个有点特殊，它表示tokenStream中的当前token与前一个token在实际的原文本中相隔的词语数量，
        //用于短语查询。比如： 在tokenStream中[2:a]的前一个token是[1:I'm ]，
        //它们在原文本中相隔的词语数是1，则token="a"的PositionIncrementAttribute值为1；
        PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);

        CharTermAttribute charTermAttribute= tokenStream.addAttribute(CharTermAttribute.class);

        //表示token词典类别信息，默认为“Word”，比如I'm就属于<APOSTROPHE>，有撇号的类型；
        TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);
        tokenStream.reset();

        int position = 0;
        while (tokenStream.incrementToken()) {
          int increment = positionIncrementAttribute.getPositionIncrement();
          if(increment > 0) {
            position = position + increment;
          }
          int startOffset = offsetAttribute.startOffset();
          int endOffset = offsetAttribute.endOffset();
          String term = charTermAttribute.toString();
          document.add(new StringField("word",term, Field.Store.YES));

          System.out.println("第"+position+"个分词，分词内容是:[" + term + "]，"
                  + "分词内容的开始结束位置为：(" + startOffset + "-->" + endOffset + ")，类型是：" + typeAttribute.type());
        }
        tokenStream.close();
    }
}