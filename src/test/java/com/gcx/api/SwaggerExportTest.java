//package com.gcx.api;
//
//import com.gcx.api.common.config.StreamTool;
//import io.github.robwin.markup.builder.MarkupLanguage;
//import io.github.robwin.swagger2markup.GroupBy;
//import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
//import io.swagger.io.HttpClient;
//import org.junit.Test;
//
//import java.io.InputStream;
//
//import static com.gcx.api.common.config.Constant.*;
//
///**
// * 将swagger-api文档导出成html
// *
// * @author Beauxie
// * @date Created on 2018/04/06
// */
//public class SwaggerExportTest {
//    /**
//     * 服务器地址,项目必须集成了Swagger而且确保v2/api-docs接口可以访问
//     */
//    private static String SERVICE_URL = "http://127.0.0.1:8081/myProject";
//
//    private static String SERVICE_URL = "http://119.90.51.183:9164/tipmnew";
//
//    static {
//        StreamTool.checkFile(OUTPUT_DIR);
//    }
//
//
//    @Test
//    public void test() throws Exception {
//        outputJson();
//        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
//        Swagger2MarkupConverter.from(FILE_PATH)
//                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
//                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
//                .withExamples(SNIPPET_DIR)
//                .build()
//                .intoFolder(OUTPUT_DIR);// 输出
//
//    }
//
//    public void outputJson() throws Exception {
//        String url = SERVICE_URL + URI;
//        HttpClient httpClient = new HttpClient(url);
//        System.out.println("start request============" + url);
//        InputStream is = httpClient.execute();
//        byte[] data = StreamTool.read(is);
//        StreamTool.saveDataToFile(data, FILE_PATH);
//        System.out.println("save path=========:" + FILE_PATH);
//
//    }
//
//}