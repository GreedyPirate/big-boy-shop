<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的购物车</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script src="/res/js/com.js"></script>
<script type="text/javascript">
	//清空购物车
	function clearCart(){
		window.location.href = "/cart/clear.shtml"
	}
	//结算
	function trueBuy(){
		window.location.href = "/buyer/statement.shtml";
	}
	//删除购物项
	function deleteItem(skuId){
		window.location.href = "/cart/deleteItem.shtml?skuId"+skuId;
	}
</script>
</head>
<body>
<jsp:include page="../share/head.jsp"></jsp:include>
<ul class="ul step st3_1">
<li title="1.我的购物车" class="here">1.我的购物车</li>
<li title="2.填写核对订单信息">2.填写核对订单信息</li>
<li title="3.成功提交订单">3.成功提交订单</li>
</ul>
<div class="w ofc case">
	<div class="confirm">
		<div class="tl"></div><div class="tr"></div>
		<div class="ofc pb40">

			<div class="page">
				<b class="l f14 blue pt48">
					我挑选的商品：
				</b>
			</div>
			<table cellspacing="0" class="tab tab4" summary="">
			<thead>
			<tr>
			<th class="wp">商品</th>
			<th>单价（元）</th>
			<th>数量</th>
			<th>操作</th>
			</tr>     
			</thead>
			<tbody>
				<c:forEach items="${cart.shopItems}" var="item">
					<tr>
						<td class="nwp pic">
							<ul class="uls">
								<li>
									<a class="pic" href="javascript:void(0)"><img src="${item.sku.product.img.fullPath}"></a>
									<dl>
										<dt><a href="javascript:void(0)">${item.sku.product.name}</a></dt>
										<dd><span class="red">【赠品】</span>
											<p class="box_d bg_gray2 gray"><a href="#">5元优惠券</a><br></p>
										</dd>
									</dl>
								</li>
							</ul>
						</td>
						<td>${item.sku.skuPrice}</td>
						<td><a onclick="subProductAmount(492,9)" class="inb arr" title="减" href="javascript:void(0);">-</a><input type="text" id="num492" readonly="readonly" value="${item.amount}" name="" size="1" class="txts"><a onclick="addProductAmount(492,9)" class="inb arr" title="加" href="javascript:void(0);">+</a></td>
						<td class="blue"><a onclick="deleteItem(${item.sku.id})" title="删除" href="javascript:void(0);">删除</a></td>
					</tr>
				</c:forEach>

			</tbody>
			</table>
			<div class="page">
				<span class="l">
					<input type="button" onclick="window.open('/product/detail.shtml?id=274')" class="hand btn100x26c" title="继续购物" value="继续购物">
					<input type="button" onclick="clearCart()" class="hand btn100x26c" title="清空购物车" value="清空购物车">
				</span>
				<span class="r box_gray">
					<dl class="total">
						<dt>购物车金额小计：<cite>(共<var id="productAmount">${cart.totalProductAmount}</var>个商品)</cite></dt>
						<dd><em class="l">商品金额：</em>￥<var id="productPrice">${cart.totalProductPrice}</var>元</dd>
						<dd><em class="l">运费：</em>￥<var id="fee">${cart.postage}</var>元</dd>
						<dd class="orange"><em class="l">应付总额：</em>￥<var id="totalPrice">${cart.totalPrice}</var>元</dd>
						<dd class="alg_c"><input type="button" onclick="trueBuy();" class="hand btn136x36a" value="结算" id="settleAccountId"></dd>
					</dl>
				</span>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../share/foot.jsp"></jsp:include>
</body>
</html>