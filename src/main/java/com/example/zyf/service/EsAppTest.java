package com.example.zyf;

import com.alibaba.fastjson.JSON;
import com.example.zyf.model.Tests;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EsAppTest {

    @Autowired
    private RestHighLevelClient client;

    public static String INDEX_TEST = null;
    public static String TYPE_TEST = null;
    public static Tests tests = null;
    public static List<Tests> testsList = null;

    @BeforeClass
    public static void before() {
        INDEX_TEST = "index_test"; // ????????????
        TYPE_TEST = "type_test"; // ????????????
        testsList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tests = new Tests();
            tests.setId(Long.valueOf(i));
            tests.setName("this is the test " + i);
            testsList.add(tests);
        }
    }

    public void testIndex() throws IOException {
        createIndex(INDEX_TEST);
        // ????????????????????????
        if (!exists(INDEX_TEST, TYPE_TEST, tests)) {
            // ?????????????????????
            add(INDEX_TEST, TYPE_TEST, tests);
        }
        // ??????????????????
        get(INDEX_TEST, TYPE_TEST, tests.getId());

        // ??????????????????
        update(INDEX_TEST, TYPE_TEST, tests);
        get(INDEX_TEST, TYPE_TEST, tests.getId());

        // ??????????????????
        delete(INDEX_TEST, TYPE_TEST, tests.getId());
        get(INDEX_TEST, TYPE_TEST, tests.getId());

        // ????????????
        bulk();

    }
    /**
     * ????????????
     * @param index
     * @throws IOException
     */
    public void createIndex(String index) throws IOException {
        GetIndexRequest indexRequest = new GetIndexRequest();
        indexRequest.indices(index);
        boolean exists = client.indices().exists(indexRequest, RequestOptions.DEFAULT);
        if (!exists){
            CreateIndexRequest request = new CreateIndexRequest();
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            System.out.println("createIndex: " + JSON.toJSONString(createIndexResponse));
        }
    }
    /**
     * ????????????????????????
     * @param index
     * @param type
     * @param tests
     * @return
     * @throws IOException
     */
    public boolean exists(String index, String type, Tests tests) throws IOException {
        GetRequest getRequest = new GetRequest(index, type, tests.getId().toString());
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("exists: " + exists);
        return exists;
    }

    /**
     * ????????????
     * @param index
     * @param type
     * @param tests
     * @throws IOException
     */

    public void add(String index, String type, Tests tests) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, type, tests.getId().toString());
        indexRequest.source(JSON.toJSONString(tests), RequestOptions.DEFAULT);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("add: " + JSON.toJSONString(indexResponse));
    }

    /**
     * ??????????????????
     * @param index
     * @param type
     * @param id
     * @throws IOException
     */
    public void get(String index, String type, Long id) throws IOException {
        GetRequest getRequest = new GetRequest(index, type, id.toString());
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("get: " + JSON.toJSONString(getResponse));
    }

    /**
     * ??????????????????
     * @param index
     * @param type
     * @param tests
     * @throws IOException
     */
    public void update(String index, String type, Tests tests) throws IOException {
        tests.setName(tests.getName() + "updated");
        UpdateRequest request = new UpdateRequest(index, type, tests.getId().toString());
        request.doc(JSON.toJSONString(tests), XContentType.JSON);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        System.out.println("update: " + JSON.toJSONString(updateResponse));
    }

    /**
     * ????????????
     * @param index
     * @param type
     * @param id
     * @throws IOException
     */
    public void delete(String index, String type, Long id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, type, id.toString());
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println("delete: " + JSON.toJSONString(response));
    }

    /**
     * ??????
     * @param index
     * @param type
     * @param name
     * @throws IOException
     */
    public void search(String index, String type, String name) throws IOException {
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(QueryBuilders.matchQuery("name", name)); // ???????????????????????????????????????must?????????????????????????????????mustnot????????????????????????
        // boolBuilder.must(QueryBuilders.matchQuery("id", tests.getId().toString()));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(100); // ????????????????????????10
        sourceBuilder.fetchSource(new String[] { "id", "name" }, new String[] {}); // ???????????????????????????????????????????????????????????????????????????
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("search: " + JSON.toJSONString(response));
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            System.out.println("search -> " + hit.getSourceAsString());
        }
    }
    /**
     * ????????????
     * @throws IOException
     */
    public void bulk() throws IOException {
        // ????????????
        BulkRequest bulkAddRequest = new BulkRequest();
        for (int i = 0; i < testsList.size(); i++) {
            tests = testsList.get(i);
            IndexRequest indexRequest = new IndexRequest(INDEX_TEST, TYPE_TEST, tests.getId().toString());
            indexRequest.source(JSON.toJSONString(tests), XContentType.JSON);
            bulkAddRequest.add(indexRequest);
        }
        BulkResponse bulkAddResponse = client.bulk(bulkAddRequest, RequestOptions.DEFAULT);
        System.out.println("bulkAdd: " + JSON.toJSONString(bulkAddResponse));
        search(INDEX_TEST, TYPE_TEST, "this");

        // ????????????
        BulkRequest bulkUpdateRequest = new BulkRequest();
        for (int i = 0; i < testsList.size(); i++) {
            tests = testsList.get(i);
            tests.setName(tests.getName() + " updated");
            UpdateRequest updateRequest = new UpdateRequest(INDEX_TEST, TYPE_TEST, tests.getId().toString());
            updateRequest.doc(JSON.toJSONString(tests), XContentType.JSON);
            bulkUpdateRequest.add(updateRequest);
        }
        BulkResponse bulkUpdateResponse = client.bulk(bulkUpdateRequest, RequestOptions.DEFAULT);
        System.out.println("bulkUpdate: " + JSON.toJSONString(bulkUpdateResponse));
        search(INDEX_TEST, TYPE_TEST, "updated");

        // ????????????
        BulkRequest bulkDeleteRequest = new BulkRequest();
        for (int i = 0; i < testsList.size(); i++) {
            tests = testsList.get(i);
            DeleteRequest deleteRequest = new DeleteRequest(INDEX_TEST, TYPE_TEST, tests.getId().toString());
            bulkDeleteRequest.add(deleteRequest);
        }
        BulkResponse bulkDeleteResponse = client.bulk(bulkDeleteRequest, RequestOptions.DEFAULT);
        System.out.println("bulkDelete: " + JSON.toJSONString(bulkDeleteResponse));
        search(INDEX_TEST, TYPE_TEST, "this");
    }


}
