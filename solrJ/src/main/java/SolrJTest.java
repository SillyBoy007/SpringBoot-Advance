import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {

    @Test
    public void solrJ(){
        //和solr服务器创建连接
        //参数：solr服务器的地址
        try {
            HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
            //创建一个文档对象
            SolrInputDocument document = new SolrInputDocument();
            //向文档中添加域
            //第一个参数：域的名称，域的名称必须是在schema.xml中定义的
            //第二个参数：域的值
            document.addField("id", "c0001");
            document.addField("title_ik", "使用solrJ添加的文档");
            document.addField("content_ik", "文档的内容");
          //  document.addField("product_name", "商品名称");
            //把document对象添加到索引库中
            solrServer.add(document);
            //提交修改
            solrServer.commit();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //删除文档，根据id删除
    @Test
    public void deleteDocumentByid() throws Exception {
        //创建连接
        SolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
        //根据id删除文档
        solrServer.deleteById("c0001");
        //提交修改
        solrServer.commit();
    }
}
