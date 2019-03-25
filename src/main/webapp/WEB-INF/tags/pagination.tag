<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty pages and not empty current_page}">
<c:if test="${pages.size() != 1}">
    <div class="has-text-centered">
        <br>
        <c:forEach items="${pages}" var="page">
            <c:choose>
                <c:when test="${current_page eq page}">
                    <div class="button is-small is-primary">${page}</div>
                </c:when>
                <c:otherwise>
                    <c:url var="nextUrl" value="">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 'page'}">
                                <c:param name="${entry.key}" value="${entry.value}"/>
                            </c:if>
                        </c:forEach>
                        <c:param name="page" value="${page}"/>
                    </c:url>
                    <a href="${nextUrl}">${page}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <br>
        <br>
    </div>
</c:if>
</c:if>