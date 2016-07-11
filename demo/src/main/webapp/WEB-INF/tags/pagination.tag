<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="searchFormId" type="java.lang.String"
	required="true"%>
<%@ attribute name="list" type="org.springframework.data.domain.PageImpl"  required="true" %>
 <%

request.setAttribute("list",list);

%> 
<c:if test="${!list.hasContent()}">
	<div class="row">
		<div class="col-md-12 col-sm-12"
			style="padding: 12px 12px; line-height: 1.42857; margin: 10px 0px; text-align: center;">
			抱歉,没有找到数据!</div>
	</div>
</c:if>

<c:if test="${list.hasContent()}">
	<div class="row">
		<div class="col-md-5 col-sm-5"
			style="padding: 12px 12px; line-height: 1.42857; margin: 10px 0px;">
			当前显示&nbsp;${list.size}&nbsp;条,总共&nbsp;${list.totalElements}&nbsp;条</div>
		<div class="col-md-7 col-sm-7">
			<ul class="pagination" style="visibility: visible; float: right;">
				<li class="prev <c:if test="${!list.hasPrevious()}"> disabled </c:if>">
				<a href="#" data-page="${previousPageable.getPageNumber()}" title="上一页"><i
					class="fa fa-angle-left"></i></a>
				</li>
				<c:forEach var="item" begin="${list.getNumber()+1}" end="${list.totalPages}">
					<c:if test="${ item == list.getNumber() +1}">
						<li class="active" data-page="${item}"><a href="#">${item}</a></li>
					</c:if>
					<c:if test="${ item != list.getNumber()+1 }">
						<li><a href="#" data-page="${item}">${item}</a></li>
					</c:if>
				</c:forEach>
				<li class="next <c:if test="${!list.hasNext()}"> disabled </c:if>">
				<a href="#" data-page="${list.nextPageable().getPageNumber()+1}" title="下一页"><i
					class="fa fa-angle-right"></i></a>
				</li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$("ul.pagination>li>a").on(
					"click",
					function(e) {
						e.preventDefault();
						if ($(this).parent().hasClass("active")
								|| $(this).parent().hasClass("disabled")) {
							return;
						}
						$("#${searchFormId}").find("[name=pageNo]").val(
								$(this).data("page"));
						$("#${searchFormId}").submit();
					});
			$("form select[name=pageSize]").on("change", function(node) {
				$("#${searchFormId}").find("[name=pageNo]").val(1);
				$(this).parents("form").submit();
			});
		});
	</script>
</c:if>
