package com.gcx.api;


import com.gcx.api.common.util.WebUtil;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DemoApplicationTests {

    @Test
    public void generateAsciiDocs() throws Exception {
        //输出Ascii格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8081/myProject/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("src/docs/asciidoc/generated/index"));
        //线程休眠
        Thread.sleep(5000);
        // TODO 调用maven asciidoctor:process-asciidoc插件
        Runtime runtime = Runtime.getRuntime();
        String rootPath = WebUtil.getRootPath();
        System.out.println("获取到的项目的路径是==========="+rootPath);
        Process exec = runtime.exec("cmd /k cd " + rootPath + "&& cd ..&& cd .. && mvn asciidoctor:process-asciidoc");
        //cmd执行需要一段时间
        Thread.sleep(10000);
        System.out.println("====================index.html生成成功===========");
    }


}