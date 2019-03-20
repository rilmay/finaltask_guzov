<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<jsp:directive.attribute name="size" required="true" description="size"/>

<c:if test="${size eq 0}">
    <div class="has-text-centered is-light">
        <br>
        <br>
        <p class="subtitle">${text['title.nothing_to_show']}</p>
        <br>
        <br>
    </div>
</c:if>