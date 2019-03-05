<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:directive.attribute name="variable" required="true" description="variable to display"/>
<c:if test="${not empty variable}">
    <img src="${variable}">
</c:if>
<c:if test="${empty variable}">
    <img height="130" width="130" src="../img/common/person.png" alt="">">
</c:if>
