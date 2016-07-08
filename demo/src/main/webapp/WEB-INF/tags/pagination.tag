<%-- <%@tag pageEncoding="UTF-8"%>
<%@ attribute name="paginator" type="com.github.miemiedev.mybatis.paginator.domain.Paginator" required="true"%>
<%@ attribute name="showNum" type="java.lang.Integer" required="false"%>
<%@ attribute name="searchFormId" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
if(paginator != null){
	int showCount = showNum == null ? 5 : showNum;
	Integer RowCount = paginator.getEndRow() <= 0 ? 0 : (paginator.getEndRow() - paginator.getStartRow() + 1);
	int startPage = paginator.getPage();
	int endPage = paginator.getPage() + showCount;
	endPage = endPage <= paginator.getTotalPages() ? endPage : paginator.getTotalPages();
	if(endPage - startPage <= 4){
		startPage = endPage - showCount < 0 ? 1 : startPage;
	}
	request.setAttribute("end_Page", endPage);
	request.setAttribute("start_Page", startPage);
	request.setAttribute("currentPage", paginator.getPage());
	request.setAttribute("totalPages", paginator.getTotalPages());
	request.setAttribute("totalCount", paginator.getTotalCount());
	request.setAttribute( "pgRowCount", RowCount <= 0 ? null : RowCount );
	request.setAttribute( "searchFormId", searchFormId );
	request.setAttribute( "prev_Page", paginator.getPrePage() );
	request.setAttribute( "next_Page", paginator.getNextPage() );
}

%>

<c:if test="${empty pgRowCount}">
<div class="row">
	<div class="col-md-12 col-sm-12" style="padding: 12px 12px; line-height: 1.42857; margin: 10px 0px;text-align: center;">
	抱歉,没有找到数据!
	</div>
</div>
</c:if>

<c:if test="${!empty pgRowCount}">
<div class="row">
	<div class="col-md-5 col-sm-5" style="padding: 12px 12px; line-height: 1.42857; margin: 10px 0px;">
	当前显示&nbsp;${pgRowCount}&nbsp;条,总共&nbsp;${totalCount}&nbsp;条
	</div>
	<div class="col-md-7 col-sm-7">
		<ul class="pagination" style="visibility: visible; float: right;">
		    <c:if test="${currentPage > 1}">
			<li class="prev">
			</c:if>
		    <c:if test="${currentPage <= 1}">
			<li class="prev disabled">
			</c:if>
			<a href="#" data-page="${prev_Page}" title="上一页"><i class="fa fa-angle-left"></i></a>
			</li>
			<c:forEach var="item" begin="${start_Page}" end="${end_Page}">
			<c:if test="${ item == currentPage }">
			<li class="active" data-page="${item}"><a href="#">${item}</a></li>
			</c:if>
			<c:if test="${ item != currentPage }">
			<li ><a href="#" data-page="${item}">${item}</a></li>
			</c:if>			
			</c:forEach>
			<c:if test="${currentPage < totalPages}">
			<li class="next">
			</c:if>
			<c:if test="${currentPage >= totalPages}">
			<li class="next disabled">
			</c:if>			
			<a href="#" data-page="${next_Page}" title="下一页"><i class="fa fa-angle-right"></i></a>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("ul.pagination>li>a").on("click",function(e){
		e.preventDefault();
		if($(this).parent().hasClass("active") || $(this).parent().hasClass("disabled")){
			return;
		}
		$("#${searchFormId}").find("[name=pageNo]").val($(this).data("page"));
		$("#${searchFormId}").submit();
	});
	$("form select[name=pageSize]").on("change", function(node) {
		$("#${searchFormId}").find("[name=pageNo]").val(1);
		$(this).parents("form").submit();
	});	
});
</script>
</c:if>
 --%>