package com.gcx.api.common.generate;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
/**
 *<p>Title:FindAllList</p>
 *<p>Description:Generator自定义扩展</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年9月28日</p>
 */
public class FindAllList extends PluginAdapter {
    
	/**
	 * 生成Dao
	 */
	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseDao<" + introspectedTable.getBaseRecordType()  + ">");  
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("com.gcx.api.common.base.BaseDao");  
        interfaze.addSuperInterface(fqjt);// 添加 extends BaseDao<User>  
        interfaze.addImportedType(imp);// 添加import common.BaseDao;  
        interfaze.getMethods().clear();
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}
	
	/** 
     * 生成实体中每个属性 
     */  
    @Override  
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,  
            IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) { 
        return true;  
    }  
  
    /** 
     * 生成实体 
     */  
    @Override  
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {  
        addSerialVersionUID(topLevelClass, introspectedTable);  
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);  
    }  
  
    /** 
     * 生成mapping 
     */  
    @Override  
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {  
        return super.sqlMapGenerated(sqlMap, introspectedTable);  
    }
	
	/**
	 * 生成mapping 添加自定义sql
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {
//		    String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();// 数据库表名  
//	        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();  
	        XmlElement parentElement = document.getRootElement();  
	          
	        // 添加sql——where  
	        XmlElement sql = new XmlElement("sql");  
	        sql.addAttribute(new Attribute("id", "Base_Where_List"));  
	        XmlElement where = new XmlElement("where");  
	        StringBuilder sb = new StringBuilder();  
	        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {  
	            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$  
	            sb.setLength(0);  
	            sb.append(introspectedColumn.getJavaProperty());  
	            sb.append(" != null"); //$NON-NLS-1$  
	            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$  
	            where.addElement(isNotNullElement);  
	  
	            sb.setLength(0);  
	            sb.append(" and ");  
	            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));  
	            sb.append(" = "); //$NON-NLS-1$  
	            sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));  
	            isNotNullElement.addElement(new TextElement(sb.toString()));  
	        }  
	        sql.addElement(where);  
	        parentElement.addElement(sql);  
	          
	        //添加findAllList  
	        XmlElement select = new XmlElement("select");  
	        select.addAttribute(new Attribute("id", "findAllList"));  
	        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));  
	        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));  
	        select.addElement(new TextElement(" select "));
	        XmlElement include1 = new XmlElement("include");
	        include1.addAttribute(new Attribute("refid", "Base_Column_List"));
	        select.addElement(include1);
	        select.addElement(new TextElement("  from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));  
	          
	        XmlElement include = new XmlElement("include");  
	        include.addAttribute(new Attribute("refid", "Base_Where_List"));  
	        select.addElement(include);  
	        select.addElement(new TextElement("order by create_time desc <if test=\"pageSize!=-1\">  LIMIT #{firstResult,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER} </if>"));
	        parentElement.addElement(select);
	        
	        //添加findAllListCount 
	        XmlElement selectCount = new XmlElement("select");  
	        selectCount.addAttribute(new Attribute("id", "findAllListCount"));  
	        selectCount.addAttribute(new Attribute("resultType", "java.lang.Long"));  
	        selectCount.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));  
	        selectCount.addElement(new TextElement(" select count(1) from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));  
	          
	        XmlElement include2 = new XmlElement("include");  
	        include2.addAttribute(new Attribute("refid", "Base_Where_List"));  
	          
	        selectCount.addElement(include2);  
	        parentElement.addElement(selectCount);  
	        //批量删除
	        XmlElement deleteByIds = new XmlElement("delete");
	        deleteByIds.addAttribute(new Attribute("id", "delRecords"));  
	        deleteByIds.addAttribute(new Attribute("parameterType", "java.lang.String"));
	        deleteByIds.addElement(new TextElement(" delete  from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()+" where tid IN(${tids})"));  
	        parentElement.addElement(deleteByIds);
		    return super.sqlMapDocumentGenerated(document, introspectedTable);
	}
	
    @Override  
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,  
            IntrospectedTable introspectedTable) {  
        return false;  
    }  
  
    @Override  
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {  
        return false;  
    }  
    
    private void addSerialVersionUID(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        CommentGenerator commentGenerator = context.getCommentGenerator();  
        Field field = new Field();  
        field.setVisibility(JavaVisibility.PRIVATE);  
        field.setType(new FullyQualifiedJavaType("long"));  
        field.setStatic(true);  
        field.setFinal(true);  
        field.setName("serialVersionUID");  
        field.setInitializationString("1L");  
        commentGenerator.addFieldComment(field, introspectedTable);  
        topLevelClass.addField(field);  
    } 
	
	@Override
	public boolean validate(List<String> arg0) {
		return true;
	}

}
