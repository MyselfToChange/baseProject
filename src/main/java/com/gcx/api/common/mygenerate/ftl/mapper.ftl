<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.gcx.${projectName}.dao.${table.javaName}Mapper">
	<resultMap id="BaseResultMap" type="com.gcx.${projectName}.model.${table.javaName}">
		<#list table.columns as column>
		<#if column_index == 0>  
		<id column="${column.columnName}" property="${column.javaName}" jdbcType="${column.columnType}" />
		<#else>
		<result column="${column.columnName}" property="${column.javaName}" jdbcType="${column.columnType}" />
		</#if>
		</#list> 
	</resultMap>

	<sql id="Base_Column_List">
		<#list table.columns as column>
		${column.columnName}<#if column_has_next>, </#if>
		</#list> 
	</sql>

	<sql id="Common_Where_List">
		<#list table.columns as column>
		<if test='record.${column.javaName} != null and record.${column.javaName} != "" '> 
		and ${column.columnName} = ${r"#{"}record.${column.javaName},jdbcType=${column.columnType}} </if>
		</#list>
	</sql>

	<insert id="insert" parameterType="com.gcx.${projectName}.model.${table.javaName}">
		insert into ${table.tableName} (
		<include refid="Base_Column_List" />
		)
		values (
		<#list table.columns as column>
		${r"#{"}${column.javaName},jdbcType=${column.columnType}}<#if column_has_next>, </#if>
		</#list> 
		)
	</insert>

	<insert id="insertSelective" parameterType="com.gcx.${projectName}.model.${table.javaName}">
		insert into ${table.tableName}
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list table.columns as column>
			<if test="${column.javaName} != null">
			${column.columnName},
			</if>			
			</#list> 
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
			<#list table.columns as column>
			<if test="${column.javaName} != null">
			${r"#{"}${column.javaName},jdbcType=${column.columnType}},
			</if>			
			</#list>
		</trim>
	</insert>

	<update id="updateByPrimaryKey" parameterType="com.gcx.${projectName}.model.${table.javaName}">
		update ${table.tableName}
		set 
		<#list table.columns as column>
		<#if column_index != 0> 
		${column.columnName} = ${r"#{"}${column.javaName},jdbcType=${column.columnType}}<#if column_has_next>, </#if>
		</#if>			
		</#list>
		where ${table.column.columnName} = ${r"#{"}${table.column.javaName},jdbcType=${table.column.columnType}}
	</update>

	<update id="updateByPrimaryKeySelective" parameterType="com.gcx.${projectName}.model.${table.javaName}">
		update ${table.tableName}
		<set>
			<#list table.columns as column>
			<#if column_index != 0> 
			<if test="${column.javaName} != null">
				${column.columnName} = ${r"#{"}${column.javaName},jdbcType=${column.columnType}},
			</if>
			</#if>			
			</#list>
		</set>
		where ${table.column.columnName} = ${r"#{"}${table.column.javaName},jdbcType=${table.column.columnType}}
	</update>

	<delete id="deleteByPrimaryKey" parameterType="${table.column.javaType}">
		delete from
		${table.tableName}
		where ${table.column.columnName} = ${r"#{"}id,jdbcType=${table.column.columnType}}
	</delete>

	<update id="deleteLogicById">
		update ${table.tableName}
		set DELETE_FLAG =
		${r"#{"}deleteFlag,jdbcType=INTEGER}
		where ${table.column.columnName} = ${r"#{"}id, jdbcType=${table.column.columnType}}
	</update>

	<update id="deleteLogicByIds">
		update ${table.tableName} set DELETE_FLAG = ${r"#{"}deleteFlag,jdbcType=INTEGER}
		where ${table.column.columnName} in
		<foreach item="item" index="index" collection="ids" open="("
			separator="," close=")">${r"#{"}item, jdbcType=${table.column.columnType}}</foreach>
	</update>

    <!--批量提交-->
    <update id="batchUpdateStatus">
        update ${table.tableName}
        set STATE = ${r"#{status,jdbcType=INTEGER}"}
        WhERE
        ID IN
        ${r'
        <foreach collection="array" index="index" item="item" open="(" separator="," close=")">
            #{item}
        </foreach>
        '}
    </update>

    <!--批量删除-->
    <update id="batchDelectRecord">
        update ${table.tableName}
        set DELETE_FLAG ='1'
        WhERE
        ID IN
        ${r'
        <foreach collection="array" index="index" item="item" open="(" separator="," close=")">
            #{item}
        </foreach>
        '}
    </update>

	<select id="selectByPrimaryKey" resultMap="BaseResultMap"
		parameterType="${table.column.javaType}">
		select
		<include refid="Base_Column_List" />
		from ${table.tableName}
		where  DELETE_FLAG != 1 AND ${table.column.columnName} = ${r"#{"}id,jdbcType=${table.column.columnType}} 
		LIMIT 1
	</select>

	<select id="findByRecord" resultMap="BaseResultMap">
		select
		<include refid='Base_Column_List' />
		from ${table.tableName} where DELETE_FLAG != 1
		<include refid='Common_Where_List' />
		ORDER BY TIME_ADD DESC 
		<if test="end != -1">
			limit ${r"#{"}start}, ${r"#{"}end}
		</if>
	</select>
	
	<select id="findByRecordCount" resultType="int">
		select count(1) from ${table.tableName} where DELETE_FLAG != 1
		<include refid='Common_Where_List' />
	</select>


</mapper>