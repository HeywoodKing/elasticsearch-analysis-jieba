package org.elasticsearch.index.analysis;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;


public class JiebaAnalyzerProvider extends AbstractIndexAnalyzerProvider<JiebaAnalyzer> {
  private final JiebaAnalyzer analyzer;

  @Inject
  public JiebaAnalyzerProvider(Index index, @IndexSettings Settings indexSettings, Environment env,
      @Assisted String name, @Assisted Settings settings) {
    super(index, indexSettings, name, settings);
    analyzer = new JiebaAnalyzer(settings);
  }

  public JiebaAnalyzerProvider(Index index, Settings indexSettings, String name, Settings settings) {
    super(index, indexSettings, name, settings);
    analyzer = new JiebaAnalyzer(settings);
  }

  public JiebaAnalyzerProvider(Index index, Settings indexSettings, String prefixSettings,
      String name, Settings settings) {
    super(index, indexSettings, prefixSettings, name, settings);
    analyzer = new JiebaAnalyzer(settings);
  }


  @Override
  public JiebaAnalyzer get() {
    return this.analyzer;
  }
}
