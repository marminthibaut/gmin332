<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ontology visualizer</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- CSS Files -->
<link type="text/css" href="css/base.css" rel="stylesheet" />
<link type="text/css" href="css/Spacetree.css" rel="stylesheet" />

<script src="js/d3/d3.js"></script>
<script src="js/d3/d3.layout.js"></script>

<style type="text/css">
.node circle {
	cursor: pointer;
	fill: #fff;
	stroke: steelblue;
	stroke-width: 1.5px;
}

.node text {
	font-size: 11px;
}

path.link {
	fill: none;
	stroke: #ccc;
	stroke-width: 1.5px;
}
</style>

</head>
<body>