<xsl:stylesheet version="2.0"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:p="http://univ.fr/sepa">
	<xsl:output method="html" version="5.0" encoding="UTF-8"
		indent="yes" />
	<xsl:template match="/">
		<xsl:element name="html">
			<xsl:element name="head">
				<xsl:element name="title">
					Curriculum vitae
				</xsl:element>
				<xsl:element name="link">
					<xsl:attribute name="href">style.css</xsl:attribute>
					<xsl:attribute name="rel">stylesheet</xsl:attribute>
				</xsl:element>
			</xsl:element>

			<!-- TODO: Auto-generated template -->
			<xsl:element name="body">
				<xsl:element name="h3">
					<xsl:text>CV taité le</xsl:text>
					<xsl:value-of select="format-date(current-date(),'[M01]/[D01]/[Y0001]')" />
				</xsl:element>


				<xsl:element name="h1">
					Curriculum vitae
				</xsl:element>
				<xsl:element name="h2">
					<xsl:value-of select="p:cvi/p:identite/nom" />
					<xsl:text> </xsl:text>
					<xsl:value-of select="p:cvi/p:identite/prenom" />
				</xsl:element>


				<xsl:if test="p:cvi/p:objectif/stage">
					<xsl:element name="h3">
						Demande de stage :
						<xsl:value-of select="p:cvi/p:objectif/stage" />
					</xsl:element>
				</xsl:if>
				<xsl:if test="p:cvi/p:objectif/emploi">
					<xsl:element name="h3">
						Demande d'emploi :
						<xsl:value-of select="p:cvi/p:objectif/emploi" />
					</xsl:element>
				</xsl:if>

				<xsl:element name="h3">
					Expérience professionelle
				</xsl:element>
				<xsl:for-each select="p:cvi/p:prof/expe">
					<xsl:element name="tr">
						<xsl:element name="td">
							<xsl:value-of select="datedeb" />
							au
							<xsl:value-of select="datefin" />
						</xsl:element>
						<xsl:element name="td">
							<xsl:value-of select="descript" />
						</xsl:element>
					</xsl:element>
				</xsl:for-each>

				<xsl:element name="h3">
					Formation
				</xsl:element>
				<xsl:for-each select="p:cvi/p:competences/diplome">
					<xsl:element name="tr">
						<xsl:element name="td">
							<xsl:value-of select="descript" />
						</xsl:element>
						<xsl:text> </xsl:text>
						<xsl:element name="td">
							<xsl:value-of select="date" />
						</xsl:element>
						<xsl:text> </xsl:text>
						<xsl:element name="td">
							<xsl:value-of select="institut" />
						</xsl:element>
					</xsl:element>
				</xsl:for-each>

				<xsl:element name="h3">
					Langues vivantes
				</xsl:element>
				<xsl:for-each select="p:cvi/p:competences/lv">
					<xsl:value-of select="@iso" />
					<xsl:text> : niveau </xsl:text>
					<xsl:value-of select="@cert" />
					<xsl:text> = </xsl:text>
					<xsl:value-of select="@nivs" />
				</xsl:for-each>

				<xsl:element name="h3">
					Langages informatiques maîtrisés
				</xsl:element>
				<xsl:for-each select="p:cvi/p:competences/info/langage">
					<xsl:element name="tr">
						<xsl:element name="td">
							<xsl:value-of select="nom" />
						</xsl:element>
						<xsl:text> : niveau </xsl:text>
						<xsl:element name="td">
							<xsl:value-of select="niveau" />
						</xsl:element>
					</xsl:element>
				</xsl:for-each>
				<xsl:element name="h3">
					Divers
				</xsl:element>
				<xsl:for-each select="p:cvi/p:divers">
					<xsl:value-of select="." />
				</xsl:for-each>

			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>