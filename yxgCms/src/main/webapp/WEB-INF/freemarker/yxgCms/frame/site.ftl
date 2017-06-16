<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>${ (project.title)! }</title>
  <@tiles.insertAttribute name="header"/>
</head>
<body class="skin-blue sidebar-mini">
  <div class="wrapper">
  	<@tiles.insertAttribute name="body"/>
  	<@tiles.insertAttribute name="footer"/>
  </div>
</body>
</html>