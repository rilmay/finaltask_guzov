<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:directive.attribute name="variable" required="true" description="variable to display"/>
<jsp:directive.attribute name="rounded" description="variable to display"/>
<jsp:directive.attribute name="type" description="type"/>

<c:if test="${not empty variable}">
    <img class="<c:if test="${rounded eq 'rounded'}">is-square is-rounded</c:if>"
         src="?command=LOAD_IMAGE&photo=${variable}"/>
</c:if>
<c:if test="${empty variable}">
    <c:choose>
        <c:when test="${type eq 'person'}">
            <img class="<c:if test="${rounded eq 'rounded'}">is-square is-rounded</c:if>"
                 src=" <c:url value="/img/common/person.png"/> ">
        </c:when>
        <c:otherwise>
            <img class="<c:if test="${rounded eq 'rounded'}">is-square is-rounded</c:if>"
                 src=" <c:url value="/img/common/no_image.png"/> ">
        </c:otherwise>
    </c:choose>
</c:if>
