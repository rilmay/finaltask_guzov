<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<jsp:directive.attribute name="variable" required="true" description="variable to display"/>
<c:if test="${not empty variable}">
    <c:out value="${variable}"/>
</c:if>
<c:if test="${empty variable}">
    ${text['title.unknown']}
</c:if>
