<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>Big-Boy商城</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script src="/res/js/com.js"></script>
<script type="text/javascript">
//登陆
function login(){
	window.location.href = "../buyer/login.jsp";
}
</script>
</head>
<body>
<jsp:include page="../share/head.jsp"></jsp:include>
<div class="w loc">
	<div class="h-title">
		<div class="h-logo"><a href="#"><img  src="/res/img/pic/logo.jpg" height="50px" /></a></div>
	    <div class="h-search">
	      	<input type="text" />
	        <div class="h-se-btn"><a href="#">搜索</a></div>
	    </div>
	</div>
	<dl id="cart" class="cart r">
		<dt><a href="#" title="结算">结算</a>购物车:<b id="">5</b>件</dt>
		<dd class="hidden">
			<p class="alg_c hidden">购物车中还没有商品，赶紧选购吧</p>
			<h3 title="最新加入的商品">最新加入的商品</h3>
			<ul class="uls">
				<li>
					<a href="#" title="霁蓝 风衣男 2016春装简约中长款大衣 韩版修身商务休闲上衣男装外套 F001 军绿色 XXL">
					<img src="/res/img/product/50x50/p50x50 (1).png" alt="霁蓝 风衣男 2016春装简约中长款大衣 韩版修身商务休闲上衣男装外套 F001 军绿色 XXL"/></a>
					<p class="dt"><a href="#" title="霁蓝 风衣男 2016春装简约中长款大衣 韩版修身商务休闲上衣男装外套 F001 军绿色 XXL">霁蓝 风衣男 2016春装简约中长款大衣 韩版修身商务休闲上衣男装外套 F001 军绿色 XXL</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="丹杰仕春季新款风衣男 韩版单排扣风衣修身潮中长款大衣男装风衣棉外套 4315866蓝色 L">
					<img src="/res/img/product/50x50/p50x50 (2).jpg" alt="丹杰仕春季新款风衣男 韩版单排扣风衣修身潮中长款大衣男装风衣棉外套 4315866蓝色 L" /></a>
					<p class="dt"><a href="#" title="丹杰仕春季新款风衣男 韩版单排扣风衣修身潮中长款大衣男装风衣棉外套 4315866蓝色 L">丹杰仕春季新款风衣男 韩版单排扣风衣修身潮中长款大衣男装风衣棉外套 4315866蓝色 L</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="真维斯男装 春装 韩版纯棉舒适休闲百搭风衣外套JW-54-122203 2188 浅杏 L">
					<img src="/res/img/product/50x50/p50x50 (3).jpg" alt="真维斯男装 春装 韩版纯棉舒适休闲百搭风衣外套JW-54-122203 2188 浅杏 L" /></a>
					<p class="dt"><a href="#" title="真维斯男装 春装 韩版纯棉舒适休闲百搭风衣外套JW-54-122203 2188 浅杏 L">真维斯男装 春装 韩版纯棉舒适休闲百搭风衣外套JW-54-122203 2188 浅杏 L</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="春装新品男装超薄防风连帽轻型夹克外套E|215321043 100灰色 180/100A/L">
					<img src="/res/img/product/50x50/p50x50 (4).jpg" alt="春装新品男装超薄防风连帽轻型夹克外套E|215321043 100灰色 180/100A/L"/></a>
					<p class="dt"><a href="#" title="春装新品男装超薄防风连帽轻型夹克外套E|215321043 100灰色 180/100A/L">春装新品男装超薄防风连帽轻型夹克外套E|215321043 100灰色 180/100A/L</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="佐丹奴外套 男装春季薄防风衣插肩袖连帽衫 男士运动风衣01074585 09经典黑色 中码(170/96A)">
					<img src="/res/img/product/50x50/p50x50 (5).jpg" alt="佐丹奴外套 男装春季薄防风衣插肩袖连帽衫 男士运动风衣01074585 09经典黑色 中码(170/96A)" /></a>
					<p class="dt"><a href="#" title="佐丹奴外套 男装春季薄防风衣插肩袖连帽衫 男士运动风衣01074585 09经典黑色 中码(170/96A)">佐丹奴外套 男装春季薄防风衣插肩袖连帽衫 男士运动风衣01074585 09经典黑色 中码(170/96A)</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
			</ul>
			<div>
				<p>共<b>5</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class="f20">¥640.00</b></p>
				<a href="#" title="去购物车结算" class="inb btn120x30c">去购物车结算</a>
			</div>
		</dd>
	</dl>
</div>

