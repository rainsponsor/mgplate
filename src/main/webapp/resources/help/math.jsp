<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>在线帮助</title>
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default"/>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/resources/images/favicon.ico;" media="screen" />
<ptags:staticfile/>
<style type="text/css">
body
{
	font-family: arial, helvetica, sans-serif;
}

table
{
	border-collapse: collapse;
	margin-bottom: 3em;
	font-size: 70%;
	line-height: 1.1;
}

tr:hover, td.start:hover, td.end:hover
{
	background: #FF9;
}

th, td
{
	padding: .3em .5em;
}
td{
	color: #68923A;
}
th
{
	font-weight: normal;
	text-align: left;
	background: url(tabletree-arrow.gif) no-repeat 2px 50%;
	padding-left: 15px;
}

th.name { width: 12em; }
th.location { width: 70em; }
th.color { width: 10em; }

thead th
{
	background: #c6ceda;
	border-color: #fff #fff #888 #fff;
	border-style: solid;
	border-width: 1px 1px 2px 1px;
	padding-left: .5em;
}

tbody th.start
{
	background: url(tabletree-dots.gif) 18px 54% no-repeat;
	padding-left: 45px;
	color: #990099;
}

tbody th.end
{
	background: url(tabletree-dots2.gif) 18px 54% no-repeat;
	padding-left: 26px;
}
tbody th.title{
	font-weight: bold;
	font-style: inherit;
	color: #0000ff;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<table summary="folder contents for fly types" width="100%">
		<thead>
			<tr>
				<th class="name" width="10%">Name</th>
				<th class="location">Location</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th colspan="2" class="title" >常用案例</th>
			</tr>
			<tr>
				<th class="start">化学案例</th>
				<td>
					<font size="4px">
						氯：Cl₂<br>
						2Cl₂ + 2Ca(OH)₂ = Ca(ClO)₂+ +CaCl₂ +2H₂O<br>
						3ClO⁻ → ClO₃⁻ + 2Cl⁻ <br>
						漂白粉：Cl₂与Ca(OH)₂反应 2Cl₂ + 2Ca(OH)₂ = Ca(ClO)₂+ +CaCl₂ +2H₂O
					</font>
				</td>
			</tr>
			<tr background="red" bgcolor="#c6ceda">
				<td colspan="2" style="border-bottom-color: blue;"></td>
			</tr>
			<tr>
				<th colspan="2" class="title">公式</th>
			</tr>
			<tr>
				<th class="start">输入符号</th>
				<td>
					<font size="4px">
						≈&nbsp;&nbsp;≡&nbsp;&nbsp;≠&nbsp;&nbsp;＝&nbsp;&nbsp;≤&nbsp;&nbsp;≥&nbsp;&nbsp;＜&nbsp;&nbsp;＞&nbsp;&nbsp;≮&nbsp;&nbsp;≯&nbsp;&nbsp;∷&nbsp;&nbsp;±&nbsp;&nbsp;＋&nbsp;&nbsp;－&nbsp;&nbsp;×&nbsp;&nbsp;÷&nbsp;&nbsp;／&nbsp;&nbsp;∫&nbsp;&nbsp;∮&nbsp;&nbsp;∝&nbsp;&nbsp;∞&nbsp;&nbsp;∧&nbsp;&nbsp;∨&nbsp;&nbsp;∑&nbsp;&nbsp;∏&nbsp;&nbsp;∪&nbsp;&nbsp;∩&nbsp;&nbsp;∈&nbsp;&nbsp;∵&nbsp;&nbsp;∴&nbsp;&nbsp;⊥&nbsp;&nbsp;‖&nbsp;&nbsp;∠&nbsp;&nbsp;⌒&nbsp;&nbsp;⊙&nbsp;&nbsp;≌&nbsp;&nbsp;∽&nbsp;&nbsp;√ 
					</font>
				</td>
			</tr>
			<tr background="red" bgcolor="#c6ceda">
				<td colspan="2" style="border-bottom-color: blue;"></td>
			</tr>
			<tr>
				<th colspan="2" class="title">化学类</th>
			</tr>
			<tr>
				<th class="start">上角标</th>
				<td>
					<font size="4px">
						¹&nbsp;&nbsp;²&nbsp;&nbsp;³&nbsp;&nbsp;⁴&nbsp;&nbsp;⁵&nbsp;&nbsp;⁶&nbsp;&nbsp;⁷&nbsp;&nbsp;⁸&nbsp;&nbsp;⁹&nbsp;&nbsp;ⁿ&nbsp;&nbsp;⁺&nbsp;&nbsp;⁻&nbsp;&nbsp;⁼&nbsp;&nbsp;⁽&nbsp;&nbsp;⁾
					</font>
				</td>
			</tr>
			<tr>
				<th class="start">下角标</th>
				<td>
					<font size="4px">
						₀&nbsp;&nbsp;₁&nbsp;&nbsp;₂&nbsp;&nbsp;₃&nbsp;&nbsp;₄&nbsp;&nbsp;₅&nbsp;&nbsp;₆&nbsp;&nbsp;₇&nbsp;&nbsp;₈&nbsp;&nbsp;₉&nbsp;&nbsp;₊&nbsp;&nbsp;₋&nbsp;&nbsp;₌&nbsp;&nbsp;₍&nbsp;&nbsp;₎&nbsp;&nbsp;ₐ&nbsp;&nbsp;ₑ&nbsp;&nbsp;ₒ&nbsp;&nbsp;ₓ&nbsp;&nbsp;ₔ
					</font>
				</td>
			</tr>
			<tr>
				<th class="start">箭头类</th>
				<td>
					<font size="4px">↗ ↙ ↖ ↘ ↑ ↓ ↔ ↕ ↑ ↓ → ←</font>
				</td>
			</tr>
			<tr background="red" bgcolor="#c6ceda">
				<td colspan="2" style="border-bottom-color: blue;"></td>
			</tr>
			<tr>
				<th colspan="2" class="title">数学类</th>
			</tr>
			<tr>
				<th class="start">上角标</th>
				<td>
					<font size="4px">
						¹&nbsp;&nbsp;²&nbsp;&nbsp;³&nbsp;&nbsp;⁴&nbsp;&nbsp;⁵&nbsp;&nbsp;⁶&nbsp;&nbsp;⁷&nbsp;&nbsp;⁸&nbsp;&nbsp;⁹&nbsp;&nbsp;ⁿ&nbsp;&nbsp;⁺&nbsp;&nbsp;⁻&nbsp;&nbsp;⁼&nbsp;&nbsp;⁽&nbsp;&nbsp;⁾
					</font>
				</td>
			</tr>
			<tr>
				<th class="start">下角标</th>
				<td>
					<font size="4px">
						₀&nbsp;&nbsp;₁&nbsp;&nbsp;₂&nbsp;&nbsp;₃&nbsp;&nbsp;₄&nbsp;&nbsp;₅&nbsp;&nbsp;₆&nbsp;&nbsp;₇&nbsp;&nbsp;₈&nbsp;&nbsp;₉&nbsp;&nbsp;₊&nbsp;&nbsp;₋&nbsp;&nbsp;₌&nbsp;&nbsp;₍&nbsp;&nbsp;₎&nbsp;&nbsp;ₐ&nbsp;&nbsp;ₑ&nbsp;&nbsp;ₒ&nbsp;&nbsp;ₓ&nbsp;&nbsp;ₔ
					</font>
				</td>
			</tr>
			<tr>
				<th class="start">基本符号</th>
				<td>
					<font size="4px">
						＋&nbsp;&nbsp;－&nbsp;&nbsp;×&nbsp;&nbsp;÷&nbsp;&nbsp;（&nbsp;&nbsp;／&nbsp;&nbsp;）&nbsp;&nbsp;
					</font>
				</td>
			</tr>
			<tr>
				<th class="start">正负号</th>
				<td>
					<font size="4px">±</font>
				</td>
			</tr>
			<tr>
				<th class="start">度</th>
				<td>
					<font size="4px">° &nbsp;&nbsp;℃ </font>
				</td>
			</tr>
			<tr>
				<th class="start">导数</th>
				<td>
					<font size="4px">∫ &nbsp;&nbsp;∬  </font>
				</td>
			</tr>
			<tr>
				<th class="start">包含被包含</th>
				<td>
					<font size="4px">⊆ &nbsp;&nbsp;⊇ &nbsp;&nbsp;⊂ &nbsp;&nbsp;⊃ </font>
				</td>
			</tr>
			<tr>
				<th class="start">推出号</th>
				<td>
					<font size="4px">⇒ </font>
				</td>
			</tr>
			<tr>
				<th class="start">等价号</th>
				<td>
					<font size="4px">⇔</font>
				</td>
			</tr>
			<tr>
				<th class="start">箭头类</th>
				<td>
					<font size="4px">↗ ↙ ↖ ↘ ↑ ↓ ↔ ↕ ↑ ↓ → ←</font>
				</td>
			</tr>
			<tr>
				<th class="start">绝对值</th>
				<td>
					<font size="4px">｜</font>
				</td>
			</tr>
			<tr>
				<th class="start">弧</th>
				<td>
					<font size="4px">⌒</font>
				</td>
			</tr>
			<tr>
				<th class="start">圆</th>
				<td>
					<font size="4px">⊙</font>
				</td>
			</tr>
		</tbody>
	</table>
	
	<!-- 
	<table summary="folder contents for fly types">
		<thead>
			<tr>
				<th class="name">Name</th>
				<th class="location">Location</th>
				<th class="color">Color</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th colspan="3">House</th>
			</tr>
			<tr>
				<th class="start">Carrion Fly</th>
				<td>Worldwide</td>
				<td>gray</td>
			</tr>
			<tr>
				<th class="start">Office Fly</th>
				<td>California, Bay Area</td>
				<td>white</td>
			</tr>
			<tr>
				<th class="end">Common House Fly</th>
				<td></td>
				<td>brown</td>
			</tr>
			<tr>
				<th colspan="3">Horse</th>
			</tr>
			<tr>
				<th class="start">Horn Fly</th>
				<td>Kansas</td>
				<td>red</td>
			</tr>
			<tr>
				<th class="start">Face Fly</th>
				<td></td>
				<td>green</td>
			</tr>
			<tr class="end">
				<th class="end">Stable Fly</th>
				<td></td>
				<td>black</td>
			</tr>
		</tbody>
	</table>
	 -->
</body>
</html>
