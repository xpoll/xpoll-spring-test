package cn.blmdz.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrCloudTest {

	public static void main(String[] args) throws SolrServerException, IOException {
		HttpSolrClient client = new HttpSolrClient("http://127.0.0.1:8983/solr/jobs");
        client.setConnectionTimeout(30000);
        client.setDefaultMaxConnectionsPerHost(100);
        client.setMaxTotalConnections(100);
        client.setSoTimeout(30000);
        
		add(client);
		delete(client);
//		query(client);
	}
	
	public static void delete(SolrClient client) throws SolrServerException, IOException {
		List<String> ids = new ArrayList<>();
		for (int i = 1; i < 17; i++) {
			ids.add(String.valueOf(i));
		}
		client.deleteById(ids);
		client.commit();
	}

	public static void add(SolrClient client) throws SolrServerException, IOException {
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id", "2");
		document.setField("name", "中国");
		document.setField("age_i", 200);
		document.setField("time_dt", new Date());
		client.add(document);
		client.commit();
	}

	public static void query(SolrClient client) throws SolrServerException, IOException {
		SolrQuery params = new SolrQuery();
		// params.set("q", "name:*f*");
		params.setQuery("李");
		params.setStart(0);
		params.setRows(10);
		params.setSort(new SortClause("time_dt", ORDER.desc));
		params.setHighlight(true);
		params.addHighlightField("name");
		params.setHighlightSimplePre("<e>");
		params.setHighlightSimplePost("</e>");

		QueryResponse rsp = client.query(params);
		SolrDocumentList docs = rsp.getResults();
		
		
		for (SolrDocument doc : docs) {
			

			Map<String, Map<String, List<String>>> highlighting = rsp.getHighlighting();
			List<String> hList = highlighting.get(doc.get("id")).get("name");
			System.out.println(hList.get(0));
			
			
			for (String key : doc.getFieldNames()) {
				System.out.print(doc.getFieldValue(key).toString() + " ");
			}
			System.out.println();
		}
	}

}
