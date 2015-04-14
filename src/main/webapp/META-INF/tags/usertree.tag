<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelId" type="java.lang.String" required="true" description="显示区域ID"%>
<%@ attribute name="labelName" type="java.lang.String" required="false" description="显示区域名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="false" description="显示区域值（Name）"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description=""%>
<%@ attribute name="checkType" type="java.lang.String" required="true" description="选择类型: radio/checkbox"%>
<%@ attribute name="orgId" type="java.lang.String" required="false" description="选择类型: radio/checkbox"%>
<%@ attribute name="checkFieldId" type="java.lang.String" required="false" description="关联必填验证属性"%>
<%@ attribute name="checkFieldProm" type="java.lang.String" required="false" description="关联必填验证属性提示"%>
<div class="input-append">
 	<input type="hidden" class="length_1_5 ${cssClass }" value="${value}"  id="${id}" name="${name}">
 	<input type="text" readonly="readonly" value="${labelValue}" class="length_1_5 ${cssClass }" id="${labelId}" name="${labelName}">
 	<a href="javascript:tree.user.show('${ctx}','${id}','${labelId}','${checkType }','${orgId }','${checkFieldId }','${checkFieldProm }');" class="btn btn-primary btn-table">选择</a>
</div>
