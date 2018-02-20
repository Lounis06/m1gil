<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" encoding="UTF-8" indent="yes"/>
 
  <xsl:template match="/">
  	<html>
  		<head>
    		<title>CV</title>
    		<style type="text/css">
    			.red {border: solid 2px red}
    			table {width: 70%}
    		</style>
    	</head>
    	<body>
    		<h1>Curriculum Vitae</h1>
    		<xsl:apply-templates select="identite" />
    		<h2>Objet</h2>
    		<xsl:apply-templates select="objectif" />
    		<h2>Expériences professionelles</h2>
    		<xsl:apply-templates select="prof" />
    		<h2>Compétences</h2>
    		<xsl:apply-templates select="competences" />
    		<h2>Divers</h2>
    		<xsl:apply-templates select="divers" />
    	</body>
  	</html>
  </xsl:template>
  
  <!-- Template identite -->
  <xsl:template match="identite">
  	<p>
  		<xsl:value-of select="nom"></xsl:value-of>
  		<xsl:text> </xsl:text>
  		<xsl:value-of select="prenom"></xsl:value-of>
  	</p>
  </xsl:template>
  
  <!-- Template objectif -->
  <xsl:template match="objectif">
  	<p class="objectif">
  		<!-- condition stage -->
  		<xsl:if test="stage">
  			<xsl:text>Demande de stage : </xsl:text>
  			<xsl:value-of select="stage"></xsl:value-of>
  		</xsl:if>
  		<!-- condition emploi -->
  		<xsl:if test="emploi">
  			<xsl:text>Candidature : </xsl:text>
  			<xsl:value-of select="emploi"></xsl:value-of>
  		</xsl:if>
  	</p>
  </xsl:template>
  
  <!-- Template prof -->
  <xsl:template match="cvi/prof">
  	<table class="red">
  		<xsl:for-each select="expe">
  			<tr>
  				<td>
  					<xsl:text>Du </xsl:text>
  					<xsl:value-of select="datedeb"></xsl:value-of>
  					<xsl:text> au </xsl:text>
  					<xsl:value-of select="datefin"></xsl:value-of>
  				</td>
  				<td>
  					<xsl:value-of select="descript"></xsl:value-of>
  				</td>
  			</tr>
  		</xsl:for-each>
  	</table>
  </xsl:template>
  
  <!-- Template competences -->
  <xsl:template match="cvi/competences">
  	<h2>Formation</h2>
  		<table class="red">
  			<xsl:for-each select="diplome">
  				<tr>
  					<td><xsl:value-of select="date"></xsl:value-of></td>
  					<td><xsl:value-of select="descript"></xsl:value-of></td>
  					<td><xsl:value-of select="institut"></xsl:value-of></td>
  				</tr>
  			</xsl:for-each>
  		</table>
  		
  </xsl:template>
</xsl:stylesheet>