<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:directive.attribute name="variable" required="true" description="variable to display"/>
<jsp:directive.attribute name="rounded" description="variable to display"/>

<c:if test="${not empty variable}">
    <img class="<c:if test="${rounded eq 'rounded'}">is-square is-rounded</c:if>" src="${pageContext.request.contextPath}/img/user/${variable}" />
</c:if>
<c:if test="${empty variable}">
    <img class="<c:if test="${rounded eq 'rounded'}">is-square is-rounded</c:if>" src=" <c:url value="/img/common/no_image.png"/> ">
</c:if>
