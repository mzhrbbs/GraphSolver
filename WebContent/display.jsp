<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="https://fonts.googleapis.com/css?family=Yellowtail"
	rel="stylesheet">
<link href="CSS/show.css"  rel='stylesheet' type='text/css'>
</head>

<script src="sig/sigma.core.js"></script>
<script src="sig/conrad.js"></script>
<script src="sig/utils/sigma.utils.js"></script>
<script src="sig/utils/sigma.polyfills.js"></script>
<script src="sig/sigma.settings.js"></script>
<script src="sig/classes/sigma.classes.dispatcher.js"></script>
<script src="sig/classes/sigma.classes.configurable.js"></script>
<script src="sig/classes/sigma.classes.graph.js"></script>
<script src="sig/classes/sigma.classes.camera.js"></script>
<script src="sig/classes/sigma.classes.quad.js"></script>
<script src="sig/classes/sigma.classes.edgequad.js"></script>
<script src="sig/captors/sigma.captors.mouse.js"></script>
<script src="sig/captors/sigma.captors.touch.js"></script>
<script src="sig/renderers/sigma.renderers.canvas.js"></script>
<script src="sig/renderers/sigma.renderers.webgl.js"></script>
<script src="sig/renderers/sigma.renderers.svg.js"></script>
<script src="sig/renderers/sigma.renderers.def.js"></script>
<script src="sig/renderers/webgl/sigma.webgl.nodes.def.js"></script>
<script src="sig/renderers/webgl/sigma.webgl.nodes.fast.js"></script>
<script src="sig/renderers/webgl/sigma.webgl.edges.def.js"></script>
<script src="sig/renderers/webgl/sigma.webgl.edges.fast.js"></script>
<script src="sig/renderers/webgl/sigma.webgl.edges.arrow.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.labels.def.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.hovers.def.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.nodes.def.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edges.def.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edges.curve.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edges.arrow.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edges.curvedArrow.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edgehovers.def.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edgehovers.curve.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edgehovers.arrow.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edgehovers.curvedArrow.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.extremities.def.js"></script>
<script src="sig/renderers/svg/sigma.svg.utils.js"></script>
<script src="sig/renderers/svg/sigma.svg.nodes.def.js"></script>
<script src="sig/renderers/svg/sigma.svg.edges.def.js"></script>
<script src="sig/renderers/svg/sigma.svg.edges.curve.js"></script>
<script src="sig/renderers/svg/sigma.svg.labels.def.js"></script>
<script src="sig/renderers/svg/sigma.svg.hovers.def.js"></script>
<script src="sig/middlewares/sigma.middlewares.rescale.js"></script>
<script src="sig/middlewares/sigma.middlewares.copy.js"></script>
<script src="sig/misc/sigma.misc.animation.js"></script>
<script src="sig/misc/sigma.misc.bindEvents.js"></script>
<script src="sig/misc/sigma.misc.bindDOMEvents.js"></script>
<script src="sig/misc/sigma.misc.drawHovers.js"></script>

<script src="sig/renderers/canvas/settings.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edges.labels.def.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edges.labels.curve.js"></script>
<script src="sig/renderers/canvas/sigma.canvas.edges.labels.curvedArrow.js"></script>
<script src="sig/sigma.parsers.json.js"></script>


<body>
	
	<div id="cont">
	<h1>Graph Center</h1>
	<div id="graph-container">
	</div>
	</div>	
	<div id="submitPos">
		<div align="center">
			<h4>
				<i>The graph center nodes are  </i>	
				<%ArrayList<Integer> arr = (ArrayList<Integer>) session.getAttribute("dist");
				
				int i  =0;
	//		   
				while (i < arr.size()){
				%>		
				<i><%out.println(arr.get(i++)+","); %></i>
				<%
				}
				
				%>
			</h4>
		</div>
	
	</div>
	

<script>

var g = {
	nodes : [],
	edges : []
}; 



g = <%= session.getAttribute("json") %>;


// Create new Sigma instance in graph-container div (use your div name here) 
s = new sigma({
	graph : g,
	container : 'graph-container',
	renderer : {
		container : document.getElementById('graph-container'),
		type : 'canvas'
	},
	settings: {
	    maxNodeSize: '9',
	    defaultNodeColor : '#DC143C',
		defaultEdgeColor : '#07889B',
		edgeColor : '#808080',
		labelColor : 'node',
		defaultLabelColor : '#66B9BF',
		edgeLabelSize : 'proportional',
		edgeLabelColor: '#006400',
		minEdgeSize: '2.5',
		maxEdgeSize: '5',
		minArrowSize: '8',
		sideMargin: 1,
	    
	}
});

</script>


</body>

</html>