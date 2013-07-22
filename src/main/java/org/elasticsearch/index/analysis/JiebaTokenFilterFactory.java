package org.elasticsearch.index.analysis;


import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;

public class JiebaTokenFilterFactory extends AbstractTokenFilterFactory {
	private final ESLogger log = Loggers.getLogger(JiebaTokenFilterFactory.class);
	private String ip;
	private Integer port;
	private String type;
	private String key;


	@Inject
	public JiebaTokenFilterFactory(Index index, Settings indexSettings,
			String name, Settings settings) {
		super(index, indexSettings, name, settings);
		ip = settings.get("ip", "183.136.223.174");
		port = settings.getAsInt("port", 8000);
		type = settings.get("t", "index");
		key = settings.get("key", "H2jo8vfZk7");
		log.info("ip:{} port:{} type:{}", ip, port, type);
	}

	@Override
	public TokenStream create(TokenStream input) {
		return new JiebaTokenFilter(this.ip, this.port, this.type, this.key, input);
	}

}
