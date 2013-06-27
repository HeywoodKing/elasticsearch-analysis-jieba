package org.elasticsearch.index.analysis;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


public final class JiebaTokenFilter extends TokenFilter {
  
  JiebaSegmenter segmenter;
  
  private Iterator<String> tokenIter;
  private List<String> tokenBuffer;
    
  private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    
  public JiebaTokenFilter(String url, TokenStream input) {
    super(input);
    segmenter = new JiebaSegmenter(url);
  }
  
  public JiebaTokenFilter(String ip, Integer port, String type, TokenStream input) {
    super(input);
    segmenter = new JiebaSegmenter(ip, port, type);
  }  

  
  @Override
  public boolean incrementToken() throws IOException {
    if (tokenIter == null || !tokenIter.hasNext()) {
      if (input.incrementToken()) {

        tokenBuffer = segmenter.segmentSentence(new StringReader(termAtt.toString()));
        tokenIter = tokenBuffer.iterator();

        if (!tokenIter.hasNext())
          return false;
      } else {
        return false; // no more sentences, end of stream!
      }
    } 
    // WordTokenFilter must clear attributes, as it is creating new tokens.
    clearAttributes();

    String nextWord = tokenIter.next();
    termAtt.copyBuffer(nextWord.toCharArray(), 0, nextWord.length());
    return true;    
  }
  
  @Override
  public void reset() throws IOException {
    super.reset();
    tokenIter = null;
  }

}
