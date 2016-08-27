<%-- 
Copyright 2013 Brady Houseknecht

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="req" value="${requestScope['javax.servlet.forward.servlet_path']}" />

<header>
<pre>
<nobr> __    __     ______   __        __    __     __   __   ______    <br />
/\ "-./  \   /\  == \ /\ \      /\ "-./  \   /\ \ / /  /\  ___\   <br />
\ \ \-./\ \  \ \  _-/ \ \ \     \ \ \-./\ \  \ \ \'/   \ \ \____  <br />
 \ \_\ \ \_\  \ \_\    \ \_\     \ \_\ \ \_\  \ \__|    \ \_____\ <br />
  \/_/  \/_/   \/_/     \/_/      \/_/  \/_/   \/_/      \/_____/ <br />
                                                                  </nobr>
</pre>
    <a href="<c:url value="/index.jsp"/>">MP1</a> &gt; ${req}
    <hr />
</header>