<div class="w ofc">
	<div class="l wl">
		<h2 class="h2 h2_l"><em title="销量排行榜">销量排行榜</em><cite></cite></h2>
		<div class="box bg_white">
			<ul class="uls x_50x50">
				<li>
					<var class="sfont">1</var>
					<a href="#" title="佐丹奴外套 男装春季薄防风衣" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (6).jpg" alt="佐丹奴外套 男装春季薄防风衣" /></a>
					<dl>
						
						<dt><a href="#" title="佐丹奴外套" target="_blank">佐丹奴外套</a></dt>
						<dd class="orange">￥180 ~ ￥230</dd>
					</dl>
				</li>
				<li>
					<var class="sfont">2</var>
					<a href="#" title="轻型夹克外套" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (5).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="轻型夹克外套" target="_blank">轻型夹克外套</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
				<li>
					<var class="sfont">3</var>
					<a href="#" title="真维斯男装" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (7).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="真维斯男装" target="_blank">真维斯男装</a></dt>
						<dd class="orange">￥160 ~ ￥199</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="霁蓝 风衣" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (8).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="霁蓝 风衣" target="_blank">霁蓝 风衣</a></dt>
						<dd class="orange">￥130 ~ ￥159</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="丹杰仕春季新款" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (9).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="丹杰仕春季新款" target="_blank">丹杰仕春季新款</a></dt>
						<dd class="orange">￥120 ~ ￥160</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="卡帝乐鳄鱼" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (10).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="卡帝乐鳄鱼" target="_blank">卡帝乐鳄鱼</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
			</ul>
		</div>

		<h2 class="h2 h2_l mt"><em title="我的浏览记录">我的浏览记录</em><cite></cite></h2>
		<div class="box bg_white">
			<ul class="uls x_50x50">
				<li>
					<var class="sfont">1</var>
					<a href="#" title="佐丹奴外套 男装春季薄防风衣" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (6).jpg" alt="佐丹奴外套 男装春季薄防风衣" /></a>
					<dl>
						
						<dt><a href="#" title="佐丹奴外套" target="_blank">佐丹奴外套</a></dt>
						<dd class="orange">￥180 ~ ￥230</dd>
					</dl>
				</li>
				<li>
					<var class="sfont">2</var>
					<a href="#" title="轻型夹克外套" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (5).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="轻型夹克外套" target="_blank">轻型夹克外套</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
				<li>
					<var class="sfont">3</var>
					<a href="#" title="真维斯男装" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (7).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="真维斯男装" target="_blank">真维斯男装</a></dt>
						<dd class="orange">￥160 ~ ￥199</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="霁蓝 风衣" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (8).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="霁蓝 风衣" target="_blank">霁蓝 风衣</a></dt>
						<dd class="orange">￥130 ~ ￥159</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="丹杰仕春季新款" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (9).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="丹杰仕春季新款" target="_blank">丹杰仕春季新款</a></dt>
						<dd class="orange">￥120 ~ ￥160</dd>
					</dl>
				</li>
				<li>
					<a href="#" title="卡帝乐鳄鱼" target="_blank" class="pic"><img src="/res/img/product/50x50/p50x50 (10).jpg" alt="" /></a>
					<dl>
						
						<dt><a href="#" title="卡帝乐鳄鱼" target="_blank">卡帝乐鳄鱼</a></dt>
						<dd class="orange">￥120 ~ ￥150</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>
	<div class="r wr">
		<h2 class="h2 h2_r rel"><samp></samp><em title="热卖推荐">&nbsp;&nbsp;&nbsp;热卖推荐</em></h2>
		<div class="box bg_white">
			<ul class="uls i_150x150 x4_150x150">
				<li>
					<a href="productDetail.jsp" title="" target="_blank" class="pic"><img src="/res/img/product/220x280/p220x282 (1).jpg" alt="" /></a>
					<dl>
						<dt><a href="productDetail.jsp" title="" target="_blank"></a></dt>
						<dd class="h40"></dd>
						<dd class="orange">￥120 ~ ￥150</dd>
						<dd><a href="productDetail.jsp" title="立即抢购" class="inb btn70x21 mr">立即抢购</a></dd>
					</dl>
				</li>
				<li>
					<a href="productDetail.jsp" title="" target="_blank" class="pic"><img src="/res/img/product/220x280/p220x282 (2).jpg" alt="" /></a>
					<dl>
						<dt><a href="productDetail.jsp" title="" target="_blank"></a></dt>
						<dd class="h40"></dd>
						<dd class="orange">￥120 ~ ￥150</dd>
						<dd><a href="productDetail.jsp" title="立即抢购" class="inb btn70x21 mr">立即抢购</a></dd>
					</dl>
				</li>
				<li>
					<a href="productDetail.jsp" title="" target="_blank" class="pic"><img src="/res/img/product/220x280/p220x282 (3).jpg" alt="" /></a>
					<dl>
						<dt><a href="productDetail.jsp" title="" target="_blank"></a></dt>
						<dd class="h40"></dd>
						<dd class="orange">￥120 ~ ￥150</dd>
						<dd><a href="productDetail.jsp" title="立即抢购" class="inb btn70x21 mr">立即抢购</a></dd>
					</dl>
				</li>
				<li>
					<a href="productDetail.jsp" title="" target="_blank" class="pic"><img src="/res/img/product/220x280/p220x282 (4).jpg" alt="" /></a>
					<dl>
						<dt><a href="productDetail.jsp" title="" target="_blank"></a></dt>
						<dd class="h40"></dd>
						<dd class="orange">￥120 ~ ￥150</dd>
						<dd><a href="productDetail.jsp" title="立即抢购" class="inb btn70x21 mr">立即抢购</a></dd>
					</dl>
				</li>
			</ul>
		</div>

		<h2 class="h2 h2_filter mt"><em title="商品筛选">商品筛选</em><cite><a href="javascript:void(0);" id="filterRest" title="重置筛选条件">重置筛选条件</a></cite></h2>
			<ul class="uls filter">
				<c:if test="${flag}">
					<li>
						<label>已选条件：</label>
						<p class="sel">
							<c:forEach items="${query}" var="entry">
							<a href="javascript:void(0);">
							<em>${entry.key }：</em>${entry.value }
							<cite title="关闭此筛选条件">X</cite></a>
							</c:forEach>
						</p>
					</li>
				</c:if>
				<c:if test="${!empty brands}">
					<li>
						<b>品牌：</b>
						<p>
							<a href="javascript:void(0);" title="不限" class="here">不限</a>
							<c:forEach items="${brands }" var="brand">
							<a href="javascript:void(0);" title="${brand.name }" onclick="window.location.href='/product/display/list.shtml?brandId=${brand.id}&brandName=${brand.name }&typeId=${typeId }&typeName=${typeName }'">${brand.name }</a>
							</c:forEach>
						</p>
					</li>
				</c:if>
				<li><b>价格：</b><p>
					<a href="javascript:void(0);" title="不限" class="here">不限</a>
					<a href="javascript:void(0);" title="1-99">0-79</a>
					<a href="javascript:void(0);" title="100-199">80-199</a>
					<a href="javascript:void(0);" title="200-499">200-299</a>
					<a href="javascript:void(0);" title="200-499">300-499</a>
					<a href="javascript:void(0);" title="200-499">500-599</a>
					<a href="javascript:void(0);" title="200-499">600以上</a>
				</p></li>
				<c:if test="${!empty types}">
					<li>
						<b>类型：</b>
						<p>
							<a href="javascript:void(0);" title="不限" class="here">不限</a>
							<c:forEach items="${types }" var="type">
							<a href="javascript:void(0);" title="${type.name }" onclick="window.location.href='/product/display/list.shtml?typeId=${type.id }&typeName=${type.name}&brandId=${brandId }&brandName=${brandName }'">${type.name }</a>
							</c:forEach>
						</p>
					</li>
				</c:if>
				<li><b>材质：</b><p>
					<a href="javascript:void(0);" title="不限" class="here">不限</a>
					<c:forEach items="${features }" var="feature">
						<a href="javascript:void(0);" title="${feature.name }">${feature.name }</a>
					</c:forEach>
				</p></li>

				<li><b>适用人群：</b><p>
					<a href="javascript:void(0);" title="不限" class="here">不限</a>
					<a href="javascript:void(0);" title="青年">青年</a>
					<a href="javascript:void(0);" title="青少年">青少年</a>
					<a href="javascript:void(0);" title="大码">大码</a>
					<a href="javascript:void(0);" title="老年">老年</a>
				</p></li>
			</ul>
			<div class="sort_type">
				<a href="javascript:void(0);" title="销量" class="sales">销量</a>
				<a href="javascript:void(0);" title="价格" class="price">价格</a>
				<a href="javascript:void(0);" title="上架时间" class="time">上架时间</a>
			</div>
			<div class="mt ofc">
				<ul class="uls i_150x150 x4_150x150b">
					<c:forEach items="${pagination.list }" var="product">
						<li>
							<a href="/html/product/${product.id }.html" title="${product.name }" target="_blank" class="pic"><img src="${product.img.fullPath }" alt="${product.name }" /></a>
							<dl>
								<dt><a href="/product/detail.shtml?id=${product.id }" title="${product.name }" target="_blank">${fn:substring(product.name,0,12)}</a></dt>
								<dd class="orange">￥128.00</dd>
								<dd>北京有货</dd>
								<dd><a href="#" title="加入购物车" class="inb btn70x21 mr">加入购物车</a></dd>
							</dl>
							<img src="/res/img/pic/hot.gif" alt="热门" class="type" />
						</li>
					</c:forEach>
				</ul>
			<div class="page pb15">
				<span class="r inb_a page_b">
					<c:forEach items="${pagination.pageView }" var="page">
						${page }
					</c:forEach>
				</span>
			</div>
	</div>
</div>
</div>
<jsp:include page="../share/foot.jsp"></jsp:include>
</body>
</html>