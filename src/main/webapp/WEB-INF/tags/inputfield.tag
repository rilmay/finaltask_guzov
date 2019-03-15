<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<jsp:directive.attribute name="label" required="true" description="label"/>
<jsp:directive.attribute name="name" required="true" description="key"/>
<jsp:directive.attribute name="requied" description="required"/>
<jsp:directive.attribute name="type" description="type"/>


<c:set value="${label}" var="langlabel"/>
<c:set value="???${label}???" var="notInMap"/>

<c:if test="${text[label] != notInMap}">
    <p class="is-hidden">${langlabel = text[label]}</p>
</c:if>

<label for="field" class="label">${langlabel}</label>

<div class="field">
    <div class="control">
        <input id="field" class="input is-normal"
                <c:choose>
                    <c:when test="${not empty type}">
                        type="${type}"
                    </c:when>
                    <c:otherwise>
                        type="text"
                    </c:otherwise>
                </c:choose>
               name="${name}"
                <c:if test="${requied != 'false'}"> required="required" </c:if>
               placeholder="${langlabel}"
               value="${requestScope.get(name)}"/>
    </div>
</div>