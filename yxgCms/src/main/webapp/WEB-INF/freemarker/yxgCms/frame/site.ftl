<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>${ (project.title)! }</title>
  <@tiles.insertAttribute name="header"/>
</head>
<body>
  <@tiles.insertAttribute name="body"/>
  <@tiles.insertAttribute name="footer"/>
</body>
</html>