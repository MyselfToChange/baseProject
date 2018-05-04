package com.gcx.api;

import com.alibaba.fastjson.JSON;
import com.gcx.api.model.UserGroup;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import springfox.documentation.staticdocs.SwaggerResultHandler;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringRunner.class)
@SpringBootTest
public class Documentation { 
	private String snippetDir = "target/generated-snippets"; 
	private String outputDir = "target/asciidoc"; 
	//private String indexDoc = "docs/asciidoc/index.adoc";
	
	 @Autowired 
	 private MockMvc mockMvc; 
	 @After 
	 public void Test() throws Exception{ 
		 // 得到swagger.json,写入outputDir目录中
		 mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON)) 
		 .andDo(SwaggerResultHandler.outputDirectory(outputDir).build()) 
		 .andExpect(status().isOk()) 
		 .andReturn(); 
		 // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir 
		 // 这个outputDir必须和插件里面<generated></generated>标签配置一致 
		 Swagger2MarkupConverter.from(outputDir + "/swagger.json") 
		 .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序 
		 .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式 
		 .withExamples(snippetDir) 
		 .build() 
		 .intoFolder(outputDir);// 输出
		 }

@Test 
public void TestApi() throws Exception{ 
	mockMvc.perform(get("/userGroups").param("groupName", "你好")) 
			.andExpect(status().isOk()) 
			.andDo(MockMvcRestDocumentation.document("列表", preprocessResponse(prettyPrint()))); 
	          UserGroup ug = new UserGroup(); 
	          ug.setGroupId("123");
	          ug.setGroupName("你好");
	          ug.setFirstResult(1);
	           mockMvc.perform(post("/userGroups").contentType(MediaType.APPLICATION_JSON) 
	        		   .content(JSON.toJSONString(ug)) 
	        		   .accept(MediaType.APPLICATION_JSON)) 
	        		   .andExpect(status().is2xxSuccessful()) 
	        		   .andDo(MockMvcRestDocumentation.document("新增", preprocessResponse(prettyPrint()))); 
	           }


}




