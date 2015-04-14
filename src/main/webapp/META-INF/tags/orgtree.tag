<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelId" type="java.lang.String" required="true" description="显示区域ID"%>
<%@ attribute name="labelName" type="java.lang.String" required="false" description="显示区域名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="false" description="显示区域值（Name）"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="显示区域值（Name）"%>
<div class="input-append">
 	<input type="hidden" class="length_1_5 ${cssClass }" value="${value}" id="${id}" name="${name}">
 	<input type="text" readonly="readonly" value="${labelValue }" class="length_1_5 ${cssClass }" id="${labelId}" name="${labelName}">
 	<a href="javascript:tree.org.show('${ctx}','${id}','${labelId}');" class="btn btn-primary btn-table">选择</a>
</div>
